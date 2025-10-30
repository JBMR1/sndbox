package com.desserabit.app.service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desserabit.app.model.SSHSession;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

@Service
public class SSHSessionService {


    ConcurrentHashMap<String, SSHSession> sshSessions;

    private static final String REMOTE_HOST = "bandit.labs.overthewire.org";
    private static final String USERNAME = "bandit0";
    private static final String PASSWORD = "bandit0";
    private static final int REMOTE_PORT = 2220;
    private static final int SESSION_TIMEOUT = 10000;
    private static final int CHANNEL_TIMEOUT = 5000;

    public SSHSessionService(){
        sshSessions = new ConcurrentHashMap<String, SSHSession>();
    }

    // service shuold be able to : open ssh sessions, 
    // returns sessionID
    public String openSession(){

        JSch jsch = new JSch();
        //Instantiates the Session object with given username, host and port. 
        //Note that the TCP connection must not be established until Session#connect().
        try{
            Session jschSession = jsch.getSession(USERNAME, REMOTE_HOST, REMOTE_PORT);
            jschSession.setPassword(PASSWORD);
            
            // accept unknwon hostkeys (like when ssh asks "are you sure u wanna coonnect?")
            // bypases the check
            jschSession.setConfig("StrictHostKeyChecking", "no"); 

            jschSession.connect(SESSION_TIMEOUT);

            String sessionId = UUID.randomUUID().toString();

            System.out.println("the possible session ID is "+ sessionId);

            SSHSession session = new SSHSession(sessionId, jschSession, null);

            session.setChannel(jschSession.openChannel("shell"));

            System.out.println(sessionId);

            sshSessions.put(sessionId,  session);

            return sessionId;

        } catch(JSchException exception){
            System.out.println(exception.toString());
            return null;
        }

    }

    public SSHSession getSession(String sessionId){
        return sshSessions.get(sessionId);

    }



}
