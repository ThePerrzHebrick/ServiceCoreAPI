package net.craftergames.projects.databases;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class PacketMongoDatabase {

    private final Integer port;
    private MongoClient client;
    private String databaseName;
    private final String hostName;

    public PacketMongoDatabase(String hostName, Integer port) {
        this.hostName = hostName;
        this.port = port;
        this.databaseName = "packet_handler";
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


    public MongoDatabase getPacketHandler() {
        return client.getDatabase(databaseName);
    }

    public String getHostName() {
        return hostName;
    }

    public Integer getPort() {
        return port;
    }
}
