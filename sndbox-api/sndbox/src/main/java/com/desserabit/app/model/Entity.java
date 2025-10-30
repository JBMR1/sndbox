package com.desserabit.app.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Entity {
    private int id;
    private String name;


    public Entity{
        @JsonProperty("id") int id,
        @JsonProperty("name") String name
    }
}
