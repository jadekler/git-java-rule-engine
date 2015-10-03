package model;

import java.util.List;

public class ProductBuilder {
    private String id;
    private String name;
    private float cost;
    private List<String> ruleIds;

    public ProductBuilder id(String id) {
        this.id = id;
        return this;
    }

    public ProductBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder cost(float cost) {
        this.cost = cost;
        return this;
    }

    public ProductBuilder ruleIds(List<String> ruleIds) {
        this.ruleIds = ruleIds;
        return this;
    }

    public static ProductBuilder productBuilder() {
        return new ProductBuilder();
    }

    public Product build() {
        return new Product(id, name, cost, ruleIds);
    }
}
