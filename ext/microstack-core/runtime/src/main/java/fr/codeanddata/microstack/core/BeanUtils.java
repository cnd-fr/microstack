package fr.codeanddata.microstack.core;

import io.smallrye.common.annotation.Identifier;

import java.util.Optional;

public class BeanUtils {

  public static String getIdentifier(Object o) {
    final Identifier identifier = Optional.ofNullable(o.getClass().getAnnotation(Identifier.class))
      .orElse(o.getClass().getSuperclass().getAnnotation(Identifier.class));
    return identifier != null ? identifier.value() : null;
  }
}
