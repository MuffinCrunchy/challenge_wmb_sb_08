package com.muffincrunchy.challenge_wmb_sb_08.service;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.FilterCustomerRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    public List<Customer> getAll();
    public Customer getById (UUID id);
    public List<Customer> getByFilter (FilterCustomerRequest request);
    public Customer insert(Customer customer);
    public Customer update(Customer customer);
    public void updateMember(UUID id, Boolean isMember);
    public void delete(UUID id);
}
