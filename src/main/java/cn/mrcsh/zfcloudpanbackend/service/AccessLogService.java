package cn.mrcsh.zfcloudpanbackend.service;

import cn.mrcsh.zfcloudpanbackend.entity.po.AccessLog;

public interface AccessLogService {
    void insertLog(AccessLog accessLog);
}
