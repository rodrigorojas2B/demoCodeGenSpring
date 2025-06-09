package test.core.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployeesBornBefore2000() {
        return employeeRepository.findAllBornBefore(LocalDate.of(2000, 1, 1));
    }
}

