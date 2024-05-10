package com.muffincrunchy.challenge_wmb_sb_08.repository;

import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TableRepository extends JpaRepository<Table, UUID>, JpaSpecificationExecutor<Table> {
}
