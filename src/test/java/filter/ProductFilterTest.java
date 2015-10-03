package filter;

import org.junit.*;
import org.mockito.Mock;
import repository.*;

import static org.mockito.Mockito.doReturn;

public class ProductFilterTest {
    @Mock ProductRepository productRepository;
    @Mock SegmentRuleRepository segmentRuleRepository;
    @Mock SegmentRepository segmentRepository;
    @Mock FragmentRepository fragmentRepository;
    @Mock SegmentRuleFilter segmentRuleFilter;

    private ProductFilter productFilter;

    @Before
    public void setup() {
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

    }
}
