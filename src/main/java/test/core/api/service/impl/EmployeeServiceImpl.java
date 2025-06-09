package test.core.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.core.api.exception.CannotDeleteEmployeeException;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // ... otros métodos existentes ...

    @Override
    public void deleteEmployee(Long id) throws CannotDeleteEmployeeException {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null && "Femenino".equalsIgnoreCase(employee.getGender())) {
            throw new CannotDeleteEmployeeException("No se puede eliminar un empleado femenino.");
        }
        employeeRepository.deleteById(id);
    }
}

