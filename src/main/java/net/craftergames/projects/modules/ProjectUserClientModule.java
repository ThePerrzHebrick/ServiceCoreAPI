package net.craftergames.projects.modules;

import com.google.inject.AbstractModule;
import net.craftergames.projects.repositories.IProjectUserClientRepository;
import net.craftergames.projects.wrapper.ProjectUserWrapper;

public class ProjectUserClientModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IProjectUserClientRepository.class).to(ProjectUserWrapper.class);
    }

}
