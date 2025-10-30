package fr.codeanddata.microstack.blocks.registries;

import fr.codeanddata.microstack.blocks.ViewDataSource;
import fr.codeanddata.microstack.blocks.loaders.CdiDataSourceLoader;
import fr.codeanddata.microstack.core.AbstractBeanRegistry;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DataSourceRegistry extends AbstractBeanRegistry<ViewDataSource> {

  @Inject
  CdiDataSourceLoader cdiDataSourceLoader;

  @Override
  public void load() {
    cdiDataSourceLoader.load(this);
  }
}
