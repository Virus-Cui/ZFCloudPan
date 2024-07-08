package cn.mrcsh.zfcloudpanbackend.entity.po;

import cn.mrcsh.zfcloudpanbackend.entity.structure.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_access_log")
public class AccessLog extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private String id;
    private String path;
    private String method;
    private String access_as;
    private String access_from;
    private String result;
    private String params;
}
