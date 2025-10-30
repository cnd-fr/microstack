package fr.codeanddata.microstack.blocks.loaders;

import fr.codeanddata.microstack.blocks.ViewDataSource;
import fr.codeanddata.microstack.core.BeanRegistry;
import fr.codeanddata.microstack.core.BeanUtils;
import fr.codeanddata.microstack.core.RegistryLoader;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

@ApplicationScoped
public class CdiDataSourceLoader implements RegistryLoader<ViewDataSource> {

  @Inject
  @Any
  Instance<ViewDataSource> dataSources;

  @Override
  public void load(BeanRegistry<ViewDataSource> registry) {
    for (ViewDataSource dataSource : dataSources) {
      registry.register(BeanUtils.getIdentifier(dataSource), dataSource);
    }
  }
}
