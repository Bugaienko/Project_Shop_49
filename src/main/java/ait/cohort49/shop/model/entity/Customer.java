package ait.cohort49.shop.model.entity;

import java.util.Objects;

/**
 * @author Sergey Bugaenko
 * {@code @date} 10.12.2024
 */

public class Customer {

    private Long id;
    private String name;
    private boolean active;


    @Override
    public String toString() {
        return String.format("Customer: id - %d, name - %s, active - %s",
                id, name, active ? "yes" : "no");
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Customer customer)) return false;

        return active == customer.active && Objects.equals(id, customer.id) && Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Boolean.hashCode(active);
        return result;
    }



    public Long getId() {
        return id;
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
}
