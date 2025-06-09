package test.core.api.service;
import org.springframework.stereotype.Service;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.exception.CannotDeleteEmployeeException;
import java.util.Calendar;
import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            // Start of AI changes
            if ("Femenino".equals(employee.getGender())) {
                throw new CannotDeleteEmployeeException("Cannot delete female employees.");
            }
            // End of AI changes
            employeeRepository.delete(employee);
        }
    }

    public List<Employee> getEmployeesBornBefore2000() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.JANUARY, 1);
        return employeeRepository.findEmployeesBornBefore(calendar.getTime());
    }
}
