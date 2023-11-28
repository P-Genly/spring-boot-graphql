package com.paulg.graphql.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Value( "${client.role}" )
    private String recoveredRole;

    @Override
    public String getAuthRole() {
        return this.recoveredRole;
    }
}
