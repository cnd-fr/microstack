package fr.codeanddata.microstack.core;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBeanRegistry<T> implements BeanRegistry<T> {

  Map<String, T> components = new HashMap<>();

  public void init(@Observes StartupEvent event) {
    load();
  }

  @Override
  public void register(String identifier, T instance) {
    if (components.containsKey(identifier)) {
      throw new RuntimeException("Duplicated component identifier : " + identifier);
    }

    components.put(identifier, instance);
  }

  @Override
  public T get(String identifier) {
    return components.getOrDefault(identifier, null);
  }

  @Override
  public boolean exists(String identifier) {
    return components.containsKey(identifier);
  }


}
