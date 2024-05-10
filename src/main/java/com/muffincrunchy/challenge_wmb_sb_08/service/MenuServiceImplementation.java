package com.muffincrunchy.challenge_wmb_sb_08.service;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.FilterMenuRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Menu;
import com.muffincrunchy.challenge_wmb_sb_08.repository.MenuRepository;
import com.muffincrunchy.challenge_wmb_sb_08.utils.specification.MenuSpesification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MenuServiceImplementation implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    @Override
    public Menu getById(UUID id) {
        return menuRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Menu> getByFilter(FilterMenuRequest request) {
        return menuRepository.findAll(MenuSpesification.getSpecification(request));
    }

    @Override
    public Menu insert(Menu menu) {
        return menuRepository.saveAndFlush(menu);
    }

    @Override
    public Menu update(Menu menu) {
        getById(menu.getId());
        return menuRepository.saveAndFlush(menu);
    }

    @Override
    public void delete(UUID id) {
        menuRepository.delete(getById(id));
    }
}
