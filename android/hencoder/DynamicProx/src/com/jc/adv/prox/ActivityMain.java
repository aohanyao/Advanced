package com.jc.adv.prox;

import java.lang.reflect.Proxy;

public class ActivityMain {
    public static void main(String[] arg) {
        LoginService newProxyInstance = (LoginService) Proxy.newProxyInstance(LoginService.class.getClassLoader(),
                new Class[]{LoginService.class},
                (proxy, method, args) -> {
                    // 在这里进行实现
                    System.out.println("doing in proxy");

                    return "this is proxy invoke result";
                });

        System.out.println(newProxyInstance.login());
    }
}
