package cn.mrcsh.zfcloudpanbackend.entity.po;

import cn.mrcsh.zfcloudpanbackend.entity.structure.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_user")
public class User extends BaseEntity {
    private String id;
    private String userName;
    private String password;
    private Integer role;
    private String email;
    private String avatar;
    private String settings;
}
