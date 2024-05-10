package com.muffincrunchy.challenge_wmb_sb_08.model.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FilterCustomerRequest {

    private String name;
    private Boolean isMember;
}
