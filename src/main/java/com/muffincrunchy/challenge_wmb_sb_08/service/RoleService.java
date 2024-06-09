package com.muffincrunchy.challenge_wmb_sb_08.service;

import com.muffincrunchy.challenge_wmb_sb_08.model.constant.UserRole;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Role;

public interface RoleService {

    Role getOrSave(UserRole userRole);
}
