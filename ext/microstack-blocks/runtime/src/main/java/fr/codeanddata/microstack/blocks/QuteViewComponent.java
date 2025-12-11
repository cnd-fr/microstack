package fr.codeanddata.microstack.blocks;

import fr.codeanddata.microstack.blocks.models.ViewFragment;
import io.quarkus.qute.Template;
import io.smallrye.mutiny.Uni;

import java.util.Collection;
import java.util.Map;

public abstract class QuteViewComponent implements ViewComponent {

  protected abstract Template getTemplate();

  @Override
  public Uni<String> render(Map<String, Object> context, Map<String, Collection<ViewFragment>> slots) {
    return getTemplate().data(context).setAttribute("__slots", slots).createUni();
  }
}
