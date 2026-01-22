package com.carlosholanda.fitness_ai_api.domain.workout;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.carlosholanda.fitness_ai_api.domain.workout.enums.WorkoutStatus;
import com.carlosholanda.fitness_ai_api.domain.workout.enums.WorkoutType;

public class Workout {
	private Long id;
	private UUID userId;
	private String name;
	private String description;
	private WorkoutType type;
	private WorkoutStatus status;
	private Boolean aiGenerated = false;
	private List<WorkoutExercise> exercises;
	private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
	
	public Workout() {
	}

	public Workout(Long id, UUID userId, String name, String description, WorkoutType type, WorkoutStatus status,
			boolean aiGenerated, List<WorkoutExercise> exercises, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.description = description;
		this.type = type;
		this.status = status;
		this.aiGenerated = aiGenerated;
		this.exercises = exercises != null ? exercises : new ArrayList<>();
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		
		// Auto-set status based on exercises if status is not explicitly set
		if (this.status == null || this.status == WorkoutStatus.ACTIVE) {
			this.updateStatusBasedOnExercises();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public WorkoutType getType() {
		return type;
	}

	public void setType(WorkoutType type) {
		this.type = type;
	}

	public WorkoutStatus getStatus() {
		return status;
	}

	public void setStatus(WorkoutStatus status) {
		this.status = status;
	}

	public boolean isAiGenerated() {
		return aiGenerated;
	}

	public void setAiGenerated(boolean aiGenerated) {
		this.aiGenerated = aiGenerated;
	}

	public List<WorkoutExercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<WorkoutExercise> exercises) {
		this.exercises = exercises;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void addExercise(WorkoutExercise workoutExercise) {
		this.exercises.add(workoutExercise);
		this.updateStatusBasedOnExercises();
	}

	public void removeExercise(WorkoutExercise workoutExercise) {
		this.exercises.remove(workoutExercise);
		this.updateStatusBasedOnExercises();
	}
	
	
	public void updateStatusBasedOnExercises() {
		// Only auto-update if current status is DRAFT or ACTIVE
		if (this.status == WorkoutStatus.DRAFT || this.status == WorkoutStatus.ACTIVE) {
			if (this.exercises != null && !this.exercises.isEmpty()) {
				this.status = WorkoutStatus.ACTIVE;
			} else {
				this.status = WorkoutStatus.DRAFT;
			}
		}
	}
}
