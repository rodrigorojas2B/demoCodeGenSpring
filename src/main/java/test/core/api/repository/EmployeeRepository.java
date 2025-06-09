package test.core.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.core.api.model.Employee;
import java.time.LocalDate;
import java.util.List;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByBirthDateBefore(LocalDate date);
}
