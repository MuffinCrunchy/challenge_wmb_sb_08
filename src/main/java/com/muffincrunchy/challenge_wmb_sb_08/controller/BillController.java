package com.muffincrunchy.challenge_wmb_sb_08.controller;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.BillRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.PagingRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.response.BillResponse;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.response.CommonResponse;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.response.PagingResponse;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Customer;
import com.muffincrunchy.challenge_wmb_sb_08.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CommonResponse<List<BillResponse>>> getAllBills(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "transactionDate") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction
    ) {
        PagingRequest request = PagingRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .build();
        Page<BillResponse> result = billService.getAll(request);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .page(result.getPageable().getPageNumber() + 1)
                .size(result.getPageable().getPageSize())
                .hasNext(result.hasNext())
                .hasPrevious(result.hasPrevious())
                .build();

        CommonResponse<List<BillResponse>> response = CommonResponse.<List<BillResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("get all data success")
                .data(result.getContent())
                .paging(pagingResponse)
                .build();
        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<BillResponse>> getBillById(@PathVariable UUID id) {
        BillResponse billResponse = billService.getById(id);
        CommonResponse<BillResponse> response = CommonResponse.<BillResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("get all data success")
                .data(billResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CommonResponse<?>> createBill(@RequestBody BillRequest billRequest) {
        BillResponse billResponse = billService.create(billRequest);
        CommonResponse<BillResponse> response = CommonResponse.<BillResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("save data success")
                .data(billResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
