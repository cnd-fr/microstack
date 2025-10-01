package fr.codeanddata.microstack.tpl.deployment;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

class MicrostackTplProcessor {

    private static final String FEATURE = "microstack-tpl";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }
}
