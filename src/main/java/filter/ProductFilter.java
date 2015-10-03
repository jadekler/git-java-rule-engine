package filter;

import model.Product;
import repository.*;

import java.util.*;

import static java.util.Collections.emptyList;

public class ProductFilter {
    private final ProductRepository productRepository;
    private final SegmentRuleRepository segmentRuleRepository;
    private final SegmentRepository segmentRepository;
    private final FragmentRepository fragmentRepository;
    private final SegmentRuleFilter segmentRuleFilter;

    // This would be @Autowired in a spring project
    public ProductFilter(
        ProductRepository productRepository,
        SegmentRuleRepository segmentRuleRepository,
        SegmentRepository segmentRepository,
        FragmentRepository fragmentRepository,
        SegmentRuleFilter segmentRuleFilter
    ) {
        this.productRepository = productRepository;
        this.segmentRuleRepository = segmentRuleRepository;
        this.segmentRepository = segmentRepository;
        this.fragmentRepository = fragmentRepository;
        this.segmentRuleFilter = segmentRuleFilter;
    }

    public List<Product> getMatchingProducts(Map<String, String> attributes) {
        return emptyList();
    }
}
