package application.models.database.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Find<Entity> {
    Optional<Entity> findOne(Entity entity);
    Entity findByID(Integer id);
    Entity findByID(Integer id, boolean isHidden) ;
    Iterable<Entity> findAllById(Iterable<Integer> ids);
    Iterable<Entity> findAll();
}
