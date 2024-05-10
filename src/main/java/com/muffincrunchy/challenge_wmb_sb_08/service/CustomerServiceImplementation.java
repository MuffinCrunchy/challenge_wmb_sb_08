package com.muffincrunchy.challenge_wmb_sb_08.service;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.FilterCustomerRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Customer;
import com.muffincrunchy.challenge_wmb_sb_08.repository.CustomerRepository;
import com.muffincrunchy.challenge_wmb_sb_08.utils.specification.CustomerSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CustomerServiceImplementation implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getById(UUID id) {
        return customerRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Customer> getByFilter(FilterCustomerRequest request) {
        return customerRepository.findAll(CustomerSpecification.getSpecification(request));
    }

    @Override
    public Customer insert(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer update(Customer customer) {
        getById(customer.getId());
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public void updateMember(UUID id, Boolean isMember) {
        getById(id);
        customerRepository.updateStatus(id, isMember);
    }

    @Override
    public void delete(UUID id) {
        customerRepository.delete(getById(id));
    }
}
