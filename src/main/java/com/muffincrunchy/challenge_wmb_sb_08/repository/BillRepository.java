package com.muffincrunchy.challenge_wmb_sb_08.repository;

import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BillRepository extends JpaRepository<Bill, UUID> {
}
