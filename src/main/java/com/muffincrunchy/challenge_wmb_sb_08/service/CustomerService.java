package com.muffincrunchy.challenge_wmb_sb_08.service;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.CreateCustomerRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.FilterCustomerRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.PagingRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.UpdateCustomerRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Customer;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface CustomerService {

    public Page<Customer> getAll(PagingRequest pagingRequest);
    public Customer getById (UUID id);
    public Page<Customer> getByFilter (PagingRequest pagingRequest, FilterCustomerRequest request);
    public Customer create(CreateCustomerRequest customer);
    public Customer update(UpdateCustomerRequest customer);
    public void updateMember(UUID id, Boolean isMember);
    public void delete(UUID id);
}
