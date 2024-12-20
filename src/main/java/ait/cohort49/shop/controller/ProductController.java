package ait.cohort49.shop.controller;

import ait.cohort49.shop.exceprionHandling.Response;
import ait.cohort49.shop.exceprionHandling.exceptions.FirstTestException;
import ait.cohort49.shop.model.dto.ProductDTO;
import ait.cohort49.shop.model.entity.Product;
import ait.cohort49.shop.service.interfaces.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Sergey Bugaenko
 * {@code @date} 10.12.2024
 */

/*
Три уровня доступа:

1. Получение всех продуктов - Доступно всем пользователям, включая анонимных
2. Получение продукта по id - Доступно только аутентифицированным пользователям с любой ролью
3. Сохранение продукта в БД - Доступен только администраторам (т.е. пользователь с ролью ADMIN)
 */


// http://localhost:8080/products

    // /login - фронт endpoint
    // /api/login
    // /api/products

@RestController
@RequestMapping("/products")
@Tag(name = "Product controller", description = "Controller for operations with products")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    // Create: POST -> /products
    @Operation(summary = "Create product", description = "Add new product", tags = {"Product"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)),
                        @Content(mediaType = "application/xml", schema = @Schema(implementation = ProductDTO.class))
            })
    })
    @PostMapping
    public ProductDTO saveProduct(@Valid @RequestBody ProductDTO productDto) {
       return productService.saveProduct(productDto);
    }

    // Получение ресурса
    // GET /products/5
    // GET /products/1565
    // GET /products/55
    @Operation(summary = "Get product by id",  tags = {"Product"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)),
                    @Content(mediaType = "application/xml", schema = @Schema(implementation = ProductDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content) })
    @GetMapping("/{productId}")
    public ProductDTO getById(
            @Parameter(description = "The id that needs to be fetched", required = true)
            @PathVariable("productId")
            Long id) {

        return productService.getProductById(id);
    }

    // get:  GET /products
    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.getAllActiveProducts();
    }

    //products?id=3
//    @GetMapping
//    public Product getById(@RequestParam Long id) {
//        // Todo обращаемся к сервисе и запрашиваем продукт по id
//        return null;
//    }

    // Update: PUT -> /products/5
    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return productService.updateProduct(id, productDTO);
    }

    // Delete: DELETE -> /products/3
    @DeleteMapping("/{id}")
    public ProductDTO remove(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

    // Delete: DELETE -> /products/by-title?title=Banana
    @DeleteMapping("/by-title")
    public ProductDTO deleteProductByTitle(@RequestParam String title) {
        return productService.deleteProductByTitle(title);
    }

    // PUT -> /products/restore/25
    @PutMapping("/restore/{id}")
    public ProductDTO restoreProductById(@PathVariable Long id) {
        return productService.restoreProductById(id);
    }

    @GetMapping("/count")
    public long getProductsCount() {
        return productService.getProductsCount();
    }

    @GetMapping("/total-price")
    public BigDecimal getTotalPrice() {
        return productService.getTotalPrice();
    }

    @GetMapping("/average-price")
    public BigDecimal getAveragePrice() {
        return productService.getAveragePrice();
    }

    // Обработчик исключения в контроллере
    @ExceptionHandler(FirstTestException.class)
    public ResponseEntity<Response> handleException(FirstTestException exception) {
        Response response = new Response(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
