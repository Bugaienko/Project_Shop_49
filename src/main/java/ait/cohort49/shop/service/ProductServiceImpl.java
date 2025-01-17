package ait.cohort49.shop.service;

import ait.cohort49.shop.exceprionHandling.exceptions.FirstTestException;
import ait.cohort49.shop.exceprionHandling.exceptions.SecondTestException;
import ait.cohort49.shop.exceprionHandling.exceptions.ThirdTestException;
import ait.cohort49.shop.model.dto.ProductDTO;
import ait.cohort49.shop.model.entity.Product;
import ait.cohort49.shop.repository.ProductRepository;
import ait.cohort49.shop.service.interfaces.ProductService;
import ait.cohort49.shop.service.mapping.ProductMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sergey Bugaenko
 * {@code @date} 11.12.2024
 */

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMappingService mappingService;

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(ProductRepository repository, ProductMappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDto) {
        logger.info("Saving product in Service work");
        Product product = mappingService.mapDtoToEntity(productDto);
        product.setActive(true);
        return mappingService.mapEntityToDto(repository.save(product));
    }

    @Override
    public List<ProductDTO> getAllActiveProducts() {
        return repository.findAll().stream()
                .filter(Product::isActive)
                .map(mappingService::mapEntityToDto)
                .toList();
               // .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = repository.findById(id).orElse(null);
        if (product == null || !product.isActive()) {
            throw new ThirdTestException("This is the third test exception");
//            return null;
        }
        return mappingService.mapEntityToDto(product);
    }

//    @Override
//    public ProductDTO getProductById(Long id) {
//        logger.info("Method  getProductById called with parameter: id= {}", id);
//        logger.warn("Method  getProductById called with parameter: id= {}", id);
//        logger.error("Method  getProductById called with parameter: id= {}", id);
//        Product product = repository.findById(id).orElse(null);
//        if (product == null || !product.isActive()) {
//            return null;
//        }
//        return mappingService.mapEntityToDto(product);
//    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO product) {
        return null;
    }

    @Override
    public ProductDTO deleteProduct(Long id) {
        return null;
    }

    @Override
    public ProductDTO deleteProductByTitle(String title) {
        return null;
    }

    @Override
    public ProductDTO restoreProductById(Long id) {
        return null;
    }

    @Override
    public long getProductsCount() {
        return 0;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return null;
    }

    @Override
    public BigDecimal getAveragePrice() {
        return null;
    }
}
