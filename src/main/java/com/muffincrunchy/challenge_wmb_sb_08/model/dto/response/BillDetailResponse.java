package com.muffincrunchy.challenge_wmb_sb_08.model.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BillDetailResponse {

    private UUID id;
    private UUID menuId;
    private Long price;
    private Integer qty;
}
