package net.craftergames.projects.modules;

import com.google.inject.AbstractModule;
import net.craftergames.projects.repositories.IProxyUserClientRepository;
import net.craftergames.projects.wrapper.ProxyUserWrapper;

public class ProxyUserClientModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IProxyUserClientRepository.class).to(ProxyUserWrapper.class);
    }

}