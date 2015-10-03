package model;

import java.util.Map;

public class FragmentBuilder {
    private String id;
    private String description;
    private Map<String, String> requiredAttributes;

    public FragmentBuilder id(String id) {
        this.id = id;
        return this;
    }

    public FragmentBuilder description(String description) {
        this.description = description;
        return this;
    }

    public FragmentBuilder requiredAttributes(Map<String, String> requiredAttributes) {
        this.requiredAttributes = requiredAttributes;
        return this;
    }

    public static FragmentBuilder fragmentBuilder() {
        return new FragmentBuilder();
    }

    public Fragment build() {
        return new Fragment(id, description, requiredAttributes);
    }
}
