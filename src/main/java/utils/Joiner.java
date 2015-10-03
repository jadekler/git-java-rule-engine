package utils;

import model.SegmentRule;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Joiner {
    public static List<SegmentRule> join(List<String> segmentRuleIds, List<SegmentRule> allSegmentRules) {
        return allSegmentRules.stream()
            .filter(segment -> segmentRuleIds.contains(segment.getId()))
            .collect(toList());
    }
}
