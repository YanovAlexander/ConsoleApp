package ua.com.corevalue.model;

import ua.com.corevalue.model.entity.EmployeeData;

import java.util.List;

public interface DatabaseManager {

    EmployeeData findEmployeeByEmail(String email);

    void saveEmployee(EmployeeData newEmployee);

    List<EmployeeData> getSubordinatesByManagerEmail(String email);

    EmployeeData getCEOByEmployeeEmail(String email);

    Boolean isCEOExist();

    Boolean isEmailExist(String email);
}
