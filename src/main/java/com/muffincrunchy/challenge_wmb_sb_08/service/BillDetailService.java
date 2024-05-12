package com.muffincrunchy.challenge_wmb_sb_08.service;

import com.muffincrunchy.challenge_wmb_sb_08.model.entity.BillDetail;

import java.util.List;

public interface BillDetailService {

    List<BillDetail> createBulk(List<BillDetail> billDetails);
}
