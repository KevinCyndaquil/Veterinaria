package application.models.database.interfaces;

public interface Save<Entity> {
    Integer save(Entity entity);
    Iterable<Integer> saveAll(Iterable<Entity> entities);
}
