package fr.codeanddata.microstack.web.routing;

import io.vertx.ext.web.RoutingContext;

public interface RouteHandler {
  void handle(RoutingContext routingContext);
}
