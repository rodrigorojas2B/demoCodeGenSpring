package test.core.api.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.core.api.model.Employee;
import test.core.api.service.EmployeeService;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/born-before-2000")
    public ResponseEntity<List<Employee>> getEmployeesBornBefore2000() {
        return ResponseEntity.ok(employeeService.getEmployeesBornBefore2000());
    }
    @GetMapping("/grouped-by-position")
    public ResponseEntity<Map<String, List<Employee>>> getEmployeesGroupedByPosition() {
        return ResponseEntity.ok(employeeService.getEmployeesGroupedByPosition());
    }
}
