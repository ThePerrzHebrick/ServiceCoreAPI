package net.craftergames.projects.databases;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ProjectMongoDatabase {

    private final Integer port;
    private MongoClient client;
    private String databaseName;
    private final String hostName;

    public ProjectMongoDatabase(String hostName, Integer port) {
        this.hostName = hostName;
        this.port = port;
    }

    public void connect() {
        this.client = MongoClients.create(new ConnectionString("mongodb://" + hostName + ":" + port));
    }

    public Boolean isConnected() {
        if (this.client != null) {
            return true;
        } else {
            return false;
        }
    }


    public MongoDatabase getProject(String project) {
        return client.getDatabase(project + "_project");
    }

    public String getHostName() {
        return hostName;
    }

    public Integer getPort() {
        return port;
    }
}
