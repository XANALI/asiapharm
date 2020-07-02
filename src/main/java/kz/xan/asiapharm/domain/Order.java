package kz.xan.asiapharm.domain;

import javax.persistence.Entity;

@Entity
public class Order {
    private User user;
    private Good good;
    private String description;
}
