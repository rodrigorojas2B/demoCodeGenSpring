package test.core.api.service;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import test.core.api.exception.InvalidDeletionException;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepository;
    @Test
    public void testDeleteEmployee() {
        Employee employee = new Employee();
        employee.setGender("Femenino");
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(employee));
        assertThrows(InvalidDeletionException.class, () -> {
            employeeService.deleteEmployee(1L);
        });
    }
    @Test
    public void testGetEmployeesBornBefore2000() {
        Employee employee = new Employee();
        employee.setBirthDate(LocalDate.of(1999, 12, 31));
        Mockito.when(employeeRepository.findByBirthDateBefore(Mockito.any())).thenReturn(Arrays.asList(employee));
        assertTrue(employeeService.getEmployeesBornBefore2000().size() > 0);
    }
}
