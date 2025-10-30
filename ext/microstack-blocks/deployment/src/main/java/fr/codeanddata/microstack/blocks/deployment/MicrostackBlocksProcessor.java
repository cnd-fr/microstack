package fr.codeanddata.microstack.blocks.deployment;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

class MicrostackTplProcessor {

    private static final String FEATURE = "microstack-blocks";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }
}
