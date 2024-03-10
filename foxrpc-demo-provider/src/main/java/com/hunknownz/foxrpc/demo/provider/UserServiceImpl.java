package com.hunknownz.foxrpc.demo.provider;

import com.hunknownz.foxrpc.core.annotation.FoxProvider;
import com.hunknownz.foxrpc.demo.api.User;
import com.hunknownz.foxrpc.demo.api.UserService;
import org.springframework.stereotype.Component;

@Component
@FoxProvider
public class UserServiceImpl implements UserService {
    @Override
    public User findById(int id) {
        return new User(id, "KK-" + System.currentTimeMillis());
    }
}
