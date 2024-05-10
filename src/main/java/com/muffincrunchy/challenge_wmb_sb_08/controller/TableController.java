package com.muffincrunchy.challenge_wmb_sb_08.controller;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.FilterTableRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Table;
import com.muffincrunchy.challenge_wmb_sb_08.service.TableService;
import lombok.RequiredArgsConstructor;
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
    public List<Table> getTables() {
        return tableService.getAll();
    }

    @GetMapping(ID_PATH_URL)
    public Table getTable(@PathVariable UUID id) {
        return tableService.getById(id);
    }

    @GetMapping(SEARCH_PATH_URL)
    public List<Table> searchTable(
            @RequestParam(name = "name", required = false) String name
    ) {
        FilterTableRequest request = FilterTableRequest.builder()
                .name(name)
                .build();
        return tableService.getByFilter(request);
    }

    @PostMapping
    public Table insertTable(@RequestBody Table table) {
        return tableService.insert(table);
    }

    @PutMapping
    public Table updateTable(@RequestBody Table table) {
        return tableService.update(table);
    }

    @DeleteMapping(ID_PATH_URL)
    public String deleteTable(@PathVariable("id") UUID id) {
        tableService.delete(id);
        return String.format("{ Status: Delete Id %s Success }", id);
    }
}
