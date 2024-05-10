package com.muffincrunchy.challenge_wmb_sb_08.repository;

import com.muffincrunchy.challenge_wmb_sb_08.model.entity.TransType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransTypeRepository extends JpaRepository<TransType, String> {
}
