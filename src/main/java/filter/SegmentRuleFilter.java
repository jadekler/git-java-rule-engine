package filter;

import model.*;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class SegmentRuleFilter {
    private List<PreProcessedTreeNode> allSegmentRules;
    private List<PreProcessedTreeNode> allSegments;
    private List<PreProcessedTreeNode> allFragments;

    // This would be @Autowired in a spring project
    public SegmentRuleFilter(List<PreProcessedTreeNode> allSegmentRules, List<PreProcessedTreeNode> allSegments, List<PreProcessedTreeNode> allFragments) {
        this.allSegmentRules = allSegmentRules;
        this.allSegments = allSegments;
        this.allFragments = allFragments;
    }

    public boolean filterSegmentRules(Product product, Map<String, String> givenAttributes) {
        List<List<PreProcessedTreeNode>> levels = asList(allSegmentRules, allSegments, allFragments);
        TreeNode recursive = genericSubleveler(product, levels, 0);

        return isMatching(recursive, givenAttributes);
    }

    // TODO: I need a better name
    private static TreeNode genericSubleveler(
        PreProcessedTreeNode parent,
        List<List<PreProcessedTreeNode>> childrenLevels,
        int depth
    ) {
        if (parent.getCriteria().isPresent()) {
            Map<String, String> criteria = parent.getCriteria().get();

            List<TreeNode> criteriaNodes = criteria.keySet().stream()
                .map(attributeKey -> new TreeNode(attributeKey, criteria.get(attributeKey)))
                .collect(toList());

            return new TreeNode(criteriaNodes);
        }

        List<PreProcessedTreeNode> allPossibleChildren = childrenLevels.get(depth);

        List<PreProcessedTreeNode> children = allPossibleChildren.stream()
            .filter(segmentRule -> parent.getChildrenIds().contains(segmentRule.getId()))
            .collect(toList());

        List<TreeNode> childrenForProduct = children.stream()
            .map(child -> genericSubleveler(child, childrenLevels, depth + 1))
            .collect(toList());

        return new TreeNode(childrenForProduct);
    }

    private static boolean isMatching(TreeNode treeNode, Map<String, String> givenAttributes) {
        if (treeNode.getCriteriaKey().isPresent()) {
            // CHILD NODE

            String criteriaKey = treeNode.getCriteriaKey().get();
            String criteriaValue = treeNode.getCriteriaValue().get();

            if (givenAttributes.containsKey(criteriaKey)) {
                return givenAttributes.get(criteriaKey).equals(criteriaValue);
            } else {
                return false;
            }
        } else {
            // BRANCH NODE

            return treeNode.getChildren().stream()
                .allMatch(child -> isMatching(child, givenAttributes));
        }
    }
}
