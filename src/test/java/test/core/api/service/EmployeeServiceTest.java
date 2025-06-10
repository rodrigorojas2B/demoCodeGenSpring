package test.core.api.service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.exception.CannotDeleteEmployeeException;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepository;
    @Test
    public void testDeleteFemaleEmployee() {
        Employee employee = new Employee();
        employee.setGender("Femenino");
        when(employeeRepository.findById(anyLong())).thenReturn(java.util.Optional.of(employee));
        try {
            employeeService.deleteEmployee(1L);
        } catch (CannotDeleteEmployeeException e) {
            verify(employeeRepository, times(0)).delete(any(Employee.class));
        }
    }
    @Test
    public void testGetEmployeesBornBefore2000() {
        Employee employee1 = new Employee();
        employee1.setBirthDate(new GregorianCalendar(1999, 0, 1).getTime());
        Employee employee2 = new Employee();
        employee2.setBirthDate(new GregorianCalendar(2000, 0, 1).getTime());
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2));
        assert(employeeService.getEmployeesBornBefore2000().size() == 1);
    }
}
