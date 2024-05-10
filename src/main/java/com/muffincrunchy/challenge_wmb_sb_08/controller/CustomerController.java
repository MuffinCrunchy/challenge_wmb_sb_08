package com.muffincrunchy.challenge_wmb_sb_08.controller;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.FilterCustomerRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Customer;
import com.muffincrunchy.challenge_wmb_sb_08.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.muffincrunchy.challenge_wmb_sb_08.model.constant.ApiUrl.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(CUSTOMER_API_URL)
public class CustomerController {

    public final CustomerService customerService;

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getAll();
    }

    @GetMapping(ID_PATH_URL)
    public Customer getCustomer(@PathVariable UUID id) {
        return customerService.getById(id);
    }

    @GetMapping(SEARCH_PATH_URL)
    public List<Customer> searchCustomer(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "is_member", required = false) Boolean isMember
    ) {
        FilterCustomerRequest request = FilterCustomerRequest.builder()
                .name(name)
                .isMember(isMember)
                .build();
        return customerService.getByFilter(request);
    }

    @PostMapping
    public Customer insertCustomer(@RequestBody Customer customer) {
        return customerService.insert(customer);
    }

    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.update(customer);
    }

    @PutMapping(ID_PATH_URL)
    public String updateMemberCustomer(@PathVariable("id") UUID id, @RequestParam("is_member") Boolean isMember) {
        customerService.updateMember(id, isMember);
        return String.format("{ Status: Update Id %s Success }", id);
    }

    @DeleteMapping(ID_PATH_URL)
    public String deleteCustomer(@PathVariable("id") UUID id) {
        customerService.delete(id);
        return String.format("{ Status: Delete Id %s Success }", id);
    }
}
