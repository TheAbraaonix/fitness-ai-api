package com.carlosholanda.fitness_ai_api.adapters.outbound.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "workout_exercises")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JpaWorkoutExerciseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "workout_id", nullable = false)
	private JpaWorkoutEntity workout;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exercise_id", nullable = false)
	private JpaExerciseEntity exercise;
	
	@Column(nullable = false)
	private int sets;
	
	@Column(nullable = false)
	private int reps;
	
	@Column
	private Double weight;
	
	@Column
	private int restTime;
	
	@Column(length = 300)
	private String notes;
	
	@Column(nullable = false)
	private Integer orderInWorkout;
}
