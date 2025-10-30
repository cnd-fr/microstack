package fr.codeanddata.microstack.web.routing;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithParentName;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@ConfigMapping(prefix = "micro.web.routing")
public interface RoutingConfig {

  /**
   * todo
   *
   * @return todo
   */
  @WithParentName
  Map<String, RouteConfig> routing();


  interface RouteConfig {

    /**
     * todo
     *
     * @return todo
     */
    Optional<String> path();

    /**
     * todo
     *
     * @return todo
     */
    Optional<String> regex();

    /**
     * todo
     *
     * @return todo
     */
    Optional<List<String>> methods();

    /**
     * Todo
     *
     * @return todo
     */
    Map<String, String> params();
  }
}
