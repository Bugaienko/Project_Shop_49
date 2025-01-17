package ait.cohort49.shop.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Sergey Bugaenko
 * {@code @date} 12.12.2024
 */

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;


    @Override
    public String toString() {
        return String.format("Cart: id - %d, products count: %d", this.id,
                products == null ? 0 : products.size());
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Cart cart)) return false;

        return Objects.equals(id, cart.id) && Objects.equals(customer, cart.customer) && Objects.equals(products, cart.products);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(customer);
        result = 31 * result + Objects.hashCode(products);
        return result;
    }

    /*
    - Добавить продукт в корзину (если активный)
    - Получение всех продуктов, находящихся в корзине (активных)
    - Удалить продукт из корзины по его идентификатору
    - Полная очистка корзины (удаление всех продуктов)
    - Получение общей стоимости корзины (активных продуктов)
    - Получение средней стоимости товара в корзине (из активных продуктов)
     */

    public void addProduct(Product product) {
        if (product != null && product.isActive()) {
            products.add(product);
        }
    }

    public List<Product> getAllActiveProducts() {
        return products.stream()
                .filter(Product::isActive)
                .toList();
    }

    public Product removeBiId(Long id) {
       //  products.removeIf(p -> p.getId().equals(id)); // Если в корзине несколько продуктов с id=id, удалятся все.
        Optional<Product> optionalProduct = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (optionalProduct.isEmpty()) return null;

        Product product = optionalProduct.get();
        products.remove(product);
        return product;
    }

    public void clear() {
        products.clear();
    }

    public BigDecimal getTotalPrice() {
        if (products == null) return BigDecimal.ZERO;

        return products.stream()
                .filter(Product::isActive)
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    public BigDecimal getAveragePrice() {

        long countActive = products.stream()
                .filter(Product::isActive)
                .count();

        if (products == null || countActive == 0) return BigDecimal.ZERO;

        return getTotalPrice().divide(new BigDecimal(countActive), 2, RoundingMode.HALF_UP);

    }
}
