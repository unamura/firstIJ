package com.mine.firstIJ.events;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserIdEvent extends BaseEvent {
    @NotBlank
    private String userId;
}
