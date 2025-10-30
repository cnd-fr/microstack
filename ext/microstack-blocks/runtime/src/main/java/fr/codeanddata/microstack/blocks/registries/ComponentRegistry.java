package fr.codeanddata.microstack.blocks.registries;

import fr.codeanddata.microstack.blocks.ViewComponent;
import fr.codeanddata.microstack.blocks.loaders.CdiComponentLoader;
import fr.codeanddata.microstack.core.AbstractBeanRegistry;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ComponentRegistry extends AbstractBeanRegistry<ViewComponent> {

  @Inject
  CdiComponentLoader cdiComponentLoader;

  @Override
  public void load() {
    cdiComponentLoader.load(this);
  }
}
