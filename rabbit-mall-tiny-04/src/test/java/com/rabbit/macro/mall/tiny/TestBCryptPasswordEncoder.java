package com.rabbit.macro.mall.tiny;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBCryptPasswordEncoder {
    @Test
    public void testEncode() {
        BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
        String encodedPwd = encoder.encode("123456");
        System.out.println(encodedPwd);
    }
}
