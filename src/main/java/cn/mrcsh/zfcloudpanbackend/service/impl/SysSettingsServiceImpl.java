package cn.mrcsh.zfcloudpanbackend.service.impl;

import cn.mrcsh.zfcloudpanbackend.entity.po.SysSettings;
import cn.mrcsh.zfcloudpanbackend.mapper.SysSettingsMapper;
import cn.mrcsh.zfcloudpanbackend.service.SysSettingsService;
import cn.mrcsh.zfcloudpanbackend.utils.RedisUtil;
import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysSettingsServiceImpl implements SysSettingsService {

    @Autowired
    private SysSettingsMapper sysSettingsMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void update(SysSettings sysSettings) {
        sysSettings.setId(0);
        sysSettingsMapper.updateById(sysSettings);
        redisUtil.set("pan:settings", JSON.toJSONString(sysSettings));
    }

    @Override
    public SysSettings getSysSettings() {
        return sysSettingsMapper.selectById(0);
    }
}
