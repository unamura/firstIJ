package com.mine.firstIJ.controller;

import com.mine.firstIJ.events.UserEvent;
import com.mine.firstIJ.repository.entity.UserCommon;
import com.mine.firstIJ.service.UserCommonService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/welcome")
@ApiResponses(value = {
        @ApiResponse(responseCode = "500", description = "Errore nell'esecuzione del metodo", content = {
                @Content(mediaType = "application/json")})
})
@Slf4j
public class UserCommonController {
    @Autowired
    private UserCommonService userCommonService;

    @GetMapping(value = "/get_all", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<UserCommon> getAllUserFromDb() {
        try {
            return userCommonService.getAllUsers();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @PostMapping(value = "/insert_user", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void insertNewUser(@RequestBody UserEvent userEvent) {
        try {
            userCommonService.insertNewUser(userEvent);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @PostMapping(value = "/update_user", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void updateUser(@RequestBody UserEvent userEvent) {
        try {
            userCommonService.updateUser(userEvent);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
