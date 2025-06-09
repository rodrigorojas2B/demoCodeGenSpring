package test.core.api.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.service.exceptions.EmployeeServiceException;
import java.util.List;
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeServiceException("Employee not found"));
        if ("Femenino".equalsIgnoreCase(employee.getGender())) {
            throw new EmployeeServiceException("Cannot delete female employees");
        }
        employeeRepository.deleteById(id);
    }
    public List<Employee> getEmployeesBornBefore2000() {
        return employeeRepository.findEmployeesBornBefore2000();
    }
}
