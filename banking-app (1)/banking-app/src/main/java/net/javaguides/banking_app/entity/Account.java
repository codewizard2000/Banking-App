package net.javaguides.banking_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor// default constructor
@AllArgsConstructor// custom constructor
@Table(name="accounts")
@Entity  // makes this class as jpa entity
public class Account {
    @Id// makes primary key id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_holder_name") // column names
    private String accountHolderName;
    private double balance;
}
