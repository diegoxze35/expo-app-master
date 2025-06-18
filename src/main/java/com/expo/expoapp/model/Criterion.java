package com.expo.expoapp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Criterion {

	@EmbeddedId
	private CriterionId id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private Double balancing;
	@Column()
	private Double value;
	@Column(nullable = false)
	private String description;
	@ManyToOne(
			cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE},
			fetch = FetchType.LAZY
	)
	@MapsId("professorId")
	@JoinColumn(name = "professor_id")
	@ToString.Exclude
	private Professor professor;
}
