package cn.mrcsh.zfcloudpanbackend.service;

import cn.mrcsh.zfcloudpanbackend.entity.po.FileInfo;
import cn.mrcsh.zfcloudpanbackend.entity.structure.PageStructure;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface FileService {

    void save(FileInfo fileInfo) throws IOException;

    void transform(FileInfo fileInfo) throws IOException;

    PageStructure<FileInfo> getFileList(HttpServletRequest request, String filePid, Integer page_size, Integer current_page);

    void download(HttpServletRequest request, HttpServletResponse response, String fileId, String type);
}
