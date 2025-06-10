package test.core.api.controller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import test.core.api.service.EmployeeService;
import test.core.api.model.Employee;
import java.util.Arrays;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {
    @InjectMocks
    private EmployeeController employeeController;
    @Mock
    private EmployeeService employeeService;
    @Test
    public void testDeleteEmployee() {
        doNothing().when(employeeService).deleteEmployee(anyLong());
        employeeController.deleteEmployee(1L);
        verify(employeeService, times(1)).deleteEmployee(anyLong());
    }
    @Test
    public void testGetEmployeesBornBefore2000() {
        when(employeeService.getEmployeesBornBefore2000()).thenReturn(Arrays.asList(new Employee()));
        assert(employeeController.getEmployeesBornBefore2000().size() == 1);
    }
}
