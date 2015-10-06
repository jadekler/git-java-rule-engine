package model;

import java.util.*;

// TODO: I need a better name
public interface PreProcessedTreeNode {
    String getId();
    List<String> getChildrenIds();
    Optional<Map<String, String>> getCriteria();
}
