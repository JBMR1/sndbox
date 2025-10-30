package com.desserabit.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desserabit.app.model.Entity;
import com.desserabit.app.persistence.EntityDAO;
import com.desserabit.app.persistence.EntityFileDAO;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;

@RestController
@RequestMapping("/entities")
@CrossOrigin(origins = "*")
public class EntityController {
    // the entitty data access object that holds our entities
    private EntityDAO entityDAO;

    //GET
    @GetMapping("/{id}")
    public Entity getEntity(@PathVariable int id){
        try{
            return entityDAO.getEntity(id);
        }
        catch(IOException ex){
            System.out.println(ex.toString());
            System.out.println("Could not find Entity of" + id + ". Does it exist?");
        }
        return null;
       
    }

    //POST
    @PostMapping
    public Entity saveEntity(@RequestBody Entity entity){
        try{
            return entityDAO.saveEntity(entity);
        }
        catch(IOException ex){
            System.out.println(ex.toString());
            System.out.println("Could not save Entity");
        }
        return null;
    }

    @DeleteMapping
    public void  deleteEntity(@PathVariable int id){
        try{
             entityDAO.deleteEntity(id);
        }
        catch(IOException ex){
            System.out.println(ex.toString());
            System.out.println("Could not delete Entity");
        }
    }

}
