package cn.mrcsh.zfcloudpanbackend.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class APPConfig {
    @Value("${app.default-role-id}")
    private Integer defaultRoleId;
    @Value("${app.data-save-path}")
    private String dataSavePath;
    @Value("${app.buffer-size}")
    private Integer bufferSize;
}
