package com.muffincrunchy.challenge_wmb_sb_08.service.implementation;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.*;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Table;
import com.muffincrunchy.challenge_wmb_sb_08.repository.TableRepository;
import com.muffincrunchy.challenge_wmb_sb_08.service.TableService;
import com.muffincrunchy.challenge_wmb_sb_08.utils.specification.TableSpecification;
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
public class TableServiceImplementation implements TableService {

    private final TableRepository tableRepository;
    private final Validation validation;

    @Override
    public Page<Table> getAll(PagingRequest pagingRequest) {
        String sortBy = "name";
        if (pagingRequest.getPage() <= 0) {
            pagingRequest.setPage(1);
        }
        if (pagingRequest.getSortBy().equals("price")) {
            sortBy = pagingRequest.getSortBy();
        }

        Sort sort = Sort.by(Sort.Direction.fromString(pagingRequest.getDirection()), sortBy);
        Pageable pageable = PageRequest.of(pagingRequest.getPage()-1, pagingRequest.getSize(), sort);
        return tableRepository.findAll(pageable);
    }

    @Override
    public Table getById(UUID id) {
        return tableRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "data not found"));
    }

    @Override
    public Page<Table> getByFilter(PagingRequest pagingRequest, FilterTableRequest filterTableRequest) {
        String sortBy = "name";
        if (pagingRequest.getPage() <= 0) {
            pagingRequest.setPage(1);
        }
        if (pagingRequest.getSortBy().equals("price")) {
            sortBy = pagingRequest.getSortBy();
        }

        Sort sort = Sort.by(Sort.Direction.fromString(pagingRequest.getDirection()), sortBy);
        Pageable pageable = PageRequest.of(pagingRequest.getPage()-1, pagingRequest.getSize(), sort);
        Specification<Table> specification = TableSpecification.getSpecification(filterTableRequest);
        return tableRepository.findAll(specification, pageable);
    }

    @Override
    public Table create(CreateTableRequest table) {
        validation.validate(table);
        Table newTable = Table.builder()
                .name(table.getName())
                .build();
        return tableRepository.saveAndFlush(newTable);
    }

    @Override
    public Table update(UpdateTableRequest table) {
        Table updateTable = getById(table.getId());
        updateTable.setName(table.getName());
        return tableRepository.saveAndFlush(updateTable);
    }

    @Override
    public void delete(UUID id) {
        tableRepository.delete(getById(id));
    }
}
