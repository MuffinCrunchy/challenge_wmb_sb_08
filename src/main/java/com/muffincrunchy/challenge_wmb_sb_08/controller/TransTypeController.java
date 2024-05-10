package com.muffincrunchy.challenge_wmb_sb_08.controller;

import com.muffincrunchy.challenge_wmb_sb_08.model.entity.TransType;
import com.muffincrunchy.challenge_wmb_sb_08.service.TransTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.muffincrunchy.challenge_wmb_sb_08.model.constant.ApiUrl.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(TRANS_TYPE_API_URL)
public class TransTypeController {

    private final TransTypeService transTypeService;

    @GetMapping
    public List<TransType> getTransTypes() {
        return transTypeService.getAll();
    }

    @GetMapping(ID_PATH_URL)
    public TransType getTransType(@PathVariable String id) {
        return transTypeService.getById(id);
    }

    @PostMapping
    public TransType insertTransType(@RequestBody TransType transType) {
        return transTypeService.insert(transType);
    }

    @PutMapping
    public TransType updateTransType(@RequestBody TransType transType) {
        return transTypeService.update(transType);
    }

    @DeleteMapping(ID_PATH_URL)
    public String deleteTransType(@PathVariable("id") String id) {
        transTypeService.delete(id);
        return String.format("{ Status: Delete Id %s Success }", id);
    }
}
