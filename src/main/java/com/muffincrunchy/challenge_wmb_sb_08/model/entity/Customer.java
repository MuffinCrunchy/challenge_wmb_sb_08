package com.muffincrunchy.challenge_wmb_sb_08.model.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_no", nullable = false)
    private String phoneNo;

    @Column(name = "is_member", nullable = false)
    private Boolean isMember;
}
