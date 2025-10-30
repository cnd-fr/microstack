package fr.codeanddata.microstack.blocks;

import fr.codeanddata.microstack.blocks.models.ViewDefinition;
import fr.codeanddata.microstack.blocks.models.ViewFragment;
import fr.codeanddata.microstack.blocks.registries.ComponentRegistry;
import fr.codeanddata.microstack.blocks.registries.DataSourceRegistry;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.*;
import java.util.function.Function;

@ApplicationScoped
public class ViewDefinitionMapper implements Function<ViewDefinition, Uni<ViewFragment>> {

  @Inject
  ComponentRegistry componentRegistry;

  @Inject
  DataSourceRegistry dsRegistry;

  @Override
  public Uni<ViewFragment> apply(ViewDefinition definition) {
    final ViewFragment viewFragment = ViewFragment.builder().build();
    final Map<String, Object> context = new HashMap<>();
    viewFragment.setContext(context);

    return mapSlots(Optional.ofNullable(definition.getSlots()).orElse(new HashMap<>()))
      .invoke(viewFragment::setSlots)

      .map(x -> componentRegistry.get(definition.getComponent()))
      .invoke(viewFragment::setComponent)

      .chain(x -> mapDataSources(Optional.ofNullable(definition.getDataSources()).orElse(new HashMap<>()))
        .call(dsMap -> {
          final Map<String, String> mapping = definition.getContextMapping();

          Uni<Void> ctxUni = Uni.createFrom().voidItem();
          for (final String key : Optional.ofNullable(mapping).orElse(new HashMap<>()).keySet()) {
            ctxUni = ctxUni.chain(() -> dsMap.get(mapping.get(key)).apply(key))
              .invoke(value -> context.put(key, value))
              .replaceWithVoid();
          }
          return ctxUni;
        })
        .invoke(dsMap -> {
          final Map<String, String> exposition = Optional.ofNullable(definition.getExpose()).orElse(new HashMap<>());
          for (String key : exposition.keySet()) {
            if (dsMap.containsKey(key)) {
              context.put(exposition.get(key), dsMap.get(key));
            }
          }
        }))
      .map(x -> viewFragment);
  }

  Uni<Map<String, Collection<ViewFragment>>> mapSlots(Map<String, List<ViewDefinition>> slotDefinition) {
    final Map<String, Collection<ViewFragment>> fragmentMap = new HashMap<>();

    Uni<Void> slotUni = Uni.createFrom().voidItem();
    for (String key : slotDefinition.keySet()) {
      slotUni = slotUni.chain(() -> mapFragments(slotDefinition.get(key))
        .invoke(result -> fragmentMap.put(key, result))
        .replaceWithVoid());
    }

    return slotUni.onItem().transform(nothing -> fragmentMap);
  }

  Uni<List<ViewFragment>> mapFragments(List<ViewDefinition> viewFragments) {
    final List<ViewFragment> fragments = new ArrayList<>();
    Uni<Void> uni = Uni.createFrom().voidItem();
    for (final ViewDefinition viewDefinition : viewFragments) {
      uni = uni.chain(() -> apply(viewDefinition)
          .invoke(fragments::add))
        .replaceWithVoid();
    }
    return uni.map(nothing -> fragments);
  }

  Uni<Map<String, Function<String, Uni<?>>>> mapDataSources(Map<String, Object> dataSourceDef) {
    final Map<String, Function<String, Uni<?>>> dataSourceMap = new HashMap<>();
    Uni<Void> uni = Uni.createFrom().voidItem();
    for (String dataSourceName : dataSourceDef.keySet()) {
      uni = uni.chain(() -> Uni.createFrom().item(dsRegistry.get(dataSourceName))
        .chain(dataSource -> dataSource.load(dataSourceDef.get(dataSourceName)))
        .invoke(result -> dataSourceMap.put(dataSourceName, result))
        .replaceWithVoid());
    }

    return uni.map(nothing -> dataSourceMap);
  }
}
