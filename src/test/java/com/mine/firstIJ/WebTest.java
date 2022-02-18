package com.mine.firstIJ;

import com.mine.firstIJ.repository.UserCommonRepository;
import com.mine.firstIJ.repository.entity.UserCommon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

@SpringBootTest
public class WebTest {
    @Autowired
    private UserCommonRepository userCommonRepository;

    @Test
    public void findAllTestUserCommon() {
        List<UserCommon> userCommons = userCommonRepository.findAll();
        assertNotNull(userCommons);
    }
}
