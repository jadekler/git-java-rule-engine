package model;

import java.util.List;

public class SegmentRule {
    private String id;
    private String name;
    private List<String> segmentIds;

    public SegmentRule(String id, String name, List<String> segmentIds) {
        this.id = id;
        this.name = name;
        this.segmentIds = segmentIds;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getSegmentIds() {
        return segmentIds;
    }

    @Override
    public String toString() {
        return "SegmentRule{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", segmentIds=" + segmentIds +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        SegmentRule that = (SegmentRule) o;

        if (id != null ? !id.equals(that.id) : that.id != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        return !(segmentIds != null ? !segmentIds.equals(that.segmentIds) : that.segmentIds != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (segmentIds != null ? segmentIds.hashCode() : 0);
        return result;
    }
}
