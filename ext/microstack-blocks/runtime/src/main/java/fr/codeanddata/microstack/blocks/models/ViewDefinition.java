package fr.codeanddata.microstack.blocks.models;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewDefinition {
  String component;
  Map<String, Object> dataSources;
  Map<String, String> contextMapping;
  Map<String, String> expose;
  Map<String, List<ViewDefinition>> slots;
}
