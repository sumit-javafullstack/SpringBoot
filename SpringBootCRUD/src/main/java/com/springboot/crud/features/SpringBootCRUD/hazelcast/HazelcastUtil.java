package com.springboot.crud.features.SpringBootCRUD.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.springboot.crud.features.SpringBootCRUD.model.employee.Employee;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class HazelcastUtil {

    @Value("${comm.hazelcast.cache.name.comm_prod_data:employeeDataMap}")
    @Setter
    private String employeeCacheMap;
    private final HazelcastInstance instance;


    public HazelcastUtil(@Qualifier("hazelcastInstance") HazelcastInstance hazelcastInstance) {
        this.instance = hazelcastInstance;
    }

    public final List<Employee>  getEmployeeDataFromCache(String key){

        IMap<String, List<Employee>> employeeMap = instance.getMap(employeeCacheMap);
        List<Employee> employeeList = null;

        if(employeeMap != null){
            employeeList = employeeMap.get(key);
          //  log.info("Retrieving {} cache value for key {}",employeeList,key);
        }
        if(employeeList==null){
            employeeList = new ArrayList<>();
        }
        return employeeList;
    }

    public final synchronized  void storeEmployeeDataInCache(String key,List<Employee> employeeList){
        IMap<String, List<Employee>> employeeMap = instance.getMap(employeeCacheMap);
        //log.info("update {} cache value for key {}",employeeList,key);
        employeeMap.set(key,employeeList);
    }

}
