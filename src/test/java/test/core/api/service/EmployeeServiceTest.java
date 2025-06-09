package test.core.api.service;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.service.exceptions.EmployeeServiceException;
import java.util.Date;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeService employeeService;
    @Test
    public void testDeleteFemaleEmployee() {
        Employee employee = new Employee();
        employee.setGender("Femenino");
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(employee));
        assertThrows(EmployeeServiceException.class, () -> employeeService.deleteEmployee(1L));
    }
}
