package fr.codeanddata.microstack.web.it;

import io.smallrye.common.annotation.Identifier;
import jakarta.enterprise.context.ApplicationScoped;

@Identifier("hello 2")
@ApplicationScoped
public class Hello2 implements IBeanDef {
  @Override
  public String hello() {
    return "hello 2";
  }
}
