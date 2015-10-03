package model;

import java.util.List;

public class Product {
    private String id;
    private String name;
    private float cost;
    private List<String> ruleIds;

    public Product(String id, String name, float cost, List<String> ruleIds) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.ruleIds = ruleIds;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getCost() {
        return cost;
    }

    public List<String> getRuleIds() {
        return ruleIds;
    }

    @Override
    public String toString() {
        return "Product{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", cost=" + cost +
            ", ruleIds=" + ruleIds +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Product product = (Product) o;

        if (Float.compare(product.cost, cost) != 0)
            return false;
        if (id != null ? !id.equals(product.id) : product.id != null)
            return false;
        if (name != null ? !name.equals(product.name) : product.name != null)
            return false;
        return !(ruleIds != null ? !ruleIds.equals(product.ruleIds) : product.ruleIds != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cost != +0.0f ? Float.floatToIntBits(cost) : 0);
        result = 31 * result + (ruleIds != null ? ruleIds.hashCode() : 0);
        return result;
    }
}
