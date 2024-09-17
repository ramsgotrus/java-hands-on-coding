package com.example.SIMPLECRUDApp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="GUEST")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String displayName;
}
