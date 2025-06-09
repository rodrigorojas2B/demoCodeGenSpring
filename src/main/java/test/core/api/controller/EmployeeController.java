package test.core.api.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import test.core.api.model.Employee;
import test.core.api.service.EmployeeService;
import java.util.List;
@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/employees/born-before-2000")
    public ResponseEntity<List<Employee>> getEmployeesBornBefore2000() {
        List<Employee> employees = employeeService.getEmployeesBornBefore2000();
        return ResponseEntity.ok(employees);
    }
}
