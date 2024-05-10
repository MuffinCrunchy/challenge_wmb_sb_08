package com.muffincrunchy.challenge_wmb_sb_08.repository;

import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Transactional
@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID>, JpaSpecificationExecutor<Customer> {
    @Modifying
    @Query(value = "UPDATE customers SET is_member = :isMember WHERE id = :id", nativeQuery = true)
    void updateStatus(@Param("id") UUID id, @Param("isMember") boolean isMember);
}
