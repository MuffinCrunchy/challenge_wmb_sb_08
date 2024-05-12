package com.muffincrunchy.challenge_wmb_sb_08.model.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class BillResponse {

    private UUID id;
    private Date transDate;
    private UUID customerId;
    private UUID tableId;
    private String transType;
    private List<BillDetailResponse> billDetails;
}
