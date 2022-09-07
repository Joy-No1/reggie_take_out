
package com.xml.reggie.common;

/**
 * 基于ThreadLocal封装工具类,用户保存和获取当前登陆的用户id
 */


public class BaseContext {

    private static ThreadLocal<Long> threadLocal=new InheritableThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }


    public static Long  getCurrentId(){
        return threadLocal.get();
    }
}
