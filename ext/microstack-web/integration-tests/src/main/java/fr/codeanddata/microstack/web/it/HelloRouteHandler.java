package fr.codeanddata.microstack.web.it;

import fr.codeanddata.microstack.web.routing.RouteHandler;
import io.smallrye.common.annotation.Identifier;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.ApplicationScoped;

@Identifier("hello")
@ApplicationScoped
public class HelloRouteHandler implements RouteHandler {
  @Override
  public void handle(RoutingContext ctx) {
    ctx.response().send("hello");
  }
}
