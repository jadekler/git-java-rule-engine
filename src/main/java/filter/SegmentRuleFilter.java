package filter;

import model.*;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class SegmentRuleFilter {
    // TODO: Make me static, figure out powermock
    public boolean filterSegmentRules(
        Product product,
        Map<String, String> attributes,
        List<SegmentRule> allSegmentRules,
        List<Segment> allSegments,
        List<Fragment> allFragments
    ) {
        TreeNode parent = buildTree(product, allSegmentRules, allSegments, allFragments);

        return false;
    }

    // TODO: Make me recursive
    private TreeNode buildTree(
        Product product,
        List<SegmentRule> allSegmentRules,
        List<Segment> allSegments,
        List<Fragment> allFragments
    ) {
        List<SegmentRule> segmentRulesForProduct = allSegmentRules.stream()
            .filter(segmentRule -> product.getSegmentRuleIds().contains(segmentRule.getId()))
            .collect(toList());

        List<TreeNode> childrenForProduct = segmentRulesForProduct.stream()
            .map(segmentRule -> {
                List<Segment> segmentsForSegmentRule = allSegments.stream()
                    .filter(segment -> segmentRule.getSegmentIds().contains(segment.getId()))
                    .collect(toList());

                List<TreeNode> segmentRuleChildren = segmentsForSegmentRule.stream()
                    .map(segment -> {
                        List<Fragment> fragmentsForSegment = allFragments.stream()
                            .filter(fragment -> segment.getFragmentIds().contains(fragment.getId()))
                            .collect(toList());

                        List<TreeNode> segmentChildren = fragmentsForSegment.stream()
                            .map(fragment -> {
                                    Map<String, String> fragmentRequiredAttributes = fragment.getRequiredAttributes();
                                    List<TreeNode> fragmentChildren = fragmentRequiredAttributes.keySet().stream()
                                        .map(attributeKey -> new TreeNode(attributeKey, fragmentRequiredAttributes.get(attributeKey)))
                                        .collect(toList());

                                    return fragmentChildren;
                                }
                            )
                            .flatMap(Collection::stream)
                            .collect(toList());

                        return new TreeNode(segmentChildren);
                    })
                    .collect(toList());

                return new TreeNode(segmentRuleChildren);
            })
            .collect(toList());

        return new TreeNode(childrenForProduct);
    }
}
