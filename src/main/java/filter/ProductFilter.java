package filter;

import model.*;
import repository.*;

import java.util.*;

import static java.util.stream.Collectors.toList;

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
        List<Product> allProducts = productRepository.getAll();
        List<SegmentRule> allSegmentRules = segmentRuleRepository.getAll();
        List<Segment> allSegments = segmentRepository.getAll();
        List<Fragment> allFragments = fragmentRepository.getAll();

        return allProducts.stream()
            .filter(product -> segmentRuleFilter.filterSegmentRules(
                product,
                attributes,
                allSegmentRules,
                allSegments,
                allFragments
            ))
            .collect(toList());
    }
}
