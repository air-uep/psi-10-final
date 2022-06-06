package pl.air.hr.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.air.hr.model.*;
import pl.air.hr.repo.*;
import pl.air.hr.service.PrintService;
import pl.air.hr.service.ReadService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class HRSystem {

    @Autowired private ReadService readService;
    @Autowired private PrintService printService;

    @Autowired private EmployeeRepository empRepo;
    @Autowired private DepartmentRepository depRepo;
    @Autowired private PositionRepository posRepo;

    public void run() {
        printMenu();
        while (true) {
            int optionIdx = readService.readInt("\nWybierz opcję z menu");

            switch (optionIdx) {
                case 1:
                    empAll();
                    break;
                case 2:
                    empById();
                    break;
                case 3:
                    empNew();
                    break;
                case 4:
                    depAll();
                    break;
                case 5:
                    depNew();
                    break;
                case 6:
                    empByDep();
                    break;
                case 7:
                    empByPos();
                    break;
                case 8:
                    empSalary();
                    break;
                case 9:
                    empHireDate();
                    break;
                case 10:
                    System.out.println("Koniec pracy");
                    return;
            }
        }
    }

    @Transactional(readOnly = true)
    protected void empAll() {
        List<Employee> all = empRepo.findAll();
        printService.printEmployees(all);
    }

    @Transactional(readOnly = true)
    protected void empById() {
        Long id = readService.readLong("Podaj id pracownika");
        Optional<Employee> opt = empRepo.findById(id);
        if (opt.isEmpty()) {
            System.out.println("Nie znaleziono pracownika");
            return;
        }

        Employee one = opt.get();
        printService.printEmployee(one);
    }

    @Transactional
    protected void empNew() {
        String firstName = readService.readText("Imię");
        String lastName = readService.readText("Nazwisko");
        LocalDate hireDate = readService.readDate("Data zatrudnia");
        BigDecimal salary = readService.readDecimal("Pensja");
        String depName = readService.readText("Nazwa działu");
        String posName = readService.readText("Nazwa stanowiska");

        Optional<Department> optDep = depRepo.findByName(depName);
        if (optDep.isEmpty()) {
            System.out.println("Nie znalezniono działu");
            return;
        }
        Department department = optDep.get();

        Optional<Position> optPos = posRepo.findByName(posName);
        if (optPos.isEmpty()) {
            System.out.println("Nie znalezniono stanowiska");
            return;
        }
        Position position = optPos.get();

        Employee one = new Employee();
        one.setFirstName(firstName);
        one.setLastName(lastName);
        one.setHireDate(hireDate);
        one.setSalary(salary);
        one.setDepartment(department);
        one.setPosition(position);

        Employee oneSaved = empRepo.save(one);

        System.out.println("Utworzono nowego pracownika");
        printService.printEmployee(oneSaved);
    }

    @Transactional(readOnly = true)
    protected void depAll() {
        List<Department> all = depRepo.findAll();
        printService.printDepartments(all);
    }

    @Transactional
    protected void depNew() {
        String name = readService.readText("Nazwa działu");
        String loc = readService.readText("Siedziba");

        Department one = new Department();
        one.setName(name);
        one.setLocation(loc);

        Department oneSaved = depRepo.save(one);

        System.out.println("Utworzono nowy dział");
        printService.printDepartment(oneSaved);
    }

    @Transactional(readOnly = true)
    protected void empByDep() {
        String name = readService.readText("Podaj nazwę działu");
        Optional<Department> opt = depRepo.findByName(name);
        if (opt.isEmpty()) {
            System.out.println("Nie znaleziono działu");
            return;
        }

        Department dep = opt.get();
        List<Employee> all = empRepo.findAllByDepartment(dep);
        printService.printEmployees(all);
    }

    @Transactional(readOnly = true)
    protected void empByPos() {
        String name = readService.readText("Podaj nazwę stanowiska");
        Optional<Position> opt = posRepo.findByName(name);
        if (opt.isEmpty()) {
            System.out.println("Nie znaleziono stanowiska");
            return;
        }

        Position pos = opt.get();
        List<Employee> all = empRepo.findAllByPosition(pos);
        printService.printEmployees(all);
    }

    @Transactional(readOnly = true)
    protected void empSalary() {
        BigDecimal salary = readService.readDecimal("Podaj wartośc pensji");

        List<Employee> all = empRepo.findAllBySalaryGreaterThan(salary);
        printService.printEmployees(all);
    }

    @Transactional(readOnly = true)
    protected void empHireDate() {
        LocalDate hireDate = readService.readDate("Podaj datę zatrudnienia");

        List<Employee> all = empRepo.findAllByHireDateAfter(hireDate);
        printService.printEmployees(all);
    }


    private void printMenu() {
        String menu =
                """
                
                Menu
                ----------------------------------------
                 1. Wszyscy pracownicy
                 2. Pracownik o podanym id
                 3. Nowy pracownik

                 4. Wszystkie działy
                 5. Nowy dział

                 6. Pracownicy zatrudnieni w określonym dziale
                 7. Pracownicy zatrudnieni na określonym stanowisku
                 8. Pracownicy o pensji powyżej określonej wartości
                 9. Pracownicy zatrudnieni po określonej dacie

                10. Koniec
                """;
        System.out.print(menu);
    }

}
