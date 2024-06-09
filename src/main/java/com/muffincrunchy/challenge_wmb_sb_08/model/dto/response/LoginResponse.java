package com.muffincrunchy.challenge_wmb_sb_08.model.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LoginResponse {

    private String username;
    private String token;
    private List<String> roles;
}
