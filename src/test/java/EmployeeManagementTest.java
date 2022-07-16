import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import tech.kbtg.entities.Employee;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeManagementTest {

    @Test
    @Order(1)
    void clearAllData() {
        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.deleteAll();

        assertEquals(0, employeeManagement.getAll());
    }

    @Test
    @Order(2)
    void insertNewEmployee() {
        EmployeeManagement employeeManagement = new EmployeeManagement();
        Employee employee = new Employee();
        employee.setFirstName("This is f name");
        employee.setLastName("This is l name");
        employee.setAge(1);

        employeeManagement.insert(employee);

        Employee byId = employeeManagement.findById(employee.getId());
        assertNotNull(byId);
    }

    @Test
    @Order(3)
    void updateEmployee() {
        EmployeeManagement employeeManagement = new EmployeeManagement();
        int age = 30;
        Long id = employeeManagement.findMaxId();
        employeeManagement.updateAge(id, age);

        Employee byId = employeeManagement.findById(id);
        assertEquals(age, byId.getAge());
    }

    @Test
    @Order(4)
    void queryByCriteria() {
        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.deleteAll();

        employeeManagement.insert(new Employee("F1", "L1", 30));
        employeeManagement.insert(new Employee("F2", "L2", 31));
        employeeManagement.insert(new Employee("F3", "L3", 32));
        employeeManagement.insert(new Employee("F4", "L4", 42));
        employeeManagement.insert(new Employee("F5", "L5", 55));
        employeeManagement.insert(new Employee("F6", "L6", 53));
        employeeManagement.insert(new Employee("F7", "L7", 51));
        employeeManagement.insert(new Employee("F8", "L8", 51));
        employeeManagement.insert(new Employee("F9", "L9", 53));
        employeeManagement.insert(new Employee("F10", "L10", 54));
        employeeManagement.insert(new Employee("F11", "L11", 50));

        List<Employee> result = employeeManagement.findEmployeeOlderThen50();
        assertEquals(6, result.size());
        assertEquals(51, result.get(0).getAge());
        assertEquals(51, result.get(1).getAge());
        assertEquals(53, result.get(2).getAge());
        assertEquals(53, result.get(3).getAge());
        assertEquals(54, result.get(4).getAge());
        assertEquals(55, result.get(5).getAge());

        assertEquals("F7", result.get(0).getFirstName());
        assertEquals("F8", result.get(1).getFirstName());
        assertEquals("F6", result.get(2).getFirstName());
        assertEquals("F9", result.get(3).getFirstName());
        assertEquals("F10", result.get(4).getFirstName());
        assertEquals("F5", result.get(5).getFirstName());
    }
}
