package fr.codeanddata.microstack.blocks.it;

import fr.codeanddata.microstack.blocks.models.ViewDefinition;
import fr.codeanddata.microstack.blocks.ViewService;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;
import java.util.Map;

@Path("/microstack-blocks")
@ApplicationScoped
public class MicrostackBlocksResource {

  @Inject
  ViewService viewService;

  @GET
  public Uni<String> helloWorld() {
    return viewService.render(ViewDefinition.builder()
      .component("core/raw")
      .contextMapping(Map.of("content", "static"))
      .dataSources(Map.of("static", Map.of("content", "hello")))
      .slots(Map.of(
        "start", List.of(ViewDefinition.builder()
          .component("core/raw")
          .contextMapping(Map.of("content", "static"))
          .dataSources(Map.of("static", Map.of("content", "start")))
          .build()),
        "end", List.of(ViewDefinition.builder()
          .component("core/raw")
          .contextMapping(Map.of("content", "static"))
          .dataSources(Map.of("static", Map.of("content", "end")))
          .build())))
      .build());
  }
}
