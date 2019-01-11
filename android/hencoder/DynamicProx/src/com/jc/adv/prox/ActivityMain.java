package com.jc.adv.prox;

import java.lang.reflect.Proxy;

public class ActivityMain {
    public static void main(String[] arg) {
        LoginService newProxyInstance = (LoginService) Proxy.newProxyInstance(LoginService.class.getClassLoader(),
                new Class[]{LoginService.class},
                (proxy, method, args) -> {
                    // 在这里进行实现
                    System.out.println("doing in proxy");
                    // 在这里 啪啦啪啦啦啦啦啦 的进行统一处理的话，
                    // 其实也是相当于实现了一个匿名类？
                    return "this is proxy invoke result";
                });

        System.out.println(newProxyInstance.login());
    }
}
