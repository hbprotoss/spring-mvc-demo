package io.hbprotoss.web.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * Created by hbprotoss on 2/3/16.
 */
public interface AuthMapper {
    String selectPassword(
            @Param("username") String username
    );
}
