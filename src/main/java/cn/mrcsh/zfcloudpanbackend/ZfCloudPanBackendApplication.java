package cn.mrcsh.zfcloudpanbackend;

import cn.mrcsh.zfcloudpanbackend.entity.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("cn.mrcsh.zfcloudpanbackend.mapper")
@EnableTransactionManagement
public class ZfCloudPanBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZfCloudPanBackendApplication.class, args);
    }

}
