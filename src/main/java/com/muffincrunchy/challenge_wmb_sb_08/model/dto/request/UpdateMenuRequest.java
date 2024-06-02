package com.muffincrunchy.challenge_wmb_sb_08.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UpdateMenuRequest {
    @NotBlank(message = "id is required")
    private UUID id;

    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "price is required")
    private Long price;
}
