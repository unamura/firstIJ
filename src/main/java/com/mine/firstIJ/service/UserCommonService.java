package com.mine.firstIJ.service;

import com.mine.firstIJ.events.BaseEvent;
import com.mine.firstIJ.events.UserEvent;
import com.mine.firstIJ.events.UserIdEvent;
import com.mine.firstIJ.repository.UserCommonRepository;
import com.mine.firstIJ.repository.entity.UserCommon;
import com.mine.firstIJ.repository.entity.UserCommonId;
import com.mine.firstIJ.security.JwtService;
import com.mine.firstIJ.util.UserCommonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserCommonService {
    private UserCommonConverter userCommonConverter = new UserCommonConverter();
    @Autowired
    private UserCommonRepository userCommonRepository;
    @Autowired
    private JwtService jwtService;

    public List<UserCommon> getAllUsers() {
        List<UserCommon> userCommons = userCommonRepository.findAll();
        return userCommons;
    }

    public void insertNewUser(UserEvent userEvent) {
        List<UserCommon> commonUser = userCommonRepository.findTopByOrderByIdDesc();
        List<UserCommon> commonUsernames = userCommonRepository.findByUsername(userEvent.getUsername());
        if (userEvent != null && commonUsernames.isEmpty()) {
            String uniqueCommonUserId = UUID.randomUUID().toString();
            String commonUserId = uniqueCommonUserId;
            String commonUserPassword = jwtService.generateToken(userEvent.getPassword());
            userCommonRepository.save(userCommonConverter.eventToUserCommonEntity(userEvent, commonUserId, commonUserPassword));
        }
    }

    public void updateUser(UserEvent userEvent) {
        List<UserCommon> commonUsers = userCommonRepository.findByUsername(userEvent.getUsername());
        if (!commonUsers.isEmpty()) {
            UserCommon commonUser = commonUsers.get(0);
            if (commonUser != null) {
                String commonUserId = commonUser.getId();
                String commonUserPassword = commonUser.getPasswordToken();
                userCommonRepository.save(userCommonConverter.eventToUserCommonEntity(userEvent, commonUserId, commonUserPassword));
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
