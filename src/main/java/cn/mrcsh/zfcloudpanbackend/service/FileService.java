package cn.mrcsh.zfcloudpanbackend.service;

import cn.mrcsh.zfcloudpanbackend.entity.po.FileInfo;

import java.io.IOException;

public interface FileService {

    void save(FileInfo fileInfo) throws IOException;

    void transform(FileInfo fileInfo) throws IOException;
}
