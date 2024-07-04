package cn.mrcsh.zfcloudpanbackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TreeMenu extends BaseEntity{
    private Integer id;
    private String menuName;
    private String menuPurview;
    private String menuRouterPath;
    private Integer menuPid;
    private String menuComment;
    private List<TreeMenu> treeMenus;
}
