package com.muffincrunchy.challenge_wmb_sb_08.service;

import com.muffincrunchy.challenge_wmb_sb_08.model.entity.TransType;

import java.util.List;

public interface TransTypeService {
    
    public List<TransType> getAll();
    public TransType getById (String id);
    public TransType insert(TransType transType);
    public TransType update(TransType transType);
    public void delete(String id);
}
