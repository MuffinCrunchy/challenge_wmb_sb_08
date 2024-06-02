package com.muffincrunchy.challenge_wmb_sb_08.service.implementation;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.BillRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.PagingRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.response.BillDetailResponse;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.response.BillResponse;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.*;
import com.muffincrunchy.challenge_wmb_sb_08.repository.BillRepository;
import com.muffincrunchy.challenge_wmb_sb_08.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class BillServiceImplementation implements BillService {

    private final BillRepository billRepository;
    private final MenuService menuService;
    private final CustomerService customerService;
    private final TableService tableService;
    private final TransTypeService transTypeService;
    private final BillDetailService billDetailService;

    @Override
    public Page<BillResponse> getAll(PagingRequest pagingRequest) {
        if (pagingRequest.getPage() <=0) {
            pagingRequest.setPage(1);
        }
        String sortBy = "transType";
        if (pagingRequest.getSortBy().equalsIgnoreCase("customerId") || pagingRequest.getSortBy().equalsIgnoreCase("tableId")) {
            sortBy = pagingRequest.getSortBy();
        }

        Sort sort = Sort.by(Sort.Direction.fromString(pagingRequest.getDirection()), sortBy);
        Pageable pageable = PageRequest.of((pagingRequest.getPage() - 1), pagingRequest.getSize(), sort);
        List<Bill> bills = billRepository.findAll();
        List<BillResponse> billResponses = bills.stream().map(bill -> {
            List<BillDetailResponse> billDetailResponses = bill.getBillDetails().stream()
                    .map(billDetail -> BillDetailResponse.builder()
                            .id(billDetail.getId())
                            .menuId(billDetail.getMenu().getId())
                            .price(billDetail.getPrice())
                            .qty(billDetail.getQty())
                            .build()).toList();
            return BillResponse.builder()
                    .id(bill.getId())
                    .transDate(bill.getTransDate())
                    .customerId(bill.getCustomer().getId())
                    .tableId(bill.getTable().getId())
                    .transType(bill.getTransType().getId())
                    .billDetails(billDetailResponses)
                    .build();
        }).toList();

        final int start = (int) pageable.getOffset();
        final int end = Math.min(start + pageable.getPageSize(), billResponses.size());
        return new PageImpl<>(billResponses.subList(start, end), pageable, billResponses.size());
    }

    @Override
    public BillResponse getById(UUID id) {
        Bill bill = billRepository.findById(id).orElseThrow(RuntimeException::new);
        List<BillDetailResponse> billDetailResponses = bill.getBillDetails().stream()
                .map(billDetail -> BillDetailResponse.builder()
                        .id(billDetail.getId())
                        .menuId(billDetail.getMenu().getId())
                        .price(billDetail.getPrice())
                        .qty(billDetail.getQty())
                        .build()).toList();
        return BillResponse.builder()
                .id(bill.getId())
                .transDate(bill.getTransDate())
                .customerId(bill.getCustomer().getId())
                .tableId(bill.getTable().getId())
                .transType(bill.getTransType().getId())
                .billDetails(billDetailResponses)
                .build();
    }

    @Override
    public BillResponse create(BillRequest request) {
        Customer customer = customerService.getById(request.getCustomerId());
        Table table = tableService.getById(request.getTableId());
        TransType transType = transTypeService.getById(request.getTransType());

        Bill bill = Bill.builder()
                .transDate(new Date())
                .customer(customer)
                .table(table)
                .transType(transType)
                .build();
        billRepository.saveAndFlush(bill);

        List<BillDetail> billDetails = request.getBillDetails().stream()
                .map(billDetail -> {
                    Menu menu = menuService.getById(billDetail.getMenuId());
                    return BillDetail.builder()
                            .bill(bill)
                            .menu(menu)
                            .price(menu.getPrice())
                            .qty(billDetail.getQty())
                            .build();
                }).toList();
        billDetailService.createBulk(billDetails);
        bill.setBillDetails(billDetails);

        List<BillDetailResponse> billDetailResponses = billDetails.stream()
                .map(billDetail -> BillDetailResponse.builder()
                        .id(billDetail.getId())
                        .menuId(billDetail.getMenu().getId())
                        .price(billDetail.getPrice())
                        .qty(billDetail.getQty())
                        .build()).toList();
        return BillResponse.builder()
                .id(bill.getId())
                .transDate(bill.getTransDate())
                .customerId(bill.getCustomer().getId())
                .tableId(bill.getTable().getId())
                .transType(bill.getTransType().getId())
                .billDetails(billDetailResponses)
                .build();
    }
}
