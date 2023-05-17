package net.craftergames.projects.wrapper;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.craftergames.projects.ServiceCoreAPI;
import net.craftergames.projects.clients.ProxyUserClient;
import net.craftergames.projects.clients.ProxyUserClient;
import net.craftergames.projects.repositories.IProxyUserClientRepository;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class ProxyUserWrapper implements IProxyUserClientRepository {

    private static Gson gson;

    @Inject
    public ProxyUserWrapper(Gson gson) {
        ProxyUserWrapper.gson = gson;
    }

    public List<ProxyUserClient> read() {
        List<ProxyUserClient> freebuildPlayers = new ArrayList<>();
        for (Document document : new ServiceCoreAPI().getProxyMongoDataBase().getProxyHandler().getCollection("players").find()) {
            ProxyUserClient ProxyUserClient = gson.fromJson(document.toJson(), ProxyUserClient.class);
            freebuildPlayers.add(ProxyUserClient);
        }

        return freebuildPlayers;
    }


    public ProxyUserClient create(ProxyUserClient proxyUserClient) {
        Document document = gson.fromJson(gson.toJson(proxyUserClient), Document.class);
        if (new ServiceCoreAPI().getProxyMongoDataBase().getProxyHandler().getCollection("players").find(new Document("playerUniqueId", proxyUserClient.getPlayerUniqueID())).first() == null) {
            new ServiceCoreAPI().getProxyMongoDataBase().getProxyHandler().getCollection("players").insertOne(document);
        }
        return proxyUserClient;
    }

    public ProxyUserClient readById(String id) {
        ProxyUserClient proxyUserClient = null;
        Document document = new ServiceCoreAPI().getProxyMongoDataBase().getProxyHandler().getCollection("players").find(new Document("playerUniqueId", id)).first();
        if (document != null) {
            proxyUserClient = gson.fromJson(document.toJson(), ProxyUserClient.class);
        }
        return proxyUserClient;
    }

    public ProxyUserClient readByName(String name) {
        ProxyUserClient proxyUserClient = null;
        Document document = new ServiceCoreAPI().getProxyMongoDataBase().getProxyHandler().getCollection("players").find(new Document("playerLowerName", name.toLowerCase())).first();
        if (document != null) {
            proxyUserClient = gson.fromJson(document.toJson(), ProxyUserClient.class);
        }
        return proxyUserClient;
    }

}
