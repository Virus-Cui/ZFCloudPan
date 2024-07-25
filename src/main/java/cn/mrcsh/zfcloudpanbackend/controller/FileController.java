package cn.mrcsh.zfcloudpanbackend.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.mrcsh.zfcloudpanbackend.annotation.AccessLog;
import cn.mrcsh.zfcloudpanbackend.entity.dto.UploadFileDto;
import cn.mrcsh.zfcloudpanbackend.entity.po.FileInfo;
import cn.mrcsh.zfcloudpanbackend.enums.FileTypes;
import cn.mrcsh.zfcloudpanbackend.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/stream")
@CrossOrigin
@Slf4j
public class FileController extends BaseController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    @AccessLog()
    public response upload_file(UploadFileDto uploadFileDto) throws InterruptedException, IOException {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileAvatar(null);
        fileInfo.setFileName(uploadFileDto.getFile_name());
        fileInfo.setFileAbsPath((String) StpUtil.getLoginId()+"/"+uploadFileDto.getFile_name());
        fileInfo.setFileType(FileTypes.getFileType("."+FileUtil.getSuffix(fileInfo.getFileName())).getType());
        fileInfo.setFileMd5(null);
        fileInfo.setFileOwner((String) StpUtil.getLoginId());
        fileInfo.setFilePid("");
        fileInfo.setFileSize(uploadFileDto.getFile_size());
        fileInfo.setDeleted(false);
        fileInfo.setStatus("uploading");
        fileInfo.setChunkNum(uploadFileDto.getChunk_num());
        fileInfo.setChunkIndex(uploadFileDto.getChunk_index());
        fileInfo.setFile(uploadFileDto.getFile());
        if (uploadFileDto.getChunk_index() == 0) {
            fileInfo.setFileId(IdUtil.getSnowflakeNextIdStr());
        } else {
            fileInfo.setFileId(uploadFileDto.getFile_id());
        }
        if (uploadFileDto.getChunk_index() + 1 == uploadFileDto.getChunk_num()) {
            fileInfo.setStatus("completed");
        }
        fileService.save(fileInfo);
        return success(fileInfo.getFileId());
    }
}
