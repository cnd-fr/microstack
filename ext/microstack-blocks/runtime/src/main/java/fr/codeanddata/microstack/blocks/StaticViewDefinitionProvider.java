package fr.codeanddata.microstack.blocks;

import fr.codeanddata.microstack.blocks.models.ViewDefinition;
import io.quarkus.arc.Unremovable;

@Unremovable
public abstract class StaticViewDefinitionProvider implements ViewDefinitionProvider {

  protected abstract ViewDefinition getViewDefinition();

  @Override
  public ViewDefinition toViewDefinition() {
    return getViewDefinition();
  }
}
