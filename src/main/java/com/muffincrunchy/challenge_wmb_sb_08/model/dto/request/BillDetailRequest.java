package com.muffincrunchy.challenge_wmb_sb_08.model.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class BillDetailRequest {

    private UUID menuId;
    private Integer qty;
}
