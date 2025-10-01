package fr.codeanddata.microstack.core;

import java.util.List;

public interface BeanProvider<T> {
  List<T> getBeans();
}
