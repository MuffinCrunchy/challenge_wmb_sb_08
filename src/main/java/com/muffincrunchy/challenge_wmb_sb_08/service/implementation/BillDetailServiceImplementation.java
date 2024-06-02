package com.muffincrunchy.challenge_wmb_sb_08.service.implementation;

import com.muffincrunchy.challenge_wmb_sb_08.model.entity.BillDetail;
import com.muffincrunchy.challenge_wmb_sb_08.repository.BillDetailRepository;
import com.muffincrunchy.challenge_wmb_sb_08.service.BillDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BillDetailServiceImplementation implements BillDetailService {

    private final BillDetailRepository billDetailRepository;

    @Override
    public List<BillDetail> createBulk(List<BillDetail> billDetails) {
        return billDetailRepository.saveAllAndFlush(billDetails);
    }
}
