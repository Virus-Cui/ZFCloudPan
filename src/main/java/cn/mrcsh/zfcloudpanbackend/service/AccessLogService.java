package cn.mrcsh.zfcloudpanbackend.service;

import cn.mrcsh.zfcloudpanbackend.entity.po.AccessLog;
import cn.mrcsh.zfcloudpanbackend.entity.structure.PageStructure;

public interface AccessLogService {
    void insertLog(AccessLog accessLog);

    PageStructure<AccessLog> selectPage(Integer pageSize, Integer currentPage);
}
