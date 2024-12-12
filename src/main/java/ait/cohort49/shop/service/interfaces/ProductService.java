package ait.cohort49.shop.service.interfaces;

import ait.cohort49.shop.model.dto.ProductDTO;
import ait.cohort49.shop.model.entity.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Sergey Bugaenko
 * {@code @date} 11.12.2024
 */


public interface ProductService {

    ProductDTO saveProduct(ProductDTO product);

    List<ProductDTO> getAllActiveProducts();
    ProductDTO getProductById(Long id);

    ProductDTO updateProduct(Long id, ProductDTO product);
    ProductDTO deleteProduct(Long id);

    ProductDTO deleteProductByTitle(String title);

    ProductDTO restoreProductById(Long id);

    long getProductsCount();

    BigDecimal getTotalPrice();
    BigDecimal getAveragePrice();

}
