package cn.mrcsh.zfcloudpanbackend.entity.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    // BASE64
    private String userName;
    // BASE64
    private String password;
    private String email;
}
