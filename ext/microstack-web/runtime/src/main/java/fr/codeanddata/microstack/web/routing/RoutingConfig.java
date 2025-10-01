package fr.codeanddata.microstack.web.routing;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithParentName;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * C'est la javadockbleu
 */
@ConfigMapping(prefix = "micro.web.routing")
public interface RoutingConfig {

  /**
   * Voila une jdoque
   *
   * @return retour de la fn
   */
  @WithParentName
  Map<String, RouteConfig> routing();

  /**
   * C'est la conf
   */
  interface RouteConfig {
    /**
     * De la javadoc
     *
     * @return pas obligatoire
     */
    Optional<String> path();

    /**
     * Jdoc aussi
     *
     * @return oui par la
     */
    Optional<String> regex();

    /**
     * Un dernier
     *
     * @return dernier retour
     */
    Optional<List<String>> methods();
  }
}
