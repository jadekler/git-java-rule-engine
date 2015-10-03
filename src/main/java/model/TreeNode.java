package model;

import java.util.*;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;

public class TreeNode {
    private Optional<String> criteriaKey = empty();
    private Optional<String> criteriaValue = empty();
    private List<TreeNode> children = emptyList();

    public TreeNode(List<TreeNode> children) {
        this.children = children;
    }

    public TreeNode(String criteriaKey, String criteriaValue) {
        this.criteriaKey = Optional.of(criteriaKey);
        this.criteriaValue = Optional.of(criteriaValue);
    }

    public Optional<String> getCriteriaKey() {
        return criteriaKey;
    }

    public Optional<String> getCriteriaValue() {
        return criteriaValue;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
            "criteriaKey=" + criteriaKey +
            ", criteriaValue=" + criteriaValue +
            ", children=" + children +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        TreeNode treeNode = (TreeNode) o;

        if (criteriaKey != null ? !criteriaKey.equals(treeNode.criteriaKey) : treeNode.criteriaKey != null)
            return false;
        if (criteriaValue != null ? !criteriaValue.equals(treeNode.criteriaValue) : treeNode.criteriaValue != null)
            return false;
        return !(children != null ? !children.equals(treeNode.children) : treeNode.children != null);

    }

    @Override
    public int hashCode() {
        int result = criteriaKey != null ? criteriaKey.hashCode() : 0;
        result = 31 * result + (criteriaValue != null ? criteriaValue.hashCode() : 0);
        result = 31 * result + (children != null ? children.hashCode() : 0);
        return result;
    }
}
