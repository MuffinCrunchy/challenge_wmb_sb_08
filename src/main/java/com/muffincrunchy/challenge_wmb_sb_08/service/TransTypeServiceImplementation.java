package com.muffincrunchy.challenge_wmb_sb_08.service;

import com.muffincrunchy.challenge_wmb_sb_08.model.entity.TransType;
import com.muffincrunchy.challenge_wmb_sb_08.repository.TransTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TransTypeServiceImplementation implements TransTypeService {

    private final TransTypeRepository transTypeRepository;

    @Override
    public List<TransType> getAll() {
        return transTypeRepository.findAll();
    }

    @Override
    public TransType getById(String id) {
        return transTypeRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public TransType insert(TransType transType) {
        return transTypeRepository.saveAndFlush(transType);
    }

    @Override
    public TransType update(TransType transType) {
        getById(transType.getId());
        return transTypeRepository.saveAndFlush(transType);
    }

    @Override
    public void delete(String id) {
        transTypeRepository.deleteById(id);
    }
}
