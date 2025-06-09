package test.core.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import test.core.api.model.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.birthDate < :date")
    List<Employee> findAllBornBefore(@Param("date") LocalDate date);
}

package test.core.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployeesBornBefore2000() {
        return employeeRepository.findAllBornBefore(LocalDate.of(2000, 1, 1));
    }
}

package test.core.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.core.api.model.Employee;
import test.core.api.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/born-before-2000")
    public List<Employee> getEmployeesBornBefore2000() {
        return employeeService.getEmployeesBornBefore2000();
    }
}

package test.core.api.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void testGetEmployeesBornBefore2000() {
        Employee emp1 = new Employee();
        emp1.setBirthDate(LocalDate.of(1990, 1, 1));
        Employee emp2 = new Employee();
        emp2.setBirthDate(LocalDate.of(1980, 1, 1));
        List<Employee> employees = Arrays.asList(emp1, emp2);

        Mockito.when(employeeRepository.findAllBornBefore(LocalDate.of(2000, 1, 1))).thenReturn(employees);

        List<Employee> result = employeeService.getEmployeesBornBefore2000();

        assertEquals(2, result.size());
        assertEquals(employees, result);
    }
}

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
