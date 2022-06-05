package pl.air.hr.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.air.hr.model.*;

@Service
public class PrintService {
	
	/* department */
	public void printDepartment(Department one) {
		printDepartment(one, true);
	}

	private void printDepartment(Department one, boolean header) {
		Long id = one.getId();
		String name = one.getName();
		String location = one.getLocation();

		if (header) {
			printDepartmentHeader();
		}
		System.out.format(
				"%-3d | %-20s | %-30s \n", id, name, location
		);
	}

	public void printDepartments(List<Department> many) {
		printDepartmentHeader();
		for (Department one : many) {
			printDepartment(one, false);
		}
	}

	private void printDepartmentHeader() {
		System.out.println(
				"id  | nazwa                | siedziba \n" +
				"----+----------------------+------------------------------"
		);
	}
	
	
	/* position */
	public void printPosition(Position one) {
		printPosition(one, true);
	}

	public void printPosition(Position one, boolean header) {
		Long id = one.getId();
		String name = one.getName();

		if (header) {
			printPositionHeader();
		}
		System.out.format(
				"%-3d | %-20s \n", id, name
		);
	}


	public void printPositions(List<Position> many) {
		printPositionHeader();
		for (Position one : many) {
			printPosition(one, false);
		}
	}

	private void printPositionHeader() {
		System.out.println(
				"id  | nazwa \n" +
				"----+----------------------"
		);
	}

	
	/* employee */
	public void printEmployee(Employee one) {
		printEmployee(one, true);
	}

	public void printEmployee(Employee one, boolean header) {
		Long id = one.getId();
		String fullName = one.getFirstName() + " " + one.getLastName();
		BigDecimal salary = one.getSalary();
		LocalDate hireDate = one.getHireDate();
		String depName = one.getDepartment().getName();
		String posName = one.getPosition().getName();

		if (header) {
			printEmployeeHeader();
		}
		System.out.format(
				"%-3d | %-30s | %8.2f | %tF   | %-20s | %-20s \n", id, fullName, salary, hireDate, depName, posName
		);
	}
	
	public void printEmployees(List<Employee> many) {
		printEmployeeHeader();
		for (Employee one : many) {
			printEmployee(one, false);
		}
	}

	private void printEmployeeHeader() {
		System.out.println(
				"id  | imię i nazwisko                | pensja   | zatrudnienie | dział                | stanowisko \n" +
				"----+--------------------------------+----------+--------------+----------------------+----------------------"
		);
	}

}
