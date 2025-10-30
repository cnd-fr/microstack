package fr.codeanddata.microstack.blocks;

import fr.codeanddata.microstack.blocks.models.ViewDefinition;
import fr.codeanddata.microstack.blocks.models.ViewFragment;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ViewService {

    @Inject
    ViewDefinitionMapper viewDefinitionMapper;

    public Uni<String> render(ViewFragment fragment) {
        if (fragment != null && fragment.getComponent() != null) {
            return fragment.getComponent().render(fragment.getContext(), fragment.getSlots());
        } else  {
            return Uni.createFrom().item("");
        }
    }

    public Uni<String> render(ViewDefinition definition) {
        return viewDefinitionMapper.apply(definition)
                .chain(this::render);
    }
}
