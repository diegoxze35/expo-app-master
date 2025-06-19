package com.expo.expoapp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Criterion {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Enumerated(EnumType.STRING)
    @Column(name = "project_type", nullable = false)
    private ProjectType projectType;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private Double balancing;
	@Column(nullable = false)
	private String description;
	@ManyToOne(
			cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE},
			fetch = FetchType.LAZY
	)
	@JoinColumn(name = "professor_id", nullable = false)
	@ToString.Exclude
	private Professor professor;
}
