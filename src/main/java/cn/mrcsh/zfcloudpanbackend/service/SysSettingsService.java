package cn.mrcsh.zfcloudpanbackend.service;

import cn.mrcsh.zfcloudpanbackend.entity.po.SysSettings;

public interface SysSettingsService {
    void update(SysSettings sysSettings);

    SysSettings getSysSettings();
}
