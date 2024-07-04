package cn.mrcsh.zfcloudpanbackend.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class APPConfig {
    @Value("${app.default-role-id}")
    private Integer defaultRoleId;
}
