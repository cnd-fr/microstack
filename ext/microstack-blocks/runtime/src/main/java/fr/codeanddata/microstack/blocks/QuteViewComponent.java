package fr.codeanddata.microstack.blocks;

import com.sun.net.httpserver.HttpContext;
import fr.codeanddata.microstack.blocks.models.ViewFragment;
import io.quarkus.qute.Template;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public abstract class QuteViewComponent implements ViewComponent {

  @Inject
  QuteViewContext viewContext;

  protected abstract Template getTemplate();

  @Override
  public Uni<String> render(Object context, Map<String, Collection<ViewFragment>> slots) {
    final String lang = viewContext.getLocale();
    return getTemplate()
      .instance().setLocale(lang)
      .data(context).setAttribute("__slots", slots).createUni();
  }
}
