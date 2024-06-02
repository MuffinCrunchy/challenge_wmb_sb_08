package com.muffincrunchy.challenge_wmb_sb_08.controller;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.CreateTableRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.FilterTableRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.PagingRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.UpdateTableRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.response.CommonResponse;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.response.PagingResponse;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Table;
import com.muffincrunchy.challenge_wmb_sb_08.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.muffincrunchy.challenge_wmb_sb_08.model.constant.ApiUrl.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(TABLE_API_URL)
public class TableController {

    private final TableService tableService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<Table>>> getTables(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction
    ) {
        PagingRequest pagingRequest = PagingRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .build();
        Page<Table> tables = tableService.getAll(pagingRequest);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(tables.getTotalPages())
                .totalElements(tables.getTotalElements())
                .page(tables.getPageable().getPageNumber()+1)
                .size(tables.getPageable().getPageSize())
                .hasNext(tables.hasNext())
                .hasPrevious(tables.hasPrevious())
                .build();

        CommonResponse<List<Table>> response = CommonResponse.<List<Table>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("get all data success")
                .data(tables.getContent())
                .paging(pagingResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(ID_PATH_URL)
    public ResponseEntity<CommonResponse<Table>> getTable(@PathVariable UUID id) {
        Table table = tableService.getById(id);
        CommonResponse<Table> response = CommonResponse.<Table>builder()
                .statusCode(HttpStatus.OK.value())
                .message("get all data success")
                .data(table)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(SEARCH_PATH_URL)
    public ResponseEntity<CommonResponse<List<Table>>> searchTable(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "name", required = false) String name
    ) {
        FilterTableRequest request = FilterTableRequest.builder()
                .name(name)
                .build();

        PagingRequest pagingRequest = PagingRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .build();
        Page<Table> tables = tableService.getByFilter(pagingRequest, request);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(tables.getTotalPages())
                .totalElements(tables.getTotalElements())
                .page(tables.getPageable().getPageNumber()+1)
                .size(tables.getPageable().getPageSize())
                .hasNext(tables.hasNext())
                .hasPrevious(tables.hasPrevious())
                .build();

        CommonResponse<List<Table>> response = CommonResponse.<List<Table>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("get all data success")
                .data(tables.getContent())
                .paging(pagingResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CommonResponse<Table>> createTable(@RequestBody CreateTableRequest table) {
        Table newTable = tableService.create(table);
        CommonResponse<Table> response = CommonResponse.<Table>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("save data success")
                .data(newTable)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Table>> updateTable(@RequestBody UpdateTableRequest table) {
        Table updateTable = tableService.update(table);
        CommonResponse<Table> response = CommonResponse.<Table>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("update data success")
                .data(updateTable)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(ID_PATH_URL)
    public ResponseEntity<CommonResponse<String>> deleteTable(@PathVariable("id") UUID id) {
        tableService.delete(id);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("delete data success")
                .build();
        return ResponseEntity.ok(response);
    }
}
