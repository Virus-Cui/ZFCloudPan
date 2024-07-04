package cn.mrcsh.zfcloudpanbackend.mapper;

import cn.mrcsh.zfcloudpanbackend.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface RoleMapper extends BaseMapper<Role> {
}
