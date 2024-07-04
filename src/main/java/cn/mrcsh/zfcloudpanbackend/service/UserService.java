package cn.mrcsh.zfcloudpanbackend.service;

import cn.mrcsh.zfcloudpanbackend.entity.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    User getUser(String username);

    List<User> selectByRoleId(Integer id);
}
