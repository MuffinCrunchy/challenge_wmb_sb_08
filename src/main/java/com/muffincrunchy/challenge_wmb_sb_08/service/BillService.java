package com.muffincrunchy.challenge_wmb_sb_08.service;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.BillRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.response.BillResponse;

import java.util.List;
import java.util.UUID;

public interface BillService {

    List<BillResponse> getAll();
    BillResponse getById(UUID id);
    BillResponse insert(BillRequest request);
}
