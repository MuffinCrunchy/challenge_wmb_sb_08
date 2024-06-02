package com.muffincrunchy.challenge_wmb_sb_08.service;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.CreateTransTypeRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.UpdateTransTypeRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.TransType;

import java.util.List;

public interface TransTypeService {
    
    public List<TransType> getAll();
    public TransType getById (String id);
    public TransType create(CreateTransTypeRequest transType);
    public TransType update(UpdateTransTypeRequest transType);
    public void delete(String id);
}
