package net.craftergames.projects.wrapper;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.craftergames.projects.ServiceCoreAPI;
import net.craftergames.projects.clients.ProjectUserClient;
import net.craftergames.projects.repositories.IProjectUserClientRepository;
import net.craftergames.projects.repositories.IProxyUserClientRepository;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class ProjectUserWrapper implements IProjectUserClientRepository {

    private static Gson gson;

    @Inject
    public ProjectUserWrapper(Gson gson) {
        ProjectUserWrapper.gson = gson;
    }

    public List<ProjectUserClient> read(String project) {
        List<ProjectUserClient> freebuildPlayers = new ArrayList<>();
        for (Document document : new ServiceCoreAPI().getProjectMongoDataBase().getProject(project).getCollection("players").find()) {
            ProjectUserClient projectUserClient = gson.fromJson(document.toJson(), ProjectUserClient.class);
            freebuildPlayers.add(projectUserClient);
        }

        return freebuildPlayers;
    }

    public ProjectUserClient create(String project, ProjectUserClient projectUserClient) {
        Document document = gson.fromJson(gson.toJson(projectUserClient), Document.class);
        if (new ServiceCoreAPI().getProjectMongoDataBase().getProject(project).getCollection("players").find(new Document("playerUniqueId", projectUserClient.getPlayerUniqueID())).first() == null) {
            new ServiceCoreAPI().getProjectMongoDataBase().getProject(project).getCollection("players").insertOne(document);
        }
        return projectUserClient;
    }

    public ProjectUserClient readById(String project, String id) {
        ProjectUserClient projectUserClient = null;
        Document document = new ServiceCoreAPI().getProjectMongoDataBase().getProject(project).getCollection("players").find(new Document("playerUniqueId", id)).first();
        if (document != null) {
            projectUserClient = gson.fromJson(document.toJson(), ProjectUserClient.class);
        }
        return projectUserClient;
    }

    public ProjectUserClient readByName(String project, String name) {
        ProjectUserClient projectUserClient = null;
        Document document = new ServiceCoreAPI().getProjectMongoDataBase().getProject(project).getCollection("players").find(new Document("playerLowerName", name.toLowerCase())).first();
        if (document != null) {
            projectUserClient = gson.fromJson(document.toJson(), ProjectUserClient.class);
        }
        return projectUserClient;
    }

}
