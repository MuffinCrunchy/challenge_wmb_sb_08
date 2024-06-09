package com.muffincrunchy.challenge_wmb_sb_08.service;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.response.JwtClaims;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.UserAccount;

public interface JwtService {

    String generateToken(UserAccount userAccount);
    boolean verifyJwtToken(String token);
    JwtClaims getClaimsByToken(String token);
}
