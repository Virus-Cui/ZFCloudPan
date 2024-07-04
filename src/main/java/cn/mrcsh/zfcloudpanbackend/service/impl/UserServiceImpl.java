package cn.mrcsh.zfcloudpanbackend.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.mrcsh.zfcloudpanbackend.entity.User;
import cn.mrcsh.zfcloudpanbackend.mapper.UserMapper;
import cn.mrcsh.zfcloudpanbackend.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        user.setId(IdUtil.getSnowflakeNextIdStr());
        userMapper.insert(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    @Override
    public void deleteUser(int id) {
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
}
