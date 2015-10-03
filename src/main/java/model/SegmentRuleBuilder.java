package model;

import java.util.List;

public class SegmentRuleBuilder {
    private String id;
    private String name;
    private List<String> segmentIds;

    public SegmentRuleBuilder id(String id) {
        this.id = id;
        return this;
    }

    public SegmentRuleBuilder name(String name) {
        this.name = name;
        return this;
    }

    public SegmentRuleBuilder segmentIds(List<String> segmentIds) {
        this.segmentIds = segmentIds;
        return this;
    }

    public static SegmentRuleBuilder segmentRuleBuilder() {
        return new SegmentRuleBuilder();
    }

    public SegmentRule build() {
        return new SegmentRule(id, name, segmentIds);
    }
}
