package com.desserabit.app.controller;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desserabit.app.model.SSHSession;
import com.desserabit.app.service.SSHSessionService;

@RestController
@RequestMapping("/ssh")
public class SSHSessionController{

    @Autowired
    private SSHSessionService sshService;


    // conenct via ssh
    @PostMapping("/connect")
    public void connect(){
        sshService.openSession();
    }

    // recieve terminal output 
    @GetMapping
    public void output(){

    }

    // send commands
    @PostMapping
    public void input(){

    }

    // close the session
    @DeleteMapping
    public void close(){

    }


}