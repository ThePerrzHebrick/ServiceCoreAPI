package net.craftergames.projects.repositories;

import net.craftergames.projects.clients.ProjectUserClient;

import java.util.List;

public interface IProjectUserClientRepository extends IRepositoryProjectWrapper<ProjectUserClient, String, String> {

    List<ProjectUserClient> read(String project);

    ProjectUserClient readById(String project, String id);

    ProjectUserClient readByName(String project, String name);

    ProjectUserClient create(String project, ProjectUserClient entity);

}
