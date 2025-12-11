package fr.codeanddata.microstack.blocks.models;

import fr.codeanddata.microstack.blocks.ViewComponent;
import lombok.*;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewFragment {
    ViewComponent component;
    Map<String, Object> context;
    Map<String, Collection<ViewFragment>> slots;
}
