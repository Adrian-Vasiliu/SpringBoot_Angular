package org.springbootbackend.service.implementation;

import org.springbootbackend.exception.NotFoundException;
import org.springbootbackend.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public class GenericServiceImpl<Entity, Repository extends PagingAndSortingRepository<Entity, Long>>
        implements GenericService<Entity, Repository> {

    protected Repository repository;

    public GenericServiceImpl(Repository repository) {
        this.repository = repository;
    }

    public Entity save(Entity entity) {
        System.out.println("instance type:" + repository.getClass().getSimpleName());
        return repository.save(entity);
    }

    public Page<Entity> getPage(int pageNumber, int pageSize) {
        return repository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    public Entity getById(Long id) {
        Optional<Entity> optionalEntity = repository.findById(id);
        if (optionalEntity.isPresent()) {
            return optionalEntity.get();
        }
        String notFoundMessage = optionalEntity.getClass().getTypeName() + "  not found!";
        throw new NotFoundException(notFoundMessage);
    }

    public void delete(Long id) {
        getById(id);
        repository.deleteById(id);
    }

}
