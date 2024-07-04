package cn.mrcsh.zfcloudpanbackend.mapper;

import cn.mrcsh.zfcloudpanbackend.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {
}
