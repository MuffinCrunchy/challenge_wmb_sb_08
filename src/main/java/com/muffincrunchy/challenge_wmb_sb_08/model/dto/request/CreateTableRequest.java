package com.muffincrunchy.challenge_wmb_sb_08.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTableRequest {

    @NotBlank(message = "name is required")
    private String name;
}
