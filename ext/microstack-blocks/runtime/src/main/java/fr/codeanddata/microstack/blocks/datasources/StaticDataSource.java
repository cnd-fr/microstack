package fr.codeanddata.microstack.blocks.datasources;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.codeanddata.microstack.blocks.ViewDataSource;
import io.quarkus.qute.TemplateExtension;
import io.smallrye.common.annotation.Identifier;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Map;
import java.util.function.Function;

@Identifier("static")
@ApplicationScoped
public class StaticDataSource implements ViewDataSource {

  @Inject
  ObjectMapper objectMapper;

  @Override
  public Uni<Function<String, Uni<?>>> load(Object params) {
    try {
      final Map<String, Object> map = objectMapper.convertValue(params, new TypeReference<>() {
      });
      return Uni.createFrom().item(key -> Uni.createFrom().item(map.getOrDefault(key, null)));

    } catch (Exception e) {
      return Uni.createFrom().failure(e);
    }
  }
}
