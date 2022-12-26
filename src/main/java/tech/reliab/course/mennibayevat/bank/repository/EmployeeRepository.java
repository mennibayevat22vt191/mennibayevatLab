package tech.reliab.course.mennibayevat.bank.repository;

import lombok.Getter;
import tech.reliab.course.mennibayevat.bank.entity.BankOffice;
import tech.reliab.course.mennibayevat.bank.entity.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class EmployeeRepository implements Repository<Employee> {

    private final ArrayList<Employee> entities = new ArrayList<>();

    public void addEntity(Employee entity) {
        this.entities.add(entity);
    }

    public void delete(Employee entity) {
        for (Employee employee : entities)
            if (Objects.equals(employee, entity)) {
                this.entities.set(Math.toIntExact(employee.getId()), null);
            }
    }

    public void save(Employee entity) {
        for (Employee employee : entities)
            if (Objects.equals(employee, entity)) {
                this.entities.set(Math.toIntExact(employee.getId()), entity);
            }
    }

    public Employee getById(Long id) {
        for (Employee employee : this.entities) {
            if (employee.getId().equals(id))
                return employee;
        }
        return null;
    }

    public List<Employee> findAllCreditAvailableByOffice(BankOffice office) {
        return entities.stream().filter(employee ->
                        employee.getOffice().equals(office) &&
                                employee.getIsMakeLoan())
                .collect(Collectors.toList());
    }
}
