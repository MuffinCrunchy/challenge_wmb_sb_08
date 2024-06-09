package com.muffincrunchy.challenge_wmb_sb_08.model.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RegisterResponse {

    private String username;
    private List<String> roles;
}
