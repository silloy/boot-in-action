package com.zj.jvmsamples.controller.remote;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2018/1/17
 * Time: 16:19
 * CopyRight: Zhouji
 * <p>JavaClass执行工具</p>
 */
public class JavaClassExecuter {

    /**
     * 执行外部传过来的代表一个Java类的byte数组
     * 将输入类的byte数组中代表java.lang.System的CONSTANT_Utf8_info常量修改为劫持后HackSystem类
     * @param classByte 代表一个Java类的byte数组
     * @return 执行结果
     */
    public static String execute(byte[] classByte) {
        HackSystem.clearBuffer();
        ClassModifier cm = new ClassModifier(classByte);
        byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System", "com/zj/jvmsamples/controller/remote/HackSystem");
        HotSwapClassLoader loader =  new HotSwapClassLoader();
        Class clazz = loader.loadByte(modiBytes);
        try {
            Method method = clazz.getMethod("main", new Class[] {String[].class});
            method.invoke(null, new String[]{null});
        } catch (Throwable e) {
            e.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }
}
