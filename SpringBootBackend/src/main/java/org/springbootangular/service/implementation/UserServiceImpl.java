package org.springbootangular.service.implementation;

import org.springbootangular.model.User;
import org.springbootangular.repository.UserRepository;
import org.springbootangular.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, UserRepository> implements UserService {

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

}
