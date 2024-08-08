package cn.mrcsh.zfcloudpanbackend.handler;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.mrcsh.zfcloudpanbackend.entity.dto.UploadFileDto;
import cn.mrcsh.zfcloudpanbackend.entity.po.FileInfo;
import cn.mrcsh.zfcloudpanbackend.enums.FileTypes;
import cn.mrcsh.zfcloudpanbackend.service.FileService;
import com.alibaba.fastjson2.codec.FieldInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FileUploaderHandler {

    @Autowired
    private FileService fileService;

    public String chunk_upload(UploadFileDto uploadFileDto) {
        String fileId = null;
        try {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileAvatar(null);
            fileInfo.setFileName(uploadFileDto.getFile_name());
            fileInfo.setFileType(FileTypes.getFileType("." + FileUtil.getSuffix(fileInfo.getFileName())).getType());
            fileInfo.setFileMd5(null);
            fileInfo.setFileOwner((String) StpUtil.getLoginId());
            fileInfo.setFilePid("");
            fileInfo.setFileSize(uploadFileDto.getFile_size());
            fileInfo.setDeleted(false);
            fileInfo.setStatus("uploading");
            fileInfo.setChunkNum(uploadFileDto.getChunk_num());
            fileInfo.setChunkIndex(uploadFileDto.getChunk_index());
            fileInfo.setFile(uploadFileDto.getFile());
            fileInfo.setFilePid(uploadFileDto.getFile_pid());
            if (uploadFileDto.getChunk_index() == 0) {
                fileInfo.setFileId(IdUtil.getSnowflakeNextIdStr());
            } else {
                fileInfo.setFileId(uploadFileDto.getFile_id());
            }
            fileInfo.setFileAbsPath((String) StpUtil.getLoginId() + "/" + fileInfo.getFileId() + "." + FileUtil.getSuffix(fileInfo.getFileName()));
            if (uploadFileDto.getChunk_index() + 1 == uploadFileDto.getChunk_num()) {
                fileInfo.setStatus("completed");
            }
            fileId = fileInfo.getFileId();
            fileService.save(fileInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return fileId;
    }
}
