package com.desserabit.app.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Entity {
    private int id;
    private String name;


    public Entity(
        @JsonProperty("id") int id,
        @JsonProperty("name") String name){
            this.id=id;
            this.name=name;
    }

    public int getId(){
        return id;
    }

    public String geString(){
        return name;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }

}

