package com.desserabit.app.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.http.HttpResponse;
import java.nio.channels.Channel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Io;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desserabit.app.model.SSHSession;
import com.desserabit.app.service.SSHSessionService;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSchException;

@RestController
@RequestMapping("/ssh")

// remember, the SSH session controller is a single instance,
/// it can link to many SSH's from SSH service, its a one-to-many relationship
//
public class SSHSessionController{

    @Autowired
    private SSHSessionService sshService;

    private static ChannelShell channel;

    private String sessionId;

    String[] sessionIds;

    // conenct via ssh
    @GetMapping("/connect")
    public ResponseEntity<String> connect(){
        //open arbitrary session
        sessionId = sshService.openSession();

        // printing - TODO: replace with LOGGER 
        SSHSession sshsession = sshService.getSession(sessionId);
        com.jcraft.jsch.Session ssh = sshsession.getSession();
        System.out.println("*** IS CONNECTED *** : "+ ssh.isConnected());
        System.out.println("SSHSessionService Info:");
        System.out.println("    session id: "+ sessionId);
        System.out.println("    toString(): " + sshService.toString());
        System.out.println("SSHSession : ");
        System.out.println("    toString(): "+ sshsession.toString());
        System.out.println("Jsch : ");
        System.out.println("    toString(): "+ ssh.toString());
        System.out.println("    Host: "+ ssh.getHost());
        System.out.println("    user: "+ ssh.getUserName());
        System.out.println("    Timeout: "+ ssh.getTimeout());
        System.out.println("Host Key: "+ ssh.getHostKey().hashCode());
        System.out.println("    Host: "+ ssh.getHostKey().getHost());
        System.out.println("    Marker: "+ ssh.getHostKey().getMarker());
        System.out.println("    Type: "+ ssh.getHostKey().getType());



        //
        return ResponseEntity.ok(sessionId);
    }

    // recieve terminal output 
    @GetMapping("/connect/terminal")
    public void output(){
        SSHSession sch = sshService.getSession(sessionId);
        try{
            ChannelShell cShell;
            cShell = (ChannelShell) sch.getSession().openChannel("shell");
            cShell.setPty(true);
            cShell.setPtyType("xterm-color");
            cShell.connect();
            cShell.setInputStream(System.in);
            cShell.setOutputStream(System.out);


            OutputStream out;
            boolean wait = true;
            while(wait){
                wait=false;
                try{
                    System.in.readAllBytes();
                } catch (IOException ioException){

                }

                wait = true;
                System.out.flush();

                try{
               System.out.println(cShell.getOutputStream()); 

                } catch (IOException ioException){

                }
            }

            
        } catch (JSchException exception){

        }


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