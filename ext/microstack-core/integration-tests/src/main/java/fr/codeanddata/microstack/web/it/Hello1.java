package fr.codeanddata.microstack.web.it;

import io.smallrye.common.annotation.Identifier;
import jakarta.enterprise.context.ApplicationScoped;

@Identifier("hello 1")
@ApplicationScoped
public class Hello1 implements IBeanDef {

  @Override
  public String hello() {
    return "hello 1";
  }
}
