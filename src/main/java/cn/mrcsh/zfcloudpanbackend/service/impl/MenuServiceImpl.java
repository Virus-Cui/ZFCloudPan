package cn.mrcsh.zfcloudpanbackend.service.impl;

import cn.mrcsh.zfcloudpanbackend.entity.po.Menu;
import cn.mrcsh.zfcloudpanbackend.entity.po.Role;
import cn.mrcsh.zfcloudpanbackend.mapper.MenuMapper;
import cn.mrcsh.zfcloudpanbackend.mapper.UserMapper;
import cn.mrcsh.zfcloudpanbackend.service.MenuService;
import cn.mrcsh.zfcloudpanbackend.service.RoleService;
import cn.mrcsh.zfcloudpanbackend.service.UserService;
import cn.mrcsh.zfcloudpanbackend.utils.TreeUtils;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleService roleService;
    @Qualifier("userMapper")
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addMenu(Menu menu) {
        menuMapper.insert(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        menuMapper.updateById(menu);
    }

    @Override
    public void deleteMenu(Integer id) {
        menuMapper.deleteById(id);
    }

    @Override
    public List<Menu> getMenuList(Integer parentId) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("menu_pid", parentId);
        return menuMapper.selectList(queryWrapper);
    }

    @Override
    public List<Menu> getMenuList() {
        return menuMapper.selectList(null);
    }

    @Override
    public List<Menu> getMenuListByRoleId(Integer roleId) {
        Role role = roleService.getRole(roleId);
        List<Menu> source = new ArrayList<>();
        String menuIds = role.getMenuIds();
        List ids = JSON.parseObject(menuIds, List.class);
        ids.forEach(id -> {
            source.add(menuMapper.selectById(Integer.parseInt(String.valueOf(id))));
        });
        return TreeUtils.streamToTree(source, 0);
    }

    @Override
    public List<String> getAuthList(String userId) {
        Integer roleId = userMapper.selectById(userId).getRole();
        Role role = roleService.getRole(roleId);
        List<String> source = new ArrayList<>();
        String menuIds = role.getAuthIds();
        List ids = JSON.parseObject(menuIds, List.class);
        ids.forEach(id -> {
            source.add(menuMapper.selectById(Integer.parseInt(String.valueOf(id))).getMenuPurview());
        });
        return source;
    }


}
