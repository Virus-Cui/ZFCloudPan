package cn.mrcsh.zfcloudpanbackend.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.mrcsh.zfcloudpanbackend.config.APPConfig;
import cn.mrcsh.zfcloudpanbackend.entity.po.FileInfo;
import cn.mrcsh.zfcloudpanbackend.mapper.FileInfoMapper;
import cn.mrcsh.zfcloudpanbackend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileInfoMapper mapper;

    @Autowired
    private APPConfig config;

    @Override
    public void save(FileInfo fileInfo) throws IOException {
        FileInfo source = mapper.selectById(fileInfo.getFileId());
        if(source == null){
            mapper.insert(fileInfo);
        }else {
            mapper.updateById(fileInfo);
        }
        saveFile(fileInfo);
    }

    public void saveFile(FileInfo fileInfo) throws IOException {
        File folder = new File(config.getDataSavePath()+File.separator+fileInfo.getFileAbsPath()+"temp");
        if(!folder.exists()){
            folder.mkdirs();
        }
        File temp = new File(folder, String.valueOf(fileInfo.getChunkIndex()));
        if(!temp.exists()){
            temp.createNewFile();
        }
        fileInfo.getFile().transferTo(temp);
        if(fileInfo.getChunkIndex()+1 == fileInfo.getChunkNum()){
            transform(fileInfo);
        }
    }

    @Override
    public void transform(FileInfo source) throws IOException {
        FileInfo fileInfo= mapper.selectById(source.getFileId());
        File sourceFile = new File(config.getDataSavePath()+File.separator+fileInfo.getFileAbsPath());
        if(!sourceFile.exists() || !sourceFile.isFile()){
            sourceFile.createNewFile();
        }
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(sourceFile));
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sourceFile),StandardCharsets.UTF_8));
        File tempFolder = new File(config.getDataSavePath()+File.separator+fileInfo.getFileAbsPath()+"temp");
        for (Integer i = 0; i < fileInfo.getChunkNum(); i++) {
            File temp = new File(tempFolder, String.valueOf(i));
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(temp));
//            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(temp), StandardCharsets.UTF_8));
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
        }
        outputStream.close();
        FileUtil.del(tempFolder);
    }
}
