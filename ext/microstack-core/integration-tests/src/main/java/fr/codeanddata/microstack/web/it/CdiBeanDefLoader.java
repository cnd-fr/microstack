package fr.codeanddata.microstack.web.it;

import fr.codeanddata.microstack.core.BeanRegistry;
import fr.codeanddata.microstack.core.BeanUtils;
import fr.codeanddata.microstack.core.RegistryLoader;
import io.quarkus.arc.All;
import io.quarkus.arc.Unremovable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;


@ApplicationScoped
public class CdiBeanDefLoader implements RegistryLoader<IBeanDef> {

  @Inject
  @Any
  Instance<IBeanDef> instance;

  @Override
  public void load(BeanRegistry<IBeanDef> registry) {
    for (IBeanDef beanDef : instance) {
      registry.register(BeanUtils.getIdentifier(beanDef), beanDef);
    }
  }
}
