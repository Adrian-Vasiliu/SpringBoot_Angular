package org.springbootbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GenericService<Entity, Repository extends PagingAndSortingRepository<Entity, Long>> {
    Entity save(Entity entity);

    Page<Entity> getPage(int pageNumber, int pageSize);

    Entity getById(Long id);

    void delete(Long id);

}
