package com.wkk.demo.jvm.classloader;

import java.io.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.List;

/**
 * @Description 自定义类加载器：从指定目录读取class文件
 * @Author Wangkunkun
 * @Date 2020/10/14 17:31
 */
public class CustomClassLoader extends ClassLoader {

    /**
     * 根目录
     */
    private String rootPath;

    /**
     * Finds the class with the specified <a href="#name">binary name</a>.
     * This method should be overridden by class loader implementations that
     * follow the delegation model for loading classes, and will be invoked by
     * the {@link #loadClass <tt>loadClass</tt>} method after checking the
     * parent class loader for the requested class.  The default implementation
     * throws a <tt>ClassNotFoundException</tt>.
     *
     * @param name The <a href="#name">binary name</a> of the class
     * @return The resulting <tt>Class</tt> object
     * @throws ClassNotFoundException If the class could not be found
     * @since 1.2
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = loadClassData(name);
        if(classData == null) {
            throw new ClassNotFoundException();
        }
        return defineClass(name, classData, 0, classData.length);
    }

    /**
     * 加载class文件
     * @param name
     * @return
     */
    private byte[] loadClassData(String name) {
        String classPath = rootPath + File.separatorChar
                + name.replace(".", File.separatorChar+"") + ".class";
        try {
            FileInputStream inputStream = new FileInputStream(new File(classPath));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int length = -1;
            while ((length = inputStream.read(bytes)) != -1){
                byteArrayOutputStream.write(bytes, 0, length);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public static void main(String[] args) throws Throwable {

        // public方法的Lookup
        MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();
        // 所有方法的Lookup
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        // 接收数组，返回一个List对象
        MethodType mt = MethodType.methodType(String.class, char.class, char.class);
        MethodHandle replace = publicLookup.findVirtual(String.class, "replace", mt);
        String output = (String) replace.invoke("jovo", Character.valueOf('o'), 'a');
        CustomClassLoader customClassLoader = new CustomClassLoader();
        customClassLoader.setRootPath("/Users/xujinxiu/Documents/project/project_code/learn-demos/demos/target/classes");
        try {
            Class<?> aClass = customClassLoader.findClass("com.wkk.demo.jvm.classloader.Test");
            System.out.println(aClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
