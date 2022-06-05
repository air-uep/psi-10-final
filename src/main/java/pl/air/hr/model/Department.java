package pl.air.hr.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "departments")
@NoArgsConstructor @Getter @Setter
public class Department {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false)
	private String name;
	
	@Column(length = 100, nullable = false)
	private String location;

}
