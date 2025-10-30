package com.desserabit.app.persistence;
import com.desserabit.app.model.Entity;
import java.io.IOException;

public interface EntityDAO {
    
    // returns array of entities
    Entity[] getEntities() throws IOException;
    
    Entity getEntity(int id) throws IOException;

    Entity createEntity(Entity entity) throws IOException;

    void deleteEntity(int id) throws IOException;

}
