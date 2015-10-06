package filter;

import model.*;
import org.junit.*;

import static java.util.Arrays.asList;
import static model.FragmentBuilder.fragmentBuilder;
import static model.ProductBuilder.productBuilder;
import static model.SegmentBuilder.segmentBuilder;
import static model.SegmentRuleBuilder.segmentRuleBuilder;
import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static utils.MapBuilder.mapBuilder;

public class SegmentRuleFilterTest {
    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void testFilterSegmentRules_MatchedAttribute_NoneMatched() {
        Product product = productBuilder()
            .id("product 1")
            .segmentRuleIds(asList("segment rule id 1"))
            .build();

        SegmentRule segmentRule = segmentRuleBuilder()
            .id("segment rule id 1")
            .segmentIds(asList("segment id 1"))
            .build();

        Segment segment = segmentBuilder()
            .id("segment id 1")
            .fragmentIds(asList("fragment id 1"))
            .build();

        Fragment fragment = fragmentBuilder()
            .id("fragment id 1")
            .requiredAttributes(mapBuilder()
                .put("key_one", "some_matched_value")
                .put("key_two", "another_matched_value")
                .build())
            .build();


        SegmentRuleFilter segmentRuleFilter = new SegmentRuleFilter(asList(segmentRule), asList(segment), asList(fragment));

        boolean result = segmentRuleFilter.filterSegmentRules(
            product,
            mapBuilder()
                .put("key_one", "nope")
                .put("key_two", "nope")
                .build());

        assertFalse(result);
    }

    @Test
    public void testFilterSegmentRules_MatchedAttribute_TwoMatched() {
        Product product = productBuilder()
            .id("product 1")
            .segmentRuleIds(asList("segment rule id 1"))
            .build();

        SegmentRule segmentRule = segmentRuleBuilder()
            .id("segment rule id 1")
            .segmentIds(asList("segment id 1"))
            .build();

        Segment segment = segmentBuilder()
            .id("segment id 1")
            .fragmentIds(asList("fragment id 1"))
            .build();

        Fragment fragment = fragmentBuilder()
            .id("fragment id 1")
            .requiredAttributes(mapBuilder()
                .put("key_one", "some_matched_value")
                .put("key_two", "another_matched_value")
                .build())
            .build();


        SegmentRuleFilter segmentRuleFilter = new SegmentRuleFilter(asList(segmentRule), asList(segment), asList(fragment));

        boolean result = segmentRuleFilter.filterSegmentRules(
            product,
            mapBuilder()
                .put("key_one", "some_matched_value")
                .put("key_two", "another_matched_value")
                .build());

        assertTrue(result);
    }

    @Test
    public void testFilterSegmentRules_MultipleAttributes_OneMatchedOneUnmatched() {
        Product product = productBuilder()
            .id("product 1")
            .segmentRuleIds(asList("segment rule id 1"))
            .build();

        SegmentRule segmentRule = segmentRuleBuilder()
            .id("segment rule id 1")
            .segmentIds(asList("segment id 1"))
            .build();

        Segment segment = segmentBuilder()
            .id("segment id 1")
            .fragmentIds(asList("fragment id 1"))
            .build();

        Fragment fragment = fragmentBuilder()
            .id("fragment id 1")
            .requiredAttributes(
                mapBuilder()
                    .put("key_one", "some_matched_value")
                    .put("key_two", "this_value_will_not_be_matched")
                    .build())
            .build();


        SegmentRuleFilter segmentRuleFilter = new SegmentRuleFilter(asList(segmentRule), asList(segment), asList(fragment));

        boolean result = segmentRuleFilter.filterSegmentRules(
            product,
            mapBuilder()
                .put("key_one", "some_matched_value")
                .put("key_two", "some_unmatched_value")
                .build());

        assertFalse(result);
    }

    @Test
    public void testFilterSegmentRules_MultipleFragments_TwoMatched() {
        Product product = productBuilder()
            .id("product 1")
            .segmentRuleIds(asList("segment rule id 1"))
            .build();

        SegmentRule segmentRule = segmentRuleBuilder()
            .id("segment rule id 1")
            .segmentIds(asList("segment id 1"))
            .build();

        Segment segment = segmentBuilder()
            .id("segment id 1")
            .fragmentIds(asList("matched fragment", "another matched fragment"))
            .build();

        Fragment matchedFragment = fragmentBuilder()
            .id("matched fragment")
            .requiredAttributes(mapBuilder().put("key_one", "some_matched_value").build())
            .build();
        Fragment anotherMatchedFragment = fragmentBuilder()
            .id("another matched fragment")
            .requiredAttributes(mapBuilder().put("key_two", "another_matched_value").build())
            .build();


        SegmentRuleFilter segmentRuleFilter = new SegmentRuleFilter(
            asList(segmentRule),
            asList(segment),
            asList(matchedFragment, anotherMatchedFragment));

        boolean result = segmentRuleFilter.filterSegmentRules(
            product,
            mapBuilder()
                .put("key_one", "some_matched_value")
                .put("key_two", "another_matched_value")
                .build());

        assertTrue(result);
    }

    @Test
    public void testFilterSegmentRules_MultipleFragments_OneMatchedOneUnmatched() {
        Product product = productBuilder()
            .id("product 1")
            .segmentRuleIds(asList("segment rule id 1"))
            .build();

        SegmentRule segmentRule = segmentRuleBuilder()
            .id("segment rule id 1")
            .segmentIds(asList("segment id 1"))
            .build();

        Segment segment = segmentBuilder()
            .id("segment id 1")
            .fragmentIds(asList("matched fragment", "unmatched fragment"))
            .build();

        Fragment matchedFragment = fragmentBuilder()
            .id("matched fragment")
            .requiredAttributes(mapBuilder().put("key_one", "some_matched_value").build())
            .build();
        Fragment unmatchedFragment = fragmentBuilder()
            .id("unmatched fragment")
            .requiredAttributes(mapBuilder().put("key_two", "this_value_will_not_be_matched").build())
            .build();


        SegmentRuleFilter segmentRuleFilter = new SegmentRuleFilter(
            asList(segmentRule),
            asList(segment),
            asList(matchedFragment, unmatchedFragment));

        boolean result = segmentRuleFilter.filterSegmentRules(
            product,
            mapBuilder()
                .put("key_one", "some_matched_value")
                .put("key_two", "some_unmatched_value")
                .build());

        assertFalse(result);
    }

    @Test
    public void testFilterSegmentRules_MultipleSegments_TwoMatched() {
        Product product = productBuilder().id("product 1").segmentRuleIds(asList("segment rule id 1")).build();

        SegmentRule segmentRule = segmentRuleBuilder()
            .id("segment rule id 1")
            .segmentIds(asList("matched segment", "another matched segment"))
            .build();

        Segment matchedSegment = segmentBuilder()
            .id("matched segment")
            .fragmentIds(asList("matched fragment"))
            .build();
        Segment anotherMatchedSegment = segmentBuilder()
            .id("another matched segment")
            .fragmentIds(asList("another matched fragment"))
            .build();

        Fragment matchedFragment = fragmentBuilder()
            .id("matched fragment")
            .requiredAttributes(mapBuilder().put("key_one", "some_matched_value").build())
            .build();
        Fragment anotherMatchedFragment = fragmentBuilder()
            .id("another matched fragment")
            .requiredAttributes(mapBuilder().put("key_two", "another_matched_value").build())
            .build();


        SegmentRuleFilter segmentRuleFilter = new SegmentRuleFilter(
            asList(segmentRule),
            asList(matchedSegment, anotherMatchedSegment),
            asList(matchedFragment, anotherMatchedFragment));

        boolean result = segmentRuleFilter.filterSegmentRules(
            product,
            mapBuilder()
                .put("key_one", "some_matched_value")
                .put("key_two", "another_matched_value")
                .build());

        assertTrue(result);
    }

    @Test
    public void testFilterSegmentRules_MultipleSegments_OneMatchedOneUnmatched() {
        Product product = productBuilder().id("product 1").segmentRuleIds(asList("segment rule id 1")).build();

        SegmentRule segmentRule = segmentRuleBuilder()
            .id("segment rule id 1")
            .segmentIds(asList("matched segment", "unmatched segment"))
            .build();

        Segment matchedSegment = segmentBuilder()
            .id("matched segment")
            .fragmentIds(asList("matched fragment"))
            .build();
        Segment unmatchedSegment = segmentBuilder()
            .id("unmatched segment")
            .fragmentIds(asList("unmatched fragment"))
            .build();

        Fragment matchedFragment = fragmentBuilder()
            .id("matched fragment")
            .requiredAttributes(mapBuilder().put("key_one", "some_matched_value").build())
            .build();
        Fragment unmatchedFragment = fragmentBuilder()
            .id("unmatched fragment")
            .requiredAttributes(mapBuilder().put("key_two", "this_value_will_not_be_matched").build())
            .build();


        SegmentRuleFilter segmentRuleFilter = new SegmentRuleFilter(
            asList(segmentRule),
            asList(matchedSegment, unmatchedSegment),
            asList(matchedFragment, unmatchedFragment));

        boolean result = segmentRuleFilter.filterSegmentRules(
            product,
            mapBuilder()
                .put("key_one", "some_matched_value")
                .put("key_two", "unmatched_value")
                .build());

        assertFalse(result);
    }

    @Test
    public void testFilterSegmentRules_MultipleSegmentRules_TwoMatched() {
        Product product = productBuilder()
            .id("product 1")
            .segmentRuleIds(asList("matched segment rule", "another matched segment rule"))
            .build();

        SegmentRule matchedSegmentRule = segmentRuleBuilder()
            .id("matched segment rule")
            .segmentIds(asList("matched segment"))
            .build();
        SegmentRule anotherMatchedSegmentRule = segmentRuleBuilder()
            .id("another matched segment rule")
            .segmentIds(asList("another matched segment"))
            .build();

        Segment matchedSegment = segmentBuilder()
            .id("matched segment")
            .fragmentIds(asList("matched fragment"))
            .build();
        Segment anotherMatchedSegment = segmentBuilder()
            .id("another matched segment")
            .fragmentIds(asList("another matched fragment"))
            .build();

        Fragment matchedFragment = fragmentBuilder()
            .id("matched fragment")
            .requiredAttributes(mapBuilder().put("key_one", "some_matched_value").build())
            .build();
        Fragment anotherMatchedFragment = fragmentBuilder()
            .id("another matched fragment")
            .requiredAttributes(mapBuilder().put("key_two", "another_matched_value").build())
            .build();


        SegmentRuleFilter segmentRuleFilter = new SegmentRuleFilter(
            asList(matchedSegmentRule, anotherMatchedSegmentRule),
            asList(matchedSegment, anotherMatchedSegment),
            asList(matchedFragment, anotherMatchedFragment));

        boolean result = segmentRuleFilter.filterSegmentRules(
            product,
            mapBuilder()
                .put("key_one", "some_matched_value")
                .put("key_two", "another_matched_value")
                .build());

        assertTrue(result);
    }

    @Test
    public void testFilterSegmentRules_MultipleSegmentRules_OneMatchedOneUnmatched() {
        Product product = productBuilder()
            .id("product 1")
            .segmentRuleIds(asList("matched segment rule", "unmatched segment rule"))
            .build();

        SegmentRule matchedSegmentRule = segmentRuleBuilder()
            .id("matched segment rule")
            .segmentIds(asList("matched segment"))
            .build();
        SegmentRule unmatchedSegmentRule = segmentRuleBuilder()
            .id("unmatched segment rule")
            .segmentIds(asList("unmatched segment"))
            .build();

        Segment matchedSegment = segmentBuilder()
            .id("matched segment")
            .fragmentIds(asList("matched fragment"))
            .build();
        Segment unmatchedSegment = segmentBuilder()
            .id("unmatched segment")
            .fragmentIds(asList("unmatched fragment"))
            .build();

        Fragment matchedFragment = fragmentBuilder()
            .id("matched fragment")
            .requiredAttributes(mapBuilder().put("key_one", "some_matched_value").build())
            .build();
        Fragment unmatchedFragment = fragmentBuilder()
            .id("unmatched fragment")
            .requiredAttributes(mapBuilder().put("key_two", "this_value_will_not_be_matched").build())
            .build();


        SegmentRuleFilter segmentRuleFilter = new SegmentRuleFilter(
            asList(matchedSegmentRule, unmatchedSegmentRule),
            asList(matchedSegment, unmatchedSegment),
            asList(matchedFragment, unmatchedFragment));

        boolean result = segmentRuleFilter.filterSegmentRules(
            product,
            mapBuilder()
                .put("key_one", "some_matched_value")
                .put("key_two", "unmatched_value")
                .build());

        assertFalse(result);
    }
}
