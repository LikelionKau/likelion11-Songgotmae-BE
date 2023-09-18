package likelion.underdog.songgotmae.util.redis;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RedisUtilTest {
    @Autowired
    private RedisUtil redisUtil;

    @DisplayName("레디스가 정상 동작하는가?")
    @Test
    public void redisTest() throws Exception {
        //given
        String email = "test@kau.kr";
        String code = "aaa111";

        //when
        redisUtil.setDataExpire(email, code, 60 * 60L);

        //then
        assertThat(redisUtil.existData("test@kau.kr")).isTrue();
        assertThat(redisUtil.existData("test@kau.ac.kr")).isFalse();
        assertThat(redisUtil.getData(email)).isEqualTo("aaa111");
    }

}
