package fr.codeanddata.microstack.blocks;

import fr.codeanddata.microstack.blocks.models.ViewFragment;
import io.smallrye.mutiny.Uni;

import java.util.Collection;
import java.util.Map;

public interface ViewComponent {
    Uni<String> render(Object context, Map<String, Collection<ViewFragment>> slots);
}
