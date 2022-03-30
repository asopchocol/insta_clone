package hero.insta_clone.service.authjwt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisUtilTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void setData() {
        redisUtil.setData("test", "test");
    }

    @Test
    void setDataExpire() {
        redisUtil.setDataExpire("test", "test", 20);
    }
}