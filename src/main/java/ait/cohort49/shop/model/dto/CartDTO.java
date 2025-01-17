package ait.cohort49.shop.model.dto;

import ait.cohort49.shop.model.entity.Customer;
import ait.cohort49.shop.model.entity.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

/**
 * @author Sergey Bugaenko
 * {@code @date} 12.12.2024
 */

// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CartDTO {

    private Long id;
    @JsonBackReference
    private CustomerDTO customerDTO;
    private List<ProductDTO> products;


    @Override
    public String toString() {
        return String.format("CartDTO: id - %d, products count: %d", this.id,
                products == null ? 0 : products.size());
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof CartDTO cartDTO)) return false;

        return Objects.equals(id, cartDTO.id) && Objects.equals(customerDTO, cartDTO.customerDTO) && Objects.equals(products, cartDTO.products);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(customerDTO);
        result = 31 * result + Objects.hashCode(products);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
