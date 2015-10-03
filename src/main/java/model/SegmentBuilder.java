package model;

import java.util.List;

public class SegmentBuilder {
    private String id;
    private String description;
    private List<String> fragmentIds;

    public SegmentBuilder id(String id) {
        this.id = id;
        return this;
    }

    public SegmentBuilder description(String description) {
        this.description = description;
        return this;
    }

    public SegmentBuilder fragmentIds(List<String> fragmentIds) {
        this.fragmentIds = fragmentIds;
        return this;
    }

    public static SegmentBuilder segmentBuilder() {
        return new SegmentBuilder();
    }

    public Segment build() {
        return new Segment(id, description, fragmentIds);
    }
}
