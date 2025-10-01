package fr.codeanddata.microstack.web.it;

import fr.codeanddata.microstack.web.routing.RouteHandler;
import io.smallrye.common.annotation.Identifier;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.ApplicationScoped;

@Identifier("hello-name-re-group")
@ApplicationScoped
public class HelloNameReGroupRouteHandler implements RouteHandler {
  @Override
  public void handle(RoutingContext ctx) {
    ctx.response().send("hello " + ctx.pathParam("name"));
  }
}
