package cn.mrcsh.zfcloudpanbackend.service.impl;

import cn.mrcsh.zfcloudpanbackend.entity.po.AccessLog;
import cn.mrcsh.zfcloudpanbackend.mapper.AccessLogMapper;
import cn.mrcsh.zfcloudpanbackend.service.AccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AccessLogServiceImpl implements AccessLogService {

    @Autowired
    private AccessLogMapper accessLogMapper;

    @Async
    @Override
    public void insertLog(AccessLog accessLog) {
        accessLogMapper.insert(accessLog);
    }
}
