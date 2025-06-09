package test.core.api.exception;

public class CannotDeleteEmployeeException extends RuntimeException {
    public CannotDeleteEmployeeException(String message) {
        super(message);
    }
}

package test.core.api.service;

import test.core.api.exception.CannotDeleteEmployeeException;

public interface EmployeeService {
    // ... otros métodos existentes ...

    void deleteEmployee(Long id) throws CannotDeleteEmployeeException;
}

package test.core.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.core.api.exception.CannotDeleteEmployeeException;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // ... otros métodos existentes ...

    @Override
    public void deleteEmployee(Long id) throws CannotDeleteEmployeeException {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null && "Femenino".equalsIgnoreCase(employee.getGender())) {
            throw new CannotDeleteEmployeeException("No se puede eliminar un empleado femenino.");
        }
        employeeRepository.deleteById(id);
    }
}

package test.core.api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import test.core.api.exception.CannotDeleteEmployeeException;
import test.core.api.model.Employee;
import test.core.api.repository.EmployeeRepository;
import test.core.api.service.impl.EmployeeServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void testDeleteEmployee_Female() {
        Employee employee = new Employee();
        employee.setGender("Femenino");
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        assertThrows(CannotDeleteEmployeeException.class, () -> employeeService.deleteEmployee(1L));

        verify(employeeRepository, times(1)).findById(anyLong());
        verify(employeeRepository, times(0)).deleteById(anyLong());
    }

    @Test
    public void testDeleteEmployee_NotFemale() {
        Employee employee = new Employee();
        employee.setGender("Masculino");
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).findById(anyLong());
        verify(employeeRepository, times(1)).deleteById(anyLong());
    }
}
