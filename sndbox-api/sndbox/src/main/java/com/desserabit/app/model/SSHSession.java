package com.desserabit.app.model;

import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener.Session;

import com.jcraft.jsch.*;

public class SSHSession{

    private String sessionId;
    private com.jcraft.jsch.Session jschSession;
    private Channel channel;


    public SSHSession(String sessionId, com.jcraft.jsch.Session jschSession, Channel channel){
        this.sessionId = sessionId;
        this.jschSession = jschSession;
        this.channel = channel;
    }

    public void setChannel(Channel channel){
        this.channel = channel;
    }

    public com.jcraft.jsch.Session getSession(){
        return this.jschSession;
    }
}
