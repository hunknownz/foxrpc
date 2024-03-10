package com.hunknownz.foxrpc.core.api;

import lombok.Data;

@Data
public class RpcRequest {

    private String service; // com.hunknownz.foxrpc.demo.api.UserService
    private String method; // findById
    private Object[] args; // 100
}
