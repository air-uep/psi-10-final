package pl.air.hr.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@NoArgsConstructor @Getter @Setter
public class Employee {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, name = "first_name", nullable = false)
	private String firstName;
	
	@Column(length = 100, name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "hire_date", nullable = false)
	private LocalDate hireDate;

	@Column(precision = 7, scale = 2, nullable = false)
	private BigDecimal salary;
	
	@ManyToOne
	@JoinColumn(name = "dep_id")
	private Department department;
	
	@ManyToOne
	@JoinColumn(name = "pos_id")
	private Position position;
	
}
