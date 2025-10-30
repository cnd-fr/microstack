package fr.codeanddata.microstack.blocks;

import fr.codeanddata.microstack.blocks.models.ViewDefinition;
import fr.codeanddata.microstack.blocks.registries.DefinitionRegistry;
import fr.codeanddata.microstack.web.routing.RouteHandler;
import io.smallrye.mutiny.Uni;
import io.vertx.ext.web.RoutingContext;
import jakarta.inject.Inject;

import java.util.Map;

public abstract class ViewDefinitionHandler implements RouteHandler {

  @Inject
  DefinitionRegistry registry;

  @Inject
  ViewService viewService;

  protected ViewDefinition getViewDefinition(Map<String, String> params) {
    final String viewDefinitionName = params.getOrDefault("view", null);
    if (viewDefinitionName != null) {
      return registry.get(viewDefinitionName);
    } else {
      return null;
    }
  }

  @Override
  public Uni<Void> handle(RoutingContext routingContext, Map<String, String> params) {
    final ViewDefinition viewDefinition = getViewDefinition(params);
    if (viewDefinition != null) {
      return Uni.createFrom().voidItem()
        .chain(x -> viewService.render(viewDefinition))
        .invoke(view -> routingContext.response().send(view))
        .replaceWithVoid();
    } else {
      return Uni.createFrom().voidItem()
          .invoke(x -> routingContext.next());
    }
  }
}
