package fr.codeanddata.microstack.blocks.loaders;

import fr.codeanddata.microstack.blocks.ViewDefinitionProvider;
import fr.codeanddata.microstack.blocks.models.ViewDefinition;
import fr.codeanddata.microstack.core.BeanRegistry;
import fr.codeanddata.microstack.core.BeanUtils;
import fr.codeanddata.microstack.core.RegistryLoader;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

@ApplicationScoped
public class CdiDefinitionLoader implements RegistryLoader<ViewDefinition> {

  @Inject
  @Any
  Instance<ViewDefinitionProvider> providers;

  @Override
  public void load(BeanRegistry<ViewDefinition> registry) {
    for (ViewDefinitionProvider provider : providers) {
      registry.register(BeanUtils.getIdentifier(provider), provider.toViewDefinition());
    }
  }
}
