package ait.cohort49.shop.model.dto;

import ait.cohort49.shop.model.entity.Cart;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * @author Sergey Bugaenko
 * {@code @date} 10.12.2024
 */


// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CustomerDTO {

    /*
    @JsonIdentityInfo
    {
        "id" : 1,
        "name" : "John Smith",
        "active": true,
        "cart" : {
            "id": 100,
            "customer": 1,
            "products": []
        }
    }

     */

    private Long id;
    private String name;
    private boolean active;

    @JsonManagedReference
    private CartDTO cart;


    @Override
    public String toString() {
        return String.format("CustomerDTO: id - %d, name - %s, active - %s, cart - %s",
                id, name, active ? "yes" : "no", cart);
    }

    public Long getId() {
        return id;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof CustomerDTO that)) return false;

        return active == that.active && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(cart, that.cart);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Boolean.hashCode(active);
        result = 31 * result + Objects.hashCode(cart);
        return result;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public CartDTO getCart() {
        return cart;
    }

    public void setCart(CartDTO cart) {
        this.cart = cart;
    }
}
