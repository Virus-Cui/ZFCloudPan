package cn.mrcsh.zfcloudpanbackend.service.impl;

import cn.mrcsh.zfcloudpanbackend.entity.po.Menu;
import cn.mrcsh.zfcloudpanbackend.mapper.MenuMapper;
import cn.mrcsh.zfcloudpanbackend.service.MenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

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
}
