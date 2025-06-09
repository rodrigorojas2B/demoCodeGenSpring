package test.core.api.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import test.core.api.model.Employee;
import test.core.api.service.EmployeeService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void testGetEmployeesBornBefore2000() {
        Employee emp1 = new Employee();
        emp1.setBirthDate(LocalDate.of(1990, 1, 1));
        Employee emp2 = new Employee();
        emp2.setBirthDate(LocalDate.of(1980, 1, 1));
        List<Employee> employees = Arrays.asList(emp1, emp2);

        Mockito.when(employeeService.getEmployeesBornBefore2000()).thenReturn(employees);

        List<Employee> result = employeeController.getEmployeesBornBefore2000();

        assertEquals(2, result.size());
        assertEquals(employees, result);
    }
}
