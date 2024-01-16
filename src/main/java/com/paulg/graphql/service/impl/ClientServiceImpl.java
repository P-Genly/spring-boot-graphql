package com.paulg.graphql.service.impl;

import com.paulg.graphql.service.ClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Value( "${client.role}" )
    private String role;

    @Override
    public String getRole() {
        return this.role;
    }
}
