package fr.codeanddata.microstack.web.it;

import fr.codeanddata.microstack.web.routing.RouteHandler;
import io.smallrye.common.annotation.Identifier;
import io.smallrye.mutiny.Uni;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Map;

@Identifier("hello-name")
@ApplicationScoped
public class HelloNameRouteHandler implements RouteHandler {
  @Override
  public Uni<Void> handle(RoutingContext ctx, Map<String, String> params) {
    return Uni.createFrom().voidItem()
      .invoke(x -> ctx.response().send("hello " + ctx.pathParam("name")));
  }
}
