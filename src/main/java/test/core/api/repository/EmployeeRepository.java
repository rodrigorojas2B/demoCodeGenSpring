package test.core.api.repository;
import org.springframework.data.repository.PagingAndSortingRepository;
import test.core.api.model.Employee;
import java.util.List;
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    @Override
    List<Employee> findAll();
}
