package cn.mrcsh.zfcloudpanbackend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.io.FileUtil;
import cn.mrcsh.zfcloudpanbackend.config.APPConfig;
import cn.mrcsh.zfcloudpanbackend.entity.po.FileInfo;
import cn.mrcsh.zfcloudpanbackend.entity.structure.PageStructure;
import cn.mrcsh.zfcloudpanbackend.mapper.FileInfoMapper;
import cn.mrcsh.zfcloudpanbackend.mapper.UserMapper;
import cn.mrcsh.zfcloudpanbackend.service.FileService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;

@Service
public class FileServiceImpl implements FileService {

    @Value("${sa-token.token-name}")
    private String tokenKey;

    @Autowired
    private FileInfoMapper mapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private APPConfig config;

    @Override
    public void save(FileInfo fileInfo) throws IOException {
        FileInfo source = mapper.selectById(fileInfo.getFileId());
        if (source == null) {
            fileInfo.setCreateTime(new Date());
            mapper.insert(fileInfo);
        } else {
            fileInfo.setUpdateTime(new Date());
            mapper.updateById(fileInfo);
        }
        saveFile(fileInfo);
    }

    public void saveFile(FileInfo fileInfo) throws IOException {
        File folder = new File(config.getDataSavePath() + File.separator + fileInfo.getFileAbsPath() + "temp");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File temp = new File(folder, String.valueOf(fileInfo.getChunkIndex()));
        if (!temp.exists()) {
            temp.createNewFile();
        }
        fileInfo.getFile().transferTo(temp);
        if (fileInfo.getChunkIndex() + 1 == fileInfo.getChunkNum()) {
            transform(fileInfo);
        }
    }

    @Override
    public void transform(FileInfo source) throws IOException {
        FileInfo fileInfo = mapper.selectById(source.getFileId());
        File sourceFile = new File(config.getDataSavePath() + File.separator + fileInfo.getFileAbsPath());
        if (!sourceFile.exists() || !sourceFile.isFile()) {
            sourceFile.createNewFile();
        }
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(sourceFile));
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sourceFile),StandardCharsets.UTF_8));
        File tempFolder = new File(config.getDataSavePath() + File.separator + fileInfo.getFileAbsPath() + "temp");
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

    @Override
    public PageStructure<FileInfo> getFileList(HttpServletRequest request, String filePid, Integer page_size, Integer current_page) {
        String userId = (String) StpUtil.getLoginId();
        QueryWrapper<FileInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_pid", filePid).eq("file_owner", userId);
        Page<FileInfo> page = new Page<>(current_page, page_size);
        mapper.selectPage(page, queryWrapper);
        PageStructure<FileInfo> pageStructure = new PageStructure<>();
        pageStructure.setTotal(page.getTotal());
        pageStructure.setData(page.getRecords());
        return pageStructure;
    }

    @Override
    public void download(HttpServletRequest request, HttpServletResponse response, String fileId, String type) {
        try {
            String login_id = null;
            if (!type.equals("img")) {
                login_id = (String) StpUtil.getLoginId();
            }
            FileInfo fileInfo = mapper.selectById(fileId);
            if (login_id == null && !type.equals("img")) {
                throw new NullPointerException("Null");
            }
            // 下载
            File file = new File(config.getDataSavePath() + File.separator + fileInfo.getFileAbsPath());
            response.setContentLengthLong(file.length());
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[config.getBufferSize()];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }
            response.getOutputStream().flush();
            response.getOutputStream().close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException("文件不存在");
        }
    }
}
