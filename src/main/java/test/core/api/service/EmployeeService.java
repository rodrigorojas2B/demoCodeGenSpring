package test.core.api.service;

import test.core.api.exception.CannotDeleteEmployeeException;

public interface EmployeeService {
    // ... otros métodos existentes ...

    void deleteEmployee(Long id) throws CannotDeleteEmployeeException;
}

