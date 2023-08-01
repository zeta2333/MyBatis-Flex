package usts.pycro.mybatisflex;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("usts.pycro.mybatisflex.mapper")
@SpringBootApplication
public class MyBatisFlexApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisFlexApplication.class, args);
    }

}
