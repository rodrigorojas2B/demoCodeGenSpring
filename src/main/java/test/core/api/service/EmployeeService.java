package test.core.api.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.exception.EmployeeServiceException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            // Start of AI modification
            if ("Femenino".equals(employee.getGender())) {
                throw new EmployeeServiceException("Cannot delete female employee due to internal policy");
            }
            // End of AI modification
            employeeRepository.delete(employee);
        }
    }
    public List<Employee> getEmployeesBornBefore2000() {
        return StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
                .filter(employee -> employee.getBirthDate().getYear() < 2000)
                .collect(Collectors.toList());
    }
    public Map<String, List<Employee>> getEmployeesGroupedByPosition() {
        return StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
                .collect(Collectors.groupingBy(Employee::getPosition));
    }
}
