package test.core.api.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.exception.CannotDeleteEmployeeException;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            if ("Femenino".equals(employee.getGender())) {
                throw new CannotDeleteEmployeeException("No se puede eliminar un empleado de género femenino");
            }
            employeeRepository.delete(employee);
        }
    }
    public List<Employee> getEmployeesBornBefore2000() {
        return employeeRepository.findAll().stream()
                .filter(employee -> employee.getBirthDate().getYear() < 2000)
                .collect(Collectors.toList());
    }
}
