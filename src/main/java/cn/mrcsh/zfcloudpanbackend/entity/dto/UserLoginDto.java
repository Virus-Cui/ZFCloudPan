package cn.mrcsh.zfcloudpanbackend.entity.dto;

import lombok.Data;

@Data
public class UserLoginDto {
    // BASE64
    private String username;
    // BASE64
    private String password;
}
