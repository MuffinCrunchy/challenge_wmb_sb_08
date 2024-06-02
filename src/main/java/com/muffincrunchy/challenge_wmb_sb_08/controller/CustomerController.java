package com.muffincrunchy.challenge_wmb_sb_08.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.CreateCustomerRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.FilterCustomerRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.PagingRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.UpdateCustomerRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.response.CommonResponse;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.response.PagingResponse;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Customer;
import com.muffincrunchy.challenge_wmb_sb_08.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.muffincrunchy.challenge_wmb_sb_08.model.constant.ApiUrl.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(CUSTOMER_API_URL)
public class CustomerController {

    private final CustomerService customerService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<CommonResponse<List<Customer>>> getCustomers(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction
    ) {
        PagingRequest pagingRequest = PagingRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .build();
        Page<Customer> customers = customerService.getAll(pagingRequest);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(customers.getTotalPages())
                .totalElements(customers.getTotalElements())
                .page(customers.getPageable().getPageNumber()+1)
                .size(customers.getPageable().getPageSize())
                .hasNext(customers.hasNext())
                .hasPrevious(customers.hasPrevious())
                .build();

        CommonResponse<List<Customer>> response = CommonResponse.<List<Customer>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("get all data success")
                .data(customers.getContent())
                .paging(pagingResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(ID_PATH_URL)
    public ResponseEntity<CommonResponse<Customer>> getCustomer(@PathVariable UUID id) {
        Customer customer = customerService.getById(id);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message("get all data success")
                .data(customer)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(SEARCH_PATH_URL)
    public ResponseEntity<CommonResponse<List<Customer>>> searchCustomer(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "is_member", required = false) Boolean isMember
    ) {
        FilterCustomerRequest filterCustomerRequest = FilterCustomerRequest.builder()
                .name(name)
                .isMember(isMember)
                .build();

        PagingRequest pagingRequest = PagingRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .build();
        Page<Customer> customers = customerService.getByFilter(pagingRequest, filterCustomerRequest);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(customers.getTotalPages())
                .totalElements(customers.getTotalElements())
                .page(customers.getPageable().getPageNumber()+1)
                .size(customers.getPageable().getPageSize())
                .hasNext(customers.hasNext())
                .hasPrevious(customers.hasPrevious())
                .build();

        CommonResponse<List<Customer>> response = CommonResponse.<List<Customer>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("get all data success")
                .data(customers.getContent())
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CommonResponse<?>> createCustomer(@RequestBody CreateCustomerRequest customer) {
        Customer newCustomer = customerService.create(customer);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("save data success")
                .data(newCustomer)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Customer>> updateCustomer(@RequestBody UpdateCustomerRequest customer) {
        Customer updateCustomer = customerService.update(customer);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message("update data success")
                .data(updateCustomer)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping(ID_PATH_URL)
    public ResponseEntity<CommonResponse<String>> updateMemberCustomer(@PathVariable("id") UUID id, @RequestParam("is_member") Boolean isMember) {
        customerService.updateMember(id, isMember);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("update data success")
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(ID_PATH_URL)
    public ResponseEntity<CommonResponse<String>> deleteCustomer(@PathVariable("id") UUID id) {
        customerService.delete(id);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("delete data success")
                .build();
        return ResponseEntity.ok(response);
    }
}
