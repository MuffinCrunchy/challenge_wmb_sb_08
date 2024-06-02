package com.muffincrunchy.challenge_wmb_sb_08.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UpdateTableRequest {
    @NotBlank(message = "id is required")
    private UUID id;

    @NotBlank(message = "name is required")
    private String name;
}
