package com.mine.firstIJ.util;

import com.mine.firstIJ.events.UserEvent;
import com.mine.firstIJ.repository.entity.UserCommon;
import com.mine.firstIJ.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class UserCommonConverter {

    public UserCommon eventToUserCommonEntity(UserEvent userEvent, String commonUserId, String commonUserPassword) {
        UserCommon result = new UserCommon();
        if (userEvent != null) {
            if (commonUserPassword != null) {
                result.setId(commonUserId);
                result.setUserEmail(userEvent.getEmail());
                result.setFirstName(userEvent.getFirstName());
                result.setLastName(userEvent.getLastName());
                result.setUsername(userEvent.getUsername());
                result.setPasswordToken(commonUserPassword);
            }
        }
        return result;
    }
}
