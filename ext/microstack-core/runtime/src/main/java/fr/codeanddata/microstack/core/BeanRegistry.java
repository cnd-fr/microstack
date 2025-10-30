package fr.codeanddata.microstack.core;

public interface BeanRegistry<T> {
  void register(String identifier, T instance);
  T get(String identifier);
  boolean exists(String identifier);

  default void load() {

  }
}
