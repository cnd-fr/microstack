package fr.codeanddata.microstack.web.it;

import fr.codeanddata.microstack.web.routing.RouteHandler;
import io.smallrye.common.annotation.Identifier;
import io.smallrye.mutiny.Uni;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;
import java.util.Map;

@Identifier("hello")
@ApplicationScoped
public class HelloRouteHandler implements RouteHandler {

  @Override
  public Uni<Void> handle(RoutingContext ctx, Map<String, String> params) {
    return Uni.createFrom().nullItem()
      .onItem().delayIt().by(Duration.ofMillis(1))
      .call(x -> Uni.createFrom().nullItem()
        .invoke(() -> ctx.response().send("hello")))
      .replaceWithVoid();
  }
}
