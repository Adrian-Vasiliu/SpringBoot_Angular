package org.springbootbackend.service.implementation;

import org.springbootbackend.model.User;
import org.springbootbackend.repository.UserRepository;
import org.springbootbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, UserRepository> implements UserService {

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

}
