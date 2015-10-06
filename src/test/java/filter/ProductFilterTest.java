package filter;

import model.*;
import org.junit.*;
import org.mockito.Mock;
import repository.*;

import java.util.List;

import static java.util.Arrays.asList;
import static model.FragmentBuilder.fragmentBuilder;
import static model.ProductBuilder.productBuilder;
import static model.SegmentBuilder.segmentBuilder;
import static model.SegmentRuleBuilder.segmentRuleBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static utils.MapBuilder.mapBuilder;

public class ProductFilterTest {
    @Mock ProductRepository productRepository;
    @Mock SegmentRuleRepository segmentRuleRepository;
    @Mock SegmentRepository segmentRepository;
    @Mock FragmentRepository fragmentRepository;
    @Mock SegmentRuleFilter segmentRuleFilter;

    private ProductFilter productFilter;

    @Before
    public void setup() {
        initMocks(this);

        productFilter = new ProductFilter(
            productRepository,
            segmentRuleRepository,
            segmentRepository,
            fragmentRepository,
            segmentRuleFilter
        );
    }

    @Test
    public void testGetMatchingProducts() throws Exception {
        Product matchedProduct = productBuilder().id("product 1").segmentRuleIds(asList("segment rule id 1")).build();
        Product unmatchedProduct = productBuilder().id("product 2").segmentRuleIds(asList("segment rule id 1")).build();
        SegmentRule segmentRule = segmentRuleBuilder().id("segment rule id 1").segmentIds(asList("segment id 1")).build();
        Segment segment = segmentBuilder().id("segment id 1").fragmentIds(asList("fragment id 1")).build();
        Fragment fragment = fragmentBuilder().id("segment rule id 1").requiredAttributes(mapBuilder().put("some_key", "some_value").build()).build();

        doReturn(asList(matchedProduct, unmatchedProduct)).when(productRepository).getAll();
        doReturn(asList(segmentRule)).when(segmentRuleRepository).getAll();
        doReturn(asList(segment)).when(segmentRepository).getAll();
        doReturn(asList(fragment)).when(fragmentRepository).getAll();

        doReturn(true).when(segmentRuleFilter).filterSegmentRules(eq(matchedProduct), anyMap());
        doReturn(false).when(segmentRuleFilter).filterSegmentRules(eq(unmatchedProduct), anyMap());


        List<Product> matchingProducts = productFilter.getMatchingProducts(mapBuilder().put("some_other_key", "some_other_value").build());


        assertThat(matchingProducts, equalTo(asList(matchedProduct)));

        verify(segmentRuleFilter).filterSegmentRules(
            matchedProduct,
            mapBuilder().put("some_other_key", "some_other_value").build()
        );

        verify(segmentRuleFilter).filterSegmentRules(
            unmatchedProduct,
            mapBuilder().put("some_other_key", "some_other_value").build()
        );
    }
}
