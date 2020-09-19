package com.lwhtarena.rpc.core.network;

/**
 * @author liwh
 * @Title: RpcResponse
 * @Package com.lwhtarena.rpc.core.network
 * @Description: RPC结果
 * @Version 1.0.0
 * @date 2020/9/19 22:29
 */
public class RpcResponse {

    private String result;

    private String interfaceMethodIdentify;

    private String requestId;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static RpcResponse create(String result, String interfaceMethodIdentify, String requestId) {
        RpcResponse response = new RpcResponse();
        response.setResult(result);
        response.setInterfaceMethodIdentify(interfaceMethodIdentify);
        response.setRequestId(requestId);
        return response;
    }

    public String getInterfaceMethodIdentify() {
        return interfaceMethodIdentify;
    }

    public void setInterfaceMethodIdentify(String interfaceMethodIdentify) {
        this.interfaceMethodIdentify = interfaceMethodIdentify;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "RpcResponse{" +
                "result='" + result + '\'' +
                ", interfaceMethodIdentify='" + interfaceMethodIdentify + '\'' +
                ", requestId='" + requestId + '\'' +
                '}';
    }
}
