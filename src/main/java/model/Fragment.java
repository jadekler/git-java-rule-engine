package model;

import java.util.*;

import static java.util.Collections.emptyList;

public class Fragment implements PreProcessedTreeNode {
    private String id;
    private String description;
    private Map<String, String> requiredAttributes;

    public Fragment(String id, String description, Map<String, String> requiredAttributes) {
        this.id = id;
        this.description = description;
        this.requiredAttributes = requiredAttributes;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getRequiredAttributes() {
        return requiredAttributes;
    }

    @Override
    public List<String> getChildrenIds() {
        return emptyList();
    }

    @Override
    public Optional<Map<String, String>> getCriteria() {
        return Optional.of(requiredAttributes);
    }

    @Override
    public String toString() {
        return "Fragment{" +
            "id='" + id + '\'' +
            ", description='" + description + '\'' +
            ", requiredAttributes=" + requiredAttributes +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Fragment fragment = (Fragment) o;

        if (id != null ? !id.equals(fragment.id) : fragment.id != null)
            return false;
        if (description != null ? !description.equals(fragment.description) : fragment.description != null)
            return false;
        return !(requiredAttributes != null ? !requiredAttributes.equals(fragment.requiredAttributes) : fragment.requiredAttributes != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (requiredAttributes != null ? requiredAttributes.hashCode() : 0);
        return result;
    }
}
