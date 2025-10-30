// import persistence tier


package com.desserabit.app.persistence;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Io;
import org.springframework.stereotype.Component;

// import entities.

import com.desserabit.app.model.Entity;
@Component
public class EntityFileDAO implements EntityDAO {

    // local cache of Need Objects

    Map<Integer,Entity> entities;


    // provides conversion between entity objects and json text format written to file
    private ObjectMapper objectMapper;

    // static because we want this to be a class-wide variable -
    // not  something that gets instantiated with each instace
    private static int nextId; 
    
    private String filename;

    @Autowired
    public EntityFileDAO(@Value("${entities.file}") String filename, ObjectMapper objectMapper) throws IOException{
        
        this.filename = filename;
        this.objectMapper = objectMapper;
        load(); //loads entities from file
    }

    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    private boolean load() throws IOException{
        entities = new TreeMap<Integer,Entity>();
        nextId = 0;
        // Deserializes the JSON objects from the file into an array of needs
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        Entity[] EntityArray = objectMapper.readValue(new File(filename),Entity[].class);

        // Add each Need to the tree map and keep track of the greatest id
        for (Entity entity : EntityArray) {
            entities.put(entity.getId(),entity);
            if (entity.getId() > nextId)
                nextId = entity.getId();
        }
        // Make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    
    private boolean save() throws IOException {
        Entity[] EntityArray = getEntities();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),EntityArray);
        return true;
    }


    @Override
    public Entity[] getEntities() throws IOException {
        //new Entity[0] works here over new Entity[entities.size()] bc:
        // toarray will create  correctly sized array internally anyway 
        return entities.values().toArray(new Entity[0]);
    }

    @Override
    public  Entity getEntity(int id) throws IOException{
        return entities.get(id);
    }

    @Override
    public Entity createEntity(Entity entity) throws IOException{
        entities.put(entity.getId(), entity);
        save();
        return entity;
    }

    @Override 
    public void deleteEntity(int id) throws IOException{
        entities.remove(id);
        save();
    }
}