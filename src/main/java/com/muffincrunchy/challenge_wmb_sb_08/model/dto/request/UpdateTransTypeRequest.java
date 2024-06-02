package com.muffincrunchy.challenge_wmb_sb_08.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UpdateTransTypeRequest {

    @NotBlank(message = "id is required")
    private String id;

    @NotBlank(message = "description is required")
    private String description;
}
