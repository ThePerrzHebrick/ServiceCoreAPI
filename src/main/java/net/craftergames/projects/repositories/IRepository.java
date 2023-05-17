package net.craftergames.projects.repositories;

import java.util.List;

public interface IRepository<T, C, K> {

    List<T> read();

    T readById(K id);

    T create(T entity);

}
