package com.mine.firstIJ.events;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class BaseEvent implements Serializable {
    @NotBlank
    private String username;
}
