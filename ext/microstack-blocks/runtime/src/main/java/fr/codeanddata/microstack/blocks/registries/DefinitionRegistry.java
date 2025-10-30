package fr.codeanddata.microstack.blocks.registries;

import fr.codeanddata.microstack.blocks.ViewComponent;
import fr.codeanddata.microstack.blocks.ViewDefinitionProvider;
import fr.codeanddata.microstack.blocks.loaders.CdiComponentLoader;
import fr.codeanddata.microstack.blocks.models.ViewDefinition;
import fr.codeanddata.microstack.core.AbstractBeanRegistry;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DefinitionRegistry extends AbstractBeanRegistry<ViewDefinition> {

  @Override
  public void load() {

  }
}
