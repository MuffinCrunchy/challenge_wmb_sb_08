package com.muffincrunchy.challenge_wmb_sb_08.service.implementation;

import com.muffincrunchy.challenge_wmb_sb_08.model.constant.UserRole;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Role;
import com.muffincrunchy.challenge_wmb_sb_08.repository.RoleRepository;
import com.muffincrunchy.challenge_wmb_sb_08.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RoleServiceImplementation implements RoleService {

    private final RoleRepository roleRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Role getOrSave(UserRole userRole) {
        return roleRepository.findByRole(userRole).orElseGet(() -> roleRepository.saveAndFlush(Role.builder().role(userRole).build()));
    }
}
