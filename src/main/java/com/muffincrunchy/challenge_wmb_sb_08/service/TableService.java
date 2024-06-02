package com.muffincrunchy.challenge_wmb_sb_08.service;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.CreateTableRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.FilterTableRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.PagingRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.UpdateTableRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Table;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface TableService {

    public Page<Table> getAll(PagingRequest pagingRequest);
    public Table getById (UUID id);
    public Page<Table> getByFilter (PagingRequest pagingRequest, FilterTableRequest request);
    public Table create(CreateTableRequest table);
    public Table update(UpdateTableRequest table);
    public void delete(UUID id);
}
