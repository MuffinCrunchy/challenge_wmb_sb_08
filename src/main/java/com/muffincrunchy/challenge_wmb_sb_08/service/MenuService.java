package com.muffincrunchy.challenge_wmb_sb_08.service;

import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.CreateMenuRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.FilterMenuRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.PagingRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.request.UpdateMenuRequest;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.Menu;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface MenuService {

    public Page<Menu> getAll(PagingRequest pagingRequest);
    public Menu getById (UUID id);
    public Page<Menu> getByFilter (PagingRequest pagingRequest, FilterMenuRequest request);
    public Menu create(CreateMenuRequest menu);
    public Menu update(UpdateMenuRequest menu);
    public void delete(UUID id);
}
