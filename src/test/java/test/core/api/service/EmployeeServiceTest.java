package test.core.api.service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import test.core.api.exception.EmployeeServiceException;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;
    @Test
    public void deleteEmployee() {
        assertThrows(EmployeeServiceException.class, () -> employeeService.deleteEmployee(1L));
    }
    @Test
    public void getEmployeesBornBefore2000() {
        assertNotNull(employeeService.getEmployeesBornBefore2000());
    }
    @Test
    public void getEmployeesGroupedByPosition() {
        assertNotNull(employeeService.getEmployeesGroupedByPosition());
    }
}
