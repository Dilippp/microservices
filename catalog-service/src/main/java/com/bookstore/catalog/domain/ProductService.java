package com.bookstore.catalog.domain;

import com.bookstore.catalog.ApplicationProperties;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final ApplicationProperties applicationProperties;

    ProductService(ProductRepository productRepository, ApplicationProperties applicationProperties) {
        this.productRepository = productRepository;
        this.applicationProperties = applicationProperties;
    }

    public PagedResult<Product> getProducts(int pageNo) {
        Sort sort = Sort.by("name").ascending();
        pageNo = pageNo <= 1 ? 0 : pageNo - 1;
        PageRequest pageRequest = PageRequest.of(pageNo, applicationProperties.pageSize(), sort);
        Page<Product> productEntityPage = productRepository.findAll(pageRequest).map(ProductMapper::toProduct);
        return new PagedResult<>(
                productEntityPage.getContent(),
                productEntityPage.getTotalElements(),
                productEntityPage.getNumber() + 1,
                productEntityPage.getTotalPages(),
                productEntityPage.isFirst(),
                productEntityPage.isLast(),
                productEntityPage.hasNext(),
                productEntityPage.hasPrevious());
    }

    public Optional<Product> getProductByCode(String code) {
        return productRepository.findByCode((code)).map(ProductMapper::toProduct);
    }
}
