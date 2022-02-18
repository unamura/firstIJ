package com.mine.firstIJ.util;

import com.mine.firstIJ.events.UserEvent;
import com.mine.firstIJ.repository.entity.UserCommon;

public class UserCommonConverter {
    public UserCommon eventToUserCommonEntity(UserEvent userEvent, Integer commonUserId) {
        UserCommon result = new UserCommon();
        if (userEvent != null) {
            result.setId(commonUserId);
            result.setUserEmail(userEvent.getEmail());
            result.setFirstName(userEvent.getFirstName());
            result.setLastName(userEvent.getLastName());
            result.setUsername(userEvent.getUsername());
        }
        return result;
    }
}
