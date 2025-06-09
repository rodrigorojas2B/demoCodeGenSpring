package test.core.api.controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Test
    public void testDeleteEmployee() {
        Employee employee = new Employee();
        employee.setGender("Femenino");
        employee = employeeRepository.save(employee);
        ResponseEntity<Void> response = restTemplate.postForEntity("/employees/" + employee.getId(), null, Void.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    @Test
    public void testGetEmployeesBornBefore2000() {
        Employee employee = new Employee();
        employee.setBirthDate(LocalDate.of(1999, 12, 31));
        employee = employeeRepository.save(employee);
        ResponseEntity<Employee[]> response = restTemplate.getForEntity("/employees/born-before-2000", Employee[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().length > 0);
    }
}
