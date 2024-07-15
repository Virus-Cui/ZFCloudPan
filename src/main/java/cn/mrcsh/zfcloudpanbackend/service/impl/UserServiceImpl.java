package cn.mrcsh.zfcloudpanbackend.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.mrcsh.zfcloudpanbackend.entity.po.User;
import cn.mrcsh.zfcloudpanbackend.entity.structure.PageStructure;
import cn.mrcsh.zfcloudpanbackend.mapper.UserMapper;
import cn.mrcsh.zfcloudpanbackend.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());
        User user1 = userMapper.selectOne(queryWrapper);
        if(user1 != null){
            throw new NullPointerException();
        }
        user.setId(IdUtil.getSnowflakeNextIdStr());
        user.setPassword(SecureUtil.sha256(user.getPassword()));
        userMapper.insert(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    @Override
    public void deleteUser(String id) {
        userMapper.deleteById(id);
    }

    @Override
    public User getUser(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public List<User> selectByRoleId(Integer id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role", id);
        return userMapper.selectList(queryWrapper);
    }

    @Override
    public PageStructure<User> selectByPage(Integer pageNo, Integer pageSize, String username) {
        PageStructure<User> structure = new PageStructure<>();
        Page<User> page = new Page<>(pageNo, pageSize);
        userMapper.selectPage(page, new QueryWrapper<User>().like("user_name", "%" + username + "%"));
        structure.setPage_size(pageSize);
        structure.setTotal(page.getTotal());
        structure.setData(page.getRecords());
        return structure;
    }
}
