package com.muffincrunchy.challenge_wmb_sb_08.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "trans_types")
public class TransType {

    @Id
    @Column(length = 3)
    private String id;

    @Column(name = "description", nullable = false)
    private String description;
}
