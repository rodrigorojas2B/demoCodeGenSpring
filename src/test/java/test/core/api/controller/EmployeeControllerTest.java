package test.core.api.controller;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import test.core.api.model.Employee;
import test.core.api.service.EmployeeService;
import java.time.LocalDate;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {
    @InjectMocks
    private EmployeeController employeeController;
    @Mock
    private EmployeeService employeeService;
    @Test
    public void testDeleteEmployee() {
        Mockito.doNothing().when(employeeService).deleteEmployee(Mockito.anyLong());
        ResponseEntity<Void> responseEntity = employeeController.deleteEmployee(1L);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }
    @Test
    public void testGetEmployeesBornBefore2000() {
        Employee employee = new Employee();
        employee.setBirthDate(LocalDate.of(1999, 12, 31));
        Mockito.when(employeeService.getEmployeesBornBefore2000()).thenReturn(Arrays.asList(employee));
        assertEquals(1, employeeController.getEmployeesBornBefore2000().size());
    }
}
