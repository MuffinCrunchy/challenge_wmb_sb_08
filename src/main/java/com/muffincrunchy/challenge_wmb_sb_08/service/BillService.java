package com.muffincrunchy.challenge_wmb_sb_08.service;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.BillRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.PagingRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.response.BillResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface BillService {

    Page<BillResponse> getAll(PagingRequest pagingRequest);
    BillResponse getById(UUID id);
    BillResponse create(BillRequest request);
}
