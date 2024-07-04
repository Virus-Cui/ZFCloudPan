package cn.mrcsh.zfcloudpanbackend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_menu")
public class Menu extends BaseEntity{
    private Integer id;
    private String menuName;
    private String menuPurview;
    private String menuRouterPath;
    private Integer menuPid;
    private String menuComment;
    private String icon;
    private boolean outline;
    @TableField(exist = false)
    private List<Menu> treeMenus;
}
