package lk.omesh.possystemspring.entity.impl;

import jakarta.persistence.*;
import lk.omesh.possystemspring.util.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    private String customerID;
    private String name;
    private Gender gender;
    @Column(unique = true)
    private String gmail;
    private int phnNo;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
