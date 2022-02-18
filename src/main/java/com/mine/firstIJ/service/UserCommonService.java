package com.mine.firstIJ.service;

import com.mine.firstIJ.events.BaseEvent;
import com.mine.firstIJ.events.UserEvent;
import com.mine.firstIJ.repository.UserCommonRepository;
import com.mine.firstIJ.repository.entity.UserCommon;
import com.mine.firstIJ.util.UserCommonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCommonService {
    private UserCommonConverter userCommonConverter = new UserCommonConverter();
    @Autowired
    private UserCommonRepository userCommonRepository;

    public List<UserCommon> getAllUsers() {
        List<UserCommon> userCommons = userCommonRepository.findAll();
        return userCommons;
    }

    public void insertNewUser(UserEvent userEvent) {
        List<UserCommon> commonUser = userCommonRepository.findTopByOrderByIdDesc();
        if (userEvent != null) {
            Integer commonUserId = 1;
            if (!commonUser.isEmpty()) {
                commonUserId = commonUser.get(0).getId() + 1;
            }
            userCommonRepository.save(userCommonConverter.eventToUserCommonEntity(userEvent, commonUserId));
        }
    }

    public void updateUser(UserEvent userEvent) {
        List<UserCommon> commonUsers = userCommonRepository.findByUsername(userEvent.getUsername());
        if (!commonUsers.isEmpty()) {
            UserCommon commonUser = commonUsers.get(0);
            if (commonUser != null) {
                Integer commonUserId = commonUser.getId();
                userCommonRepository.save(userCommonConverter.eventToUserCommonEntity(userEvent, commonUserId));
            }
        }
    }
}
