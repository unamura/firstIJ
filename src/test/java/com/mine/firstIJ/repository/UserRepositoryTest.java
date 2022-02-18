package com.mine.firstIJ.repository;

import com.mine.firstIJ.repository.entity.UserCommon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserCommonRepository userCommonRepository;

    @Test
    public void findUserCommon() {
        List<UserCommon> userCommons = userCommonRepository.findAll();
        assertNotNull(userCommons);
    }

    @Test
    public void findUserTopId() {
        List<UserCommon> userCommons = userCommonRepository.findTopByOrderByIdDesc();
        assertNotNull(userCommons);
    }
}
