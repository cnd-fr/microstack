package fr.codeanddata.microstack.blocks.components;

import fr.codeanddata.microstack.blocks.QuteViewComponent;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.smallrye.common.annotation.Identifier;
import jakarta.enterprise.context.ApplicationScoped;

@Identifier("core/raw")
@ApplicationScoped
public class HtmlComponent extends QuteViewComponent {

  @Location("components/core/raw")
  Template tpl;

  @Override
  protected Template getTemplate() {
    return tpl;
  }
}
