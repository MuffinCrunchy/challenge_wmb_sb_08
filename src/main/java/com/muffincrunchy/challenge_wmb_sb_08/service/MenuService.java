package com.muffincrunchy.challenge_wmb_sb_08.service;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.FilterMenuRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Menu;

import java.util.List;
import java.util.UUID;

public interface MenuService {

    public List<Menu> getAll();
    public Menu getById (UUID id);
    public List<Menu> getByFilter (FilterMenuRequest request);
    public Menu insert(Menu menu);
    public Menu update(Menu menu);
    public void delete(UUID id);
}
