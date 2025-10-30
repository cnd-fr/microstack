package fr.codeanddata.microstack.blocks;

import io.smallrye.mutiny.Uni;

import java.util.Map;
import java.util.function.Function;

public interface ViewDataSource {
    Uni<Function<String, Uni<?>>> load(Object params);
}
