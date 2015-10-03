package model;

import java.util.List;

public class Segment {
    private String id;
    private String description;
    private List<String> fragmentIds;

    public Segment(String id, String description, List<String> fragmentIds) {
        this.id = id;
        this.description = description;
        this.fragmentIds = fragmentIds;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getFragmentIds() {
        return fragmentIds;
    }

    @Override
    public String toString() {
        return "Segment{" +
            "id='" + id + '\'' +
            ", description='" + description + '\'' +
            ", fragmentIds=" + fragmentIds +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Segment segment = (Segment) o;

        if (id != null ? !id.equals(segment.id) : segment.id != null)
            return false;
        if (description != null ? !description.equals(segment.description) : segment.description != null)
            return false;
        return !(fragmentIds != null ? !fragmentIds.equals(segment.fragmentIds) : segment.fragmentIds != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (fragmentIds != null ? fragmentIds.hashCode() : 0);
        return result;
    }
}
