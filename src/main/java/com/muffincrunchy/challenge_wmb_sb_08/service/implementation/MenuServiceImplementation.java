package com.muffincrunchy.challenge_wmb_sb_08.service.implementation;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.CreateMenuRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.FilterMenuRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.PagingRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.UpdateMenuRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Menu;
import com.muffincrunchy.challenge_wmb_sb_08.repository.MenuRepository;
import com.muffincrunchy.challenge_wmb_sb_08.service.MenuService;
import com.muffincrunchy.challenge_wmb_sb_08.utils.specification.MenuSpesification;
import com.muffincrunchy.challenge_wmb_sb_08.utils.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MenuServiceImplementation implements MenuService {

    private final MenuRepository menuRepository;
    private final Validation validation;

    @Override
    public Page<Menu> getAll(PagingRequest pagingRequest) {
        String sortBy = "name";
        if (pagingRequest.getPage() <= 0) {
            pagingRequest.setPage(1);
        }
        if (pagingRequest.getSortBy().equals("price")) {
            sortBy = pagingRequest.getSortBy();
        }

        Sort sort = Sort.by(Sort.Direction.fromString(pagingRequest.getDirection()), sortBy);
        Pageable pageable = PageRequest.of(pagingRequest.getPage()-1, pagingRequest.getSize(), sort);
        return menuRepository.findAll(pageable);
    }

    @Override
    public Menu getById(UUID id) {
        return menuRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "data not found"));
    }

    @Override
    public Page<Menu> getByFilter(PagingRequest pagingRequest, FilterMenuRequest filterMenuRequest) {
        String sortBy = "name";
        if (pagingRequest.getPage() <= 0) {
            pagingRequest.setPage(1);
        }
        if (pagingRequest.getSortBy().equals("price")) {
            sortBy = pagingRequest.getSortBy();
        }

        Sort sort = Sort.by(Sort.Direction.fromString(pagingRequest.getDirection()), sortBy);
        Pageable pageable = PageRequest.of(pagingRequest.getPage()-1, pagingRequest.getSize(), sort);
        Specification<Menu> specification = MenuSpesification.getSpecification(filterMenuRequest);
        return menuRepository.findAll(specification, pageable);
    }

    @Override
    public Menu create(CreateMenuRequest menu) {
        validation.validate(menu);
        Menu newMenu = Menu.builder()
                .name(menu.getName())
                .price(menu.getPrice())
                .build();
        return menuRepository.saveAndFlush(newMenu);
    }

    @Override
    public Menu update(UpdateMenuRequest menu) {
        Menu updateMenu = getById(menu.getId());
        updateMenu.setName(menu.getName());
        updateMenu.setPrice(menu.getPrice());
        return menuRepository.saveAndFlush(updateMenu);
    }

    @Override
    public void delete(UUID id) {
        menuRepository.delete(getById(id));
    }
}
