package com.desserabit.app.model;

import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener.Session;

import com.jcraft.jsch.*;

public class SSHSession{


    
    private static final String REMOTE_HOST = "bandit.labs.overthewire.org";
    private static final String USERNAME = "bandit0";
    private static final String PASSWORD = "bandit0";
    private static final int REMOTE_PORT = 22;
    private static final int SESSION_TIMEOUT = 10000;
    private static final int CHANNEL_TIMEOW = 5000;


    public SSHSession(){
        com.jcraft.jsch.Session jschSession = null;

        JSch jsch = new JSch();
        try{
            //Instantiates the Session object with given username, host and port. 
            //Note that the TCP connection must not be established until Session#connect().
            jschSession = jsch.getSession(USERNAME, REMOTE_HOST, REMOTE_PORT);
            jschSession.setPassword(PASSWORD);
            jschSession.connect(SESSION_TIMEOUT);

        }
        catch(JSchException exception){
            

        }

    }

}
