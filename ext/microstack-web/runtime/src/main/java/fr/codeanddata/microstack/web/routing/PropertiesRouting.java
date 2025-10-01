package fr.codeanddata.microstack.web.routing;

import io.quarkus.arc.All;
import io.smallrye.common.annotation.Identifier;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class PropertiesRouting {
  @Inject
  RoutingConfig config;

  @Inject
  @All
  List<RouteHandler> handlers;

  void handleRouter(@Observes Router router) {
    final Map<String, RouteHandler> handlersMap = new HashMap<>();
    handlers.forEach(handler -> {
      final Identifier a = handler.getClass().getSuperclass().getAnnotation(Identifier.class);
      if (a != null) {
        handlersMap.put(a.value(), handler);
      }
    });

    final Map<String, RoutingConfig.RouteConfig> routeConfig = config.routing();
    for (final String handlerName: routeConfig.keySet()) {
      if (handlersMap.containsKey(handlerName)) {
        final RouteHandler handler = handlersMap.get(handlerName);
        final RoutingConfig.RouteConfig route = routeConfig.get(handlerName);
        final List<String> methods = route.methods().orElse(List.of("GET"));
        methods.forEach(method -> {
          final HttpMethod httpMethod = HttpMethod.valueOf(method);
          if (route.path().isPresent()) {
            router.route(httpMethod, route.path().get()).handler(handler::handle);
          } else if (route.regex().isPresent()) {
            router.routeWithRegex(httpMethod, route.regex().get()).handler(handler::handle);
          }
        });
      }
    }
  }
}
