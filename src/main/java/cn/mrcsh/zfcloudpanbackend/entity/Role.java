package cn.mrcsh.zfcloudpanbackend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_role")
public class Role extends BaseEntity{
    private Integer id;
    private String roleName;
    private String menuIds;
}
