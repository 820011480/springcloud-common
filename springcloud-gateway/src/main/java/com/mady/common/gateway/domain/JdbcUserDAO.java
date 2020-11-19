package com.mady.common.gateway.domain;

import com.mady.common.gateway.config.UserDAO;

import java.util.Arrays;
import java.util.List;

public class JdbcUserDAO implements UserDAO {
    @Override
    public List<String> getAllUserNames() {
        System.out.println("**** Getting usernames from RDBMS *****");
        return Arrays.asList("Siva", "Prasad", "Reddy");
    }
}
