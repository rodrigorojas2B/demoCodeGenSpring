package test.core.api.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.core.api.exception.CannotDeleteEmployeeException;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import java.time.LocalDate;
import java.util.List;
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null && "Femenino".equalsIgnoreCase(employee.getGender())) {
            throw new CannotDeleteEmployeeException("Cannot delete female employee");
        }
        employeeRepository.deleteById(id);
    }
    public List<Employee> getEmployeesBornBefore2000() {
        return employeeRepository.findByBirthDateBefore(LocalDate.of(2000, 1, 1));
    }
}
