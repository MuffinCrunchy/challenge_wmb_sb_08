package com.muffincrunchy.challenge_wmb_sb_08.service.implementation;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.CreateCustomerRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.FilterCustomerRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.PagingRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.UpdateCustomerRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Customer;
import com.muffincrunchy.challenge_wmb_sb_08.repository.CustomerRepository;
import com.muffincrunchy.challenge_wmb_sb_08.service.CustomerService;
import com.muffincrunchy.challenge_wmb_sb_08.utils.specification.CustomerSpecification;
import com.muffincrunchy.challenge_wmb_sb_08.utils.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CustomerServiceImplementation implements CustomerService {

    private final CustomerRepository customerRepository;
    private final Validation validation;

    @Override
    public Page<Customer> getAll(PagingRequest pagingRequest) {
        String sortBy = "name";
        if (pagingRequest.getPage() <= 0) {
            pagingRequest.setPage(1);
        }
        if (pagingRequest.getSortBy().equals("price")) {
            sortBy = pagingRequest.getSortBy();
        }

        Sort sort = Sort.by(Sort.Direction.fromString(pagingRequest.getDirection()), sortBy);
        Pageable pageable = PageRequest.of(pagingRequest.getPage()-1, pagingRequest.getSize(), sort);
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer getById(UUID id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "data not found"));
    }

    @Override
    public Page<Customer> getByFilter(PagingRequest pagingRequest, FilterCustomerRequest filterCustomerRequest) {
        String sortBy = "name";
        if (pagingRequest.getPage() <= 0) {
            pagingRequest.setPage(1);
        }
        if (pagingRequest.getSortBy().equals("price")) {
            sortBy = pagingRequest.getSortBy();
        }

        Sort sort = Sort.by(Sort.Direction.fromString(pagingRequest.getDirection()), sortBy);
        Pageable pageable = PageRequest.of(pagingRequest.getPage()-1, pagingRequest.getSize(), sort);
        Specification<Customer> specification = CustomerSpecification.getSpecification(filterCustomerRequest);
        return customerRepository.findAll(specification, pageable);
    }

    @Override
    public Customer create(CreateCustomerRequest customer) {
        validation.validate(customer);
        Customer newCustomer = Customer.builder()
                .name(customer.getName())
                .phoneNo(customer.getPhoneNo())
                .isMember(customer.getIsMember())
                .build();
        return customerRepository.saveAndFlush(newCustomer);
    }

    @Override
    public Customer update(UpdateCustomerRequest customer) {
        Customer updateCustomer = getById(customer.getId());
        updateCustomer.setName(customer.getName());
        updateCustomer.setPhoneNo(customer.getPhoneNo());
        updateCustomer.setIsMember(customer.getIsMember());
        return customerRepository.saveAndFlush(updateCustomer);
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
