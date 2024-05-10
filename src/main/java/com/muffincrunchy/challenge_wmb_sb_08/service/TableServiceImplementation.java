package com.muffincrunchy.challenge_wmb_sb_08.service;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.FilterTableRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Table;
import com.muffincrunchy.challenge_wmb_sb_08.repository.TableRepository;
import com.muffincrunchy.challenge_wmb_sb_08.utils.specification.TableSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TableServiceImplementation implements TableService {

    private final TableRepository tableRepository;

    @Override
    public List<Table> getAll() {
        return tableRepository.findAll();
    }

    @Override
    public Table getById(UUID id) {
        return tableRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Table> getByFilter(FilterTableRequest request) {
        return tableRepository.findAll(TableSpecification.getSpecification(request));
    }

    @Override
    public Table insert(Table table) {
        return tableRepository.saveAndFlush(table);
    }

    @Override
    public Table update(Table table) {
        getById(table.getId());
        return tableRepository.saveAndFlush(table);
    }

    @Override
    public void delete(UUID id) {
        tableRepository.delete(getById(id));
    }
}
