package test.core.api.controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import test.core.api.model.Employee;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class EmployeeControllerTest {
    @Autowired
    private EmployeeController employeeController;
    @Test
    public void deleteEmployee() {
        ResponseEntity<Void> response = employeeController.deleteEmployee(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void getEmployeesBornBefore2000() {
        ResponseEntity<List<Employee>> response = employeeController.getEmployeesBornBefore2000();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    public void getEmployeesGroupedByPosition() {
        ResponseEntity<Map<String, List<Employee>>> response = employeeController.getEmployeesGroupedByPosition();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
