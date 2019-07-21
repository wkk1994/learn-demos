package com.wkk.demo.references.softsample.cache;

import com.wkk.demo.references.softsample.entity.Employee;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Hashtable;

import static com.sun.xml.internal.bind.v2.ClassFactory.cleanCache;

/**
 * @Description
 * @Author wangkunkun
 * @Date 2018/06/12 22:00
 **/
public class EmployeeCache  {
    private static  EmployeeCache cache;// 一个Cache实例
    private Hashtable<String,EmployeeRef> employeeRefs;// 用于Chche内容的存储
    private ReferenceQueue<Employee> queue;// 垃圾Reference的队列
    private class EmployeeRef extends SoftReference<Employee> {

        private String _key;

        // 继承SoftReference，使得每一个实例都具有可识别的标识。
        // 并且该标识与其在HashMap内的key相同。
        public EmployeeRef(Employee referent, ReferenceQueue<? super Employee> q) {
            super(referent, q);
            this._key=referent.getId();
        }
    }

    // 构建一个缓存器实例
    private EmployeeCache() {
        employeeRefs = new Hashtable<String,EmployeeRef>();
        queue = new ReferenceQueue<Employee>();
    }

    // 取得缓存器实例
    public static EmployeeCache getInstance(){
        if(cache == null){
            return new EmployeeCache();
        }
        return cache;
    }

    /**
     * @description 缓存employee对象
     * @param employee
     */
    public void cacheEmployee(Employee employee){
        cleanCache();// 清除垃圾引用
        EmployeeRef ref = new EmployeeRef(employee, queue);
        employeeRefs.put(employee.getId(), ref);
    }

    /**
     * @description 根据id获取实例对象
     * @param id
     * @return
     */
    public Employee getEmployee(String id){
        Employee employee = null;
        if(employeeRefs != null && employeeRefs.contains(id)){
            EmployeeRef employeeRef = employeeRefs.get(id);
            employee = employeeRef.get();
        }
        if(employee == null){
            System.out.println("不存在缓存中实体对象新建");
            employee = new Employee();
            employee.setId(String.valueOf(Math.random()));
            cacheEmployee(employee);
        }
        return employee;
    }

    /**
     * @description 清除失效引用
     */
    public void cleanCache(){
        EmployeeRef ref = null;
        while((ref = (EmployeeRef) queue.poll()) != null){
            employeeRefs.remove(ref._key);
        }
    }

    /**
     * @description  清除Cache内的全部内容
     */
    public void clearCache() {
        cleanCache();
        employeeRefs.clear();
        System.gc();
        System.runFinalization();
    }
    public static Employee buildEmployee(){
        int id = (int) Math.random();
        Employee employee = new Employee();
        employee.setId(String.valueOf(id));
        return employee;
    }
}
