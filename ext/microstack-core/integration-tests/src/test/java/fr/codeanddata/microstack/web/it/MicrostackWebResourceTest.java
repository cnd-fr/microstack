package fr.codeanddata.microstack.web.it;


import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class MicrostackWebResourceTest {

  @Inject
  BeanDefRegistry registry;

  @Test
  public void testHelloEndpoint() {
    Assertions.assertNotNull(registry);
    Assertions.assertNotNull(registry.get("hello 1"));
    Assertions.assertNotNull(registry.get("hello 2"));
  }
}
