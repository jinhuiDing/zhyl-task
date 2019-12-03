package com.gbicc.controller;

import com.gbicc.mapper.TaskMapper;
import com.gbicc.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: UpdateController
 * @author: ding
 * @date: 2019/12/3 13:46
 * @version: 1.0
 */


@RestController
public class UpdateController {

    @Autowired
    TaskMapper taskMapper;


    @GetMapping("user/{code}")
    public ResponseEntity update(@PathVariable("code") String code) {
        User user = new User();
        user.setId(1L);
        user.setCode(code);
        taskMapper.save(user);
        return ResponseEntity.ok(null);
    }
}
