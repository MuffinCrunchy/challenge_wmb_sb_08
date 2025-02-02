package com.muffincrunchy.challenge_wmb_sb_08.repository;

import com.muffincrunchy.challenge_wmb_sb_08.model.constant.UserRole;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRole(UserRole role);
}
