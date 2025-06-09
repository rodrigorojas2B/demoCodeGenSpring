package test.core.api.controller;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import test.core.api.model.Employee;
import test.core.api.service.EmployeeService;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private EmployeeController employeeController;
    @Test
    public void testGetEmployeesBornBefore2000() {
        Employee employee = new Employee();
        employee.setBirthDate(new Date(90, 0, 1)); // 1990-01-01
        Mockito.when(employeeService.getEmployeesBornBefore2000()).thenReturn(Arrays.asList(employee));
        List<Employee> employees = employeeController.getEmployeesBornBefore2000().getBody();
        assertEquals(1, employees.size());
        assertEquals(employee, employees.get(0));
    }
}
