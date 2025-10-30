package fr.codeanddata.microstack.web.it;

import fr.codeanddata.microstack.core.AbstractBeanRegistry;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BeanDefRegistry extends AbstractBeanRegistry<IBeanDef> {

  @Inject
  CdiBeanDefLoader loader;

  @Override
  public void load() {
    loader.load(this);
  }
}
