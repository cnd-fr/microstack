package fr.codeanddata.microstack.blocks;

import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestScoped
public class QuteViewContext {

  @Inject
  HttpServerRequest request;

  public String getLocale() {
    final String langs = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
    final List<String> acceptedLocales = new ArrayList<>();
    Arrays.stream((langs != null ? langs : "en").split(";"))
      .filter(s -> !"q=".startsWith(s))
      .forEach(l -> acceptedLocales.addAll(Arrays.stream(l.split(",")).map(String::trim).toList()));

    return acceptedLocales.isEmpty() ? "en" : acceptedLocales.getFirst();
  }
}
