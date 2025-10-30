package fr.codeanddata.microstack.blocks.loaders;

import fr.codeanddata.microstack.blocks.ViewComponent;
import fr.codeanddata.microstack.core.BeanRegistry;
import fr.codeanddata.microstack.core.BeanUtils;
import fr.codeanddata.microstack.core.RegistryLoader;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

@ApplicationScoped
public class CdiComponentLoader implements RegistryLoader<ViewComponent> {

  @Inject
  @Any
  Instance<ViewComponent> components;

  @Override
  public void load(BeanRegistry<ViewComponent> registry) {
    for (ViewComponent component : components) {
      registry.register(BeanUtils.getIdentifier(component), component);
    }
  }
}
