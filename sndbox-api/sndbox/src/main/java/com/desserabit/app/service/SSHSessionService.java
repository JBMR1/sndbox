package com.desserabit.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desserabit.app.model.SSHSession;

@Service
public class SSHSessionService {
    Map<String, SSHSession> sshSessions;


    public SSHSessionService(){

    }

    // service shuold be able to : open ssh sessions
    public void openSession(){
        sshSessions.put("sample", new SSHSession());
    }




}
