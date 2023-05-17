package net.craftergames.projects.repositories;

import java.util.List;

public interface IRepositoryProjectWrapper<T, C, K> {

    List<T> read(C project);

    T readById(C project, K id);

    T create(C project, T entity);

}
