package com.muffincrunchy.challenge_wmb_sb_08.service;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.FilterTableRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Table;

import java.util.List;
import java.util.UUID;

public interface TableService {

    public List<Table> getAll();
    public Table getById (UUID id);
    public List<Table> getByFilter (FilterTableRequest request);
    public Table insert(Table table);
    public Table update(Table table);
    public void delete(UUID id);
}
