package com.mine.firstIJ.service;

import com.mine.firstIJ.repository.UserCommonRepository;
import com.mine.firstIJ.repository.entity.UserCommon;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.List;

public class CommonUserServiceTest {

    private UserCommonRepository userCommonRepository = Mockito.mock(UserCommonRepository.class);
    private UserCommonService userCommonService = new UserCommonService();

    public void insertUserTest() {
        List<UserCommon> testResultList = userCommonService.getAllUsers();
        Assertions.assertNotNull(testResultList);
    }
}
