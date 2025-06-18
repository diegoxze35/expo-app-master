package com.expo.expoapp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Professor extends ExpoUser {

	@OneToMany(
			mappedBy = "professor",
			fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
	)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Project> projects;

	@OneToMany(
			cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE},
			fetch = FetchType.LAZY,
			mappedBy = "professor"
	)
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Set<Criterion> criteria;

}
