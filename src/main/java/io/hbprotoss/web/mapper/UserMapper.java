package io.hbprotoss.web.mapper;

import io.hbprotoss.web.model.UserModel;

/**
 * Created by hbprotoss on 9/26/15.
 */
public interface UserMapper {
    UserModel selectUserByName(String name);
}
