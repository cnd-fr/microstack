package fr.codeanddata.microstack.blocks.conf;

import fr.codeanddata.microstack.blocks.models.ViewFragment;
import fr.codeanddata.microstack.blocks.ViewService;
import io.quarkus.qute.*;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionStage;

@EngineConfiguration
public class EngineSlotConfiguration implements SectionHelperFactory<EngineSlotConfiguration.SlotSectionHelper> {

    @Inject
    ViewService viewService;

    @Override
    public List<String> getDefaultAliases() {
        return List.of("slot");
    }

    @Override
    public ParametersInfo getParameters() {
        return ParametersInfo.builder().addParameter("name").build();
    }

    @Override
    public Scope initializeBlock(Scope outerScope, BlockInfo block) {
        block.addExpression("name", block.getParameter("name"));
        return outerScope;
    }

    @Override
    public SlotSectionHelper initialize(SectionInitContext context) {
        return new SlotSectionHelper(context.getExpression("name"), viewService);
    }

    static class SlotSectionHelper implements SectionHelper {

        private final Expression slotName;
        private final ViewService viewService;

        public SlotSectionHelper(Expression slotName, ViewService viewService) {
            this.slotName = slotName;
            this.viewService = viewService;
        }

        @Override
        public CompletionStage<ResultNode> resolve(SectionResolutionContext context) {
            return context.evaluate(slotName)
                    .thenCompose(slotName -> {
                        final Object slotMap = context.resolutionContext().getAttribute("__slots");
                        try {
                            @SuppressWarnings("unchecked") final List<ViewFragment> fragments = ((Map<String, List<ViewFragment>>) slotMap).getOrDefault(slotName, new ArrayList<>());

                            if (fragments.isEmpty()) {
                                return Uni.createFrom().item((ResultNode) new SingleResultNode("")).subscribeAsCompletionStage();
                            } else {
                                return Uni.join().all(fragments.stream().map(viewService::render).toList())
                                        .andCollectFailures()
                                        .map(results -> (ResultNode) new SingleResultNode(String.join("", results)))
                                        .onFailure().recoverWithItem(() -> new SingleResultNode(""))
                                        .subscribeAsCompletionStage();
                            }
                        } catch (Exception e) {
                            return Uni.createFrom().item((ResultNode) new SingleResultNode("")).subscribeAsCompletionStage();
                        }
                    });
        }
    }
}
