package com.wdev.avanade_dio_api.service;

import com.wdev.avanade_dio_api.model.User;

public interface UserService {
    User FindById(Long id);

    User create(User user);

    User update(Long id,User user);

    void delete(Long id);
}
