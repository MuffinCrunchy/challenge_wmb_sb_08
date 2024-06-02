package com.muffincrunchy.challenge_wmb_sb_08.service.implementation;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.CreateTransTypeRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.UpdateTransTypeRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.TransType;
import com.muffincrunchy.challenge_wmb_sb_08.repository.TransTypeRepository;
import com.muffincrunchy.challenge_wmb_sb_08.service.TransTypeService;
import com.muffincrunchy.challenge_wmb_sb_08.utils.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TransTypeServiceImplementation implements TransTypeService {

    private final TransTypeRepository transTypeRepository;
    private final Validation validation;

    @Override
    public List<TransType> getAll() {
        return transTypeRepository.findAll();
    }

    @Override
    public TransType getById(String id) {
        return transTypeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "data not found"));
    }

    @Override
    public TransType create(CreateTransTypeRequest transType) {
        validation.validate(transType);
        TransType newTransType = TransType.builder()
                .description(transType.getDescription())
                .build();
        return transTypeRepository.saveAndFlush(newTransType);
    }

    @Override
    public TransType update(UpdateTransTypeRequest transType) {
        TransType updateTransType = getById(transType.getId());
        updateTransType.setDescription(transType.getDescription());
        return transTypeRepository.saveAndFlush(updateTransType);
    }

    @Override
    public void delete(String id) {
        transTypeRepository.deleteById(id);
    }
}
