package com.mine.firstIJ.service;

import com.mine.firstIJ.events.UserEvent;
import com.mine.firstIJ.events.UserIdEvent;
import com.mine.firstIJ.repository.UserCommonRepository;
import com.mine.firstIJ.repository.entity.UserCommon;
import com.mine.firstIJ.security.PasswordHashing;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommonUserServiceTest {
    private UserCommonRepository userCommonRepository = Mockito.mock(UserCommonRepository.class);
    private PasswordHashing passwordHashing = Mockito.mock(PasswordHashing.class);
    private UserCommonService userCommonService = new UserCommonService(userCommonRepository, passwordHashing);

    public UserCommon newUser(String userId, String userEmail, String userUsername,
                              String userLastName, String userFirstName) {
        UserCommon result = new UserCommon();
        result.setId(userId);
        result.setUserEmail(userEmail);
        result.setUsername(userUsername);
        result.setLastName(userLastName);
        result.setFirstName(userFirstName);
        result.setPasswordEncrypted("ABCD");
        return result;
    }

    public UserEvent newEvent() {
        UserEvent result = new UserEvent();
        result.setEmail("maxrex@email.com");
        result.setUsername("maxrex");
        result.setLastName("Rex");
        result.setFirstName("Max");
        result.setPassword("PasswordTest@123");
        return result;
    }

    public List<UserCommon> listUserCommon() {
        List<UserCommon> result = new ArrayList<UserCommon>();
        UserCommon userCommonA = newUser("A", "A@example.com", "userA", "Verdi", "Luca");
        UserCommon userCommonB = newUser("B", "B@example.com", "userB", "Blu", "Andrea");
        result.add(userCommonA);
        result.add(userCommonB);
        return result;
    }

    @Test
    public void getAllUserTest() {
        Mockito.when(userCommonRepository.findAll()).thenReturn(listUserCommon());
        List<UserCommon> testResultList = userCommonService.getAllUsers();
        Assertions.assertNotNull(testResultList);
    }

    @Test
    public void insertNewUserTest() {
        Mockito.when(userCommonRepository.findByUsername(Mockito.anyString())).thenReturn(new ArrayList<UserCommon>());
        userCommonService.insertNewUser(newEvent());
        Mockito.verify(userCommonRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void updateUserTest() {
        Mockito.when(userCommonRepository.findByUsername(Mockito.anyString())).thenReturn(listUserCommon());
        Mockito.when(passwordHashing.verifyEncryptedPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
        userCommonService.updateUser(newEvent());
        Mockito.verify(userCommonRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void deleteUserTest() {
        UserIdEvent userIdEvent = new UserIdEvent();
        userCommonService.deleteUser(userIdEvent);
        Mockito.verify(userCommonRepository, Mockito.times(1)).deleteById(Mockito.any());
    }

    @Test
    public void insertNewUserExceptionTest() {
        UserEvent userEvent = newEvent();
        Mockito.when(userCommonRepository.findByUsername(Mockito.anyString())).thenReturn(listUserCommon());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userCommonService.insertNewUser(userEvent);
        });
        String expectedMessage = "Username già utilizzata, sceglierne un'altra ";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void updateUserExceptionTest() {
        UserEvent userEvent = newEvent();
        Mockito.when(userCommonRepository.findByUsername(Mockito.anyString())).thenReturn(listUserCommon());
        Mockito.when(passwordHashing.verifyEncryptedPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(false);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userCommonService.updateUser(userEvent);
        });
        String expectedMessage = "Password non corretta ";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
