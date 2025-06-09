package test.core.api.service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.core.api.exception.CannotDeleteEmployeeException;
import test.core.api.model.Employee;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;
    @Test
    public void deleteEmployee_FemaleEmployee_ThrowsException() {
        Employee femaleEmployee = new Employee();
        femaleEmployee.setGender("Femenino");
        assertThrows(CannotDeleteEmployeeException.class, () -> employeeService.deleteEmployee(femaleEmployee.getId()));
    }
    @Test
    public void getEmployeesBornBefore2000_ReturnsEmployees() {
        List<Employee> employees = employeeService.getEmployeesBornBefore2000();
        assertFalse(employees.isEmpty());
        assertTrue(employees.stream().allMatch(e -> e.getBirthDate().getYear() < 2000));
    }
}
