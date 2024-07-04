package cn.mrcsh.zfcloudpanbackend.utils;

import cn.mrcsh.zfcloudpanbackend.entity.Menu;

import java.util.List;
import java.util.stream.Collectors;

public class TreeUtils {
    public static List<Menu> streamToTree(List<Menu> treeList, Integer parentId) {
        return treeList.stream()
                .filter(parent -> parent.getMenuPid().equals(parentId))
                .peek(child -> child.setTreeMenus(streamToTree(treeList,child.getId()))).collect(Collectors.toList());

    }
}
