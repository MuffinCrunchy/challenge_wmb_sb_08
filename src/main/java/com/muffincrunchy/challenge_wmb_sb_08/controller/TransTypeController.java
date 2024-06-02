package com.muffincrunchy.challenge_wmb_sb_08.controller;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.CreateTransTypeRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.UpdateTransTypeRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.response.CommonResponse;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.TransType;
import com.muffincrunchy.challenge_wmb_sb_08.service.TransTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.muffincrunchy.challenge_wmb_sb_08.model.constant.ApiUrl.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(TRANS_TYPE_API_URL)
public class TransTypeController {

    private final TransTypeService transTypeService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<TransType>>> getTransTypes() {
        List<TransType> transTypes = transTypeService.getAll();
        CommonResponse<List<TransType>> response = CommonResponse.<List<TransType>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("get all data success")
                .data(transTypes)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(ID_PATH_URL)
    public ResponseEntity<CommonResponse<TransType>> getTransType(@PathVariable String id) {
        TransType transType = transTypeService.getById(id);
        CommonResponse<TransType> response = CommonResponse.<TransType>builder()
                .statusCode(HttpStatus.OK.value())
                .message("get all data success")
                .data(transType)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CommonResponse<TransType>> createTransType(@RequestBody CreateTransTypeRequest transType) {
        TransType newTransType = transTypeService.create(transType);
        CommonResponse<TransType> response = CommonResponse.<TransType>builder()
                .statusCode(HttpStatus.OK.value())
                .message("save data success")
                .data(newTransType)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<TransType>> updateTransType(@RequestBody UpdateTransTypeRequest transType) {
        TransType updateTransType = transTypeService.update(transType);
        CommonResponse<TransType> response = CommonResponse.<TransType>builder()
                .statusCode(HttpStatus.OK.value())
                .message("update data success")
                .data(updateTransType)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(ID_PATH_URL)
    public ResponseEntity<CommonResponse<String>> deleteTransType(@PathVariable("id") String id) {
        transTypeService.delete(id);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("delete data success")
                .build();
        return ResponseEntity.ok(response);
    }
}
