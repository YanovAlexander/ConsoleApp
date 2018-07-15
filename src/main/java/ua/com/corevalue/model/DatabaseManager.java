package ua.com.corevalue.model;

import ua.com.corevalue.model.entity.EmployeeData;

import java.util.List;

public interface DatabaseManager {

    Integer countUsers();

    EmployeeData findEmployeeByEmail(String email);

    void saveEmployee(EmployeeData newEmployee);

    List<EmployeeData> getSubordinatesByManagerEmail(String email);

    EmployeeData getCEOByEmployeeEmail(String email);
}
