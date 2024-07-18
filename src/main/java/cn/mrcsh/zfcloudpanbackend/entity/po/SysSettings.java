package cn.mrcsh.zfcloudpanbackend.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_setting")
public class SysSettings {
    private Integer id;
    private String title;
    private String loginBgImg;
    private String logoSmall;
    private String logo;
    private String logoTextBlack;
    private String logoTextWhite;
}
