package test.core.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import test.core.api.model.Employee;
import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Existing methods ...
    // Start of AI generated code
    @Query("SELECT e FROM Employee e WHERE YEAR(e.birthDate) < 2000")
    List<Employee> findEmployeesBornBefore2000();
    // End of AI generated code
}
package test.core.api.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.service.exceptions.EmployeeServiceException;
import java.util.List;
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeServiceException("Employee not found"));
        if ("Femenino".equalsIgnoreCase(employee.getGender())) {
            throw new EmployeeServiceException("Cannot delete female employees");
        }
        employeeRepository.deleteById(id);
    }
    public List<Employee> getEmployeesBornBefore2000() {
        return employeeRepository.findEmployeesBornBefore2000();
    }
}
package test.core.api.service.exceptions;
public class EmployeeServiceException extends RuntimeException {
    public EmployeeServiceException(String message) {
        super(message);
    }
}
package test.core.api.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.core.api.model.Employee;
import test.core.api.service.EmployeeService;
import java.util.List;
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/born-before-2000")
    public ResponseEntity<List<Employee>> getEmployeesBornBefore2000() {
        List<Employee> employees = employeeService.getEmployeesBornBefore2000();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
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
