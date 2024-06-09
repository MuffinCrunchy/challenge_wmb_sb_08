package com.muffincrunchy.challenge_wmb_sb_08.service;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.AuthRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.response.LoginResponse;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.response.RegisterResponse;

public interface AuthService {

    RegisterResponse register(AuthRequest request);
    RegisterResponse registerAdmin(AuthRequest request);
    LoginResponse login(AuthRequest request);
}
