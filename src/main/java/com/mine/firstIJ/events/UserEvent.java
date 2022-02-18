package com.mine.firstIJ.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserEvent extends BaseEvent {
    private String firstName;
    private String lastName;
    private String email;
}
