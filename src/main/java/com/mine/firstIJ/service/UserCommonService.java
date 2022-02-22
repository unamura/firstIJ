package com.mine.firstIJ.service;

import com.mine.firstIJ.events.UserEvent;
import com.mine.firstIJ.events.UserIdEvent;
import com.mine.firstIJ.repository.UserCommonRepository;
import com.mine.firstIJ.repository.entity.UserCommon;
import com.mine.firstIJ.repository.entity.UserCommonId;
import com.mine.firstIJ.security.PasswordHashing;
import com.mine.firstIJ.util.UserCommonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserCommonService {
    private UserCommonConverter userCommonConverter = new UserCommonConverter();
    private PasswordHashing passwordHashing = new PasswordHashing();
    @Autowired
    private UserCommonRepository userCommonRepository;

    public List<UserCommon> getAllUsers() {
        List<UserCommon> userCommons = userCommonRepository.findAll();
        return userCommons;
    }

    public void insertNewUser(UserEvent userEvent) throws IllegalArgumentException {
        List<UserCommon> commonUser = userCommonRepository.findTopByOrderByIdDesc();
        List<UserCommon> commonUsernames = userCommonRepository.findByUsername(userEvent.getUsername());
        if (userEvent != null && commonUsernames.isEmpty()) {
            String uniqueCommonUserId = UUID.randomUUID().toString();
            String commonUserId = uniqueCommonUserId;
            String commonUserPassword = passwordHashing.encryptPassword(userEvent.getPassword());
            userCommonRepository.save(userCommonConverter.eventToUserCommonEntity(userEvent, commonUserId, commonUserPassword));
        } else if (!commonUsernames.isEmpty()) {
            throw new IllegalArgumentException("Username già utilizzata, sceglierne un'altra ");
        }
    }

    public void updateUser(UserEvent userEvent) {
        List<UserCommon> commonUsers = userCommonRepository.findByUsername(userEvent.getUsername());
        if (!commonUsers.isEmpty()) {
            UserCommon commonUser = commonUsers.get(0);
            String commonUserId = commonUser.getId();
            String commonUserPassword = commonUser.getPasswordEncrypted();
            Boolean isCorrectPassword = passwordHashing.verifyEncryptedPassword(commonUser.getPasswordEncrypted(), userEvent.getPassword());
            if (isCorrectPassword) {
                userCommonRepository.save(userCommonConverter.eventToUserCommonEntity(userEvent, commonUserId, commonUser.getPasswordEncrypted()));
            } else if (isCorrectPassword == false) {
                throw new IllegalArgumentException("Password non corretta ");
            }
        }
    }

    public void deleteUser(UserIdEvent userId) {
        UserCommonId userCommonId = new UserCommonId();
        if (userId != null) {
            userCommonId.setId(userId.getUserId());
            userCommonId.setUsername(userId.getUsername());
        }
        userCommonRepository.deleteById(userCommonId);
    }
}
