package io.hbprotoss.web.service;

import io.hbprotoss.web.model.UserModel;

/**
 * Created by hbprotoss on 9/27/15.
 */
public interface UserService {
    UserModel getUserByName(String name);
}
