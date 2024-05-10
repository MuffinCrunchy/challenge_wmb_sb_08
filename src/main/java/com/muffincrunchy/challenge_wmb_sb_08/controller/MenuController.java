package com.muffincrunchy.challenge_wmb_sb_08.controller;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.FilterMenuRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Menu;
import com.muffincrunchy.challenge_wmb_sb_08.service.MenuService;
import lombok.RequiredArgsConstructor;
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
    public List<Menu> getMenus() {
        return menuService.getAll();
    }

    @GetMapping(ID_PATH_URL)
    public Menu getMenu(@PathVariable UUID id) {
        return menuService.getById(id);
    }

    @GetMapping(SEARCH_PATH_URL)
    public List<Menu> searchMenu(
            @RequestParam(name = "name", required = false) String name
    ) {
        FilterMenuRequest request = FilterMenuRequest.builder()
                .name(name)
                .build();
        return menuService.getByFilter(request);
    }

    @PostMapping
    public Menu insertMenu(@RequestBody Menu menu) {
        return menuService.insert(menu);
    }

    @PutMapping
    public Menu updateMenu(@RequestBody Menu menu) {
        return menuService.update(menu);
    }

    @DeleteMapping(ID_PATH_URL)
    public String deleteMenu(@PathVariable("id") UUID id) {
        menuService.delete(id);
        return String.format("{ Status: Delete Id %s Success }", id);
    }
}
