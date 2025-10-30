package fr.codeanddata.microstack.web.routing;

import io.smallrye.mutiny.Uni;
import io.vertx.ext.web.RoutingContext;

import java.util.Map;

public interface RouteHandler {
  Uni<Void> handle(RoutingContext routingContext, Map<String, String> params);
}
