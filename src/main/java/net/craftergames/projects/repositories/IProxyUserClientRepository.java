package net.craftergames.projects.repositories;

import net.craftergames.projects.clients.ProxyUserClient;

import java.util.List;

public interface IProxyUserClientRepository extends IRepository<ProxyUserClient, String, String> {

    @Override
    List<ProxyUserClient> read();

    @Override
    ProxyUserClient readById(String id);

    ProxyUserClient readByName(String name);

    @Override
    ProxyUserClient create(ProxyUserClient entity);

}
