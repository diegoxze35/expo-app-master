package com.expo.expoapp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Student extends ExpoUser {
	@Column(length = 4)
	private String groupNumber;
	@Column(length = 1)
	private Integer semester;
	@Enumerated(EnumType.STRING)
	private Career career;
	@Column(columnDefinition = "boolean default false", nullable = false)
	private Boolean isLeader = false;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Team team;
}
