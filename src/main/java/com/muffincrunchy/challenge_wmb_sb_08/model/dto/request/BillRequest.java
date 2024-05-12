package com.muffincrunchy.challenge_wmb_sb_08.model.dto.request;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BillRequest {

    private UUID customerId;
    private UUID tableId;
    private String transType;
    private List<BillDetailRequest> billDetails;
}
