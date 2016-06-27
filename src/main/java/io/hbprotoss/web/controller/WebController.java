package io.hbprotoss.web.controller;

import io.hbprotoss.web.mapper.UserMapper;
import io.hbprotoss.web.model.JsonModel;
import io.hbprotoss.web.model.UserModel;
import io.hbprotoss.web.service.UserService;
import io.hbprotoss.web.validator.annotation.CheckDate;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.annotation.Secured;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hbprotoss on 9/21/15.
 */

@Controller
@RequestMapping("/")
public class WebController {

    private final Logger logger = LoggerFactory.getLogger(WebController.class);

    @Resource
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        logger.info("home() called");
        int a = 1/0;
        return "home";
    }

    @RequestMapping(value = "json", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "返回JSON MODEL", httpMethod = "GET", response = JsonModel.class, notes = "notes是什么")
    public JsonModel json(@ApiParam(required = true, name = "id", value = "ID") @RequestParam int id, @ApiParam(required = true, name = "name", value = "name") @RequestParam String name) {
        return new JsonModel(id, name);
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    @ResponseBody
    public JsonModel postJson(@RequestParam int id, @RequestParam String name) {
        return new JsonModel(id, name);
    }

    @RequestMapping(value = "/json/obj", method = RequestMethod.POST)
    @ResponseBody
    public JsonModel jsonObj(JsonModel model) {
        return model;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public UserModel user(@RequestParam String name) {
        UserModel userModel = userService.getUserByName(name);
        if (userModel == null) {
            logger.error("user not found");
            userModel = new UserModel();
        }
        return userModel;
    }

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Map<String, String>> mapInterface() {
        Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();

        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("code", "1");
        map1.put("name", "1");
        map.put("1", map1);

        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("code", "2");
        map2.put("name", "2");
        map.put("2", map2);
        return map;
    }

    @RequestMapping(value = "/check/date")
    @ResponseBody
    public String checkDate(@Valid UserModel model) {
        return model.getDate();
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse admin() {
        return new ApiResponse(200, "OK", null);
    }

    @RequestMapping(value = "/jadmin", method = RequestMethod.GET)
    @ResponseBody
    @Secured("ROLE_ADMIN")
    public ApiResponse jsonAdmin() {
        return new ApiResponse(200, "OK", null);
    }
}
