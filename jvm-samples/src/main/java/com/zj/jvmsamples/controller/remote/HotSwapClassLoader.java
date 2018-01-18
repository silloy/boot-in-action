package com.zj.jvmsamples.controller.remote;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/17
 * Time: 14:32
 * CopyRight: Zhouji
 */
public class HotSwapClassLoader extends ClassLoader {
    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadByte(byte[] classByte) {
        return defineClass(null, classByte, 0, classByte.length);
    }
}
