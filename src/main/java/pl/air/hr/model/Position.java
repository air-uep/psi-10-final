package pl.air.hr.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "positions")
@NoArgsConstructor @Getter @Setter
public class Position {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false)
	private String name;

}
