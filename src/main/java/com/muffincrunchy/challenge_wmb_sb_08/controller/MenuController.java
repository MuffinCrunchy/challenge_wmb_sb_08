package com.muffincrunchy.challenge_wmb_sb_08.controller;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.CreateMenuRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.FilterMenuRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.PagingRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.UpdateMenuRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.response.CommonResponse;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.response.PagingResponse;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Menu;
import com.muffincrunchy.challenge_wmb_sb_08.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.muffincrunchy.challenge_wmb_sb_08.model.constant.ApiUrl.*;
import static com.muffincrunchy.challenge_wmb_sb_08.model.constant.ApiUrl.ID_PATH_URL;

@RequiredArgsConstructor
@RestController
@RequestMapping(MENU_API_URL)
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<Menu>>> getMenus(
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
        Page<Menu> menus = menuService.getAll(pagingRequest);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(menus.getTotalPages())
                .totalElements(menus.getTotalElements())
                .page(menus.getPageable().getPageNumber()+1)
                .size(menus.getPageable().getPageSize())
                .hasNext(menus.hasNext())
                .hasPrevious(menus.hasPrevious())
                .build();

        CommonResponse<List<Menu>> response = CommonResponse.<List<Menu>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("get all data success")
                .data(menus.getContent())
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping(ID_PATH_URL)
    public ResponseEntity<CommonResponse<Menu>> getMenu(@PathVariable UUID id) {
        Menu menu = menuService.getById(id);
        CommonResponse<Menu> response = CommonResponse.<Menu>builder()
                .statusCode(HttpStatus.OK.value())
                .message("get all data success")
                .data(menu)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(SEARCH_PATH_URL)
    public ResponseEntity<CommonResponse<List<Menu>>> searchMenu(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "name", required = false) String name
    ) {
        FilterMenuRequest request = FilterMenuRequest.builder()
                .name(name)
                .build();

        PagingRequest pagingRequest = PagingRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .build();
        Page<Menu> menus = menuService.getByFilter(pagingRequest, request);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(menus.getTotalPages())
                .totalElements(menus.getTotalElements())
                .page(menus.getPageable().getPageNumber()+1)
                .size(menus.getPageable().getPageSize())
                .hasNext(menus.hasNext())
                .hasPrevious(menus.hasPrevious())
                .build();

        CommonResponse<List<Menu>> response = CommonResponse.<List<Menu>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("get all data success")
                .data(menus.getContent())
                .paging(pagingResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CommonResponse<Menu>> createMenu(@RequestBody CreateMenuRequest menu) {
        Menu newMenu = menuService.create(menu);
        CommonResponse<Menu> response = CommonResponse.<Menu>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("save data success")
                .data(newMenu)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Menu>> updateMenu(@RequestBody UpdateMenuRequest menu) {
        Menu updateMenu = menuService.update(menu);
        CommonResponse<Menu> response = CommonResponse.<Menu>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("update data success")
                .data(updateMenu)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(ID_PATH_URL)
    public ResponseEntity<CommonResponse<String>> deleteMenu(@PathVariable("id") UUID id) {
        menuService.delete(id);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message("delete data success")
                .build();
        return ResponseEntity.ok(response);
    }
}
