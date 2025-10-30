package fr.codeanddata.microstack.core;

public interface RegistryLoader<T> {
  void load(BeanRegistry<T> registry);
}
