package com.gbicc.mapper;

import com.gbicc.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description: TaskMapper
 * @author: ding
 * @date: 2019/11/25 15:05
 * @version: 1.0
 */


public interface TaskMapper extends JpaRepository<User, Long> {


}
