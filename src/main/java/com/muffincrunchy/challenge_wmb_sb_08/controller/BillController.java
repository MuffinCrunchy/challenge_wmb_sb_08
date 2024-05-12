package com.muffincrunchy.challenge_wmb_sb_08.controller;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.BillRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.response.BillResponse;
import com.muffincrunchy.challenge_wmb_sb_08.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.muffincrunchy.challenge_wmb_sb_08.model.constant.ApiUrl.BILL_API_URL;

@RequiredArgsConstructor
@RestController
@RequestMapping(BILL_API_URL)
public class BillController {

    private final BillService billService;

    @GetMapping
    public List<BillResponse> getAllBills() {
        return billService.getAll();
    }

    @GetMapping("/{id}")
    public BillResponse getBillById(@PathVariable UUID id) {
        return billService.getById(id);
    }

    @PostMapping
    public BillResponse insertBill(@RequestBody BillRequest billRequest) {
        return billService.insert(billRequest);
    }
}
