package com.carlosholanda.fitness_ai_api.domain.workout;

public class WorkoutExercise {
    private Long id;
	private Long workoutId;
	private Long exerciseId;
	private int sets;
	private int reps;
	private Double weight;
	private int restTime;
	private String notes;
	private Integer orderInWorkout;

	public WorkoutExercise() {
	}

	public WorkoutExercise(Long id, Long workoutId, Long exerciseId, int sets, int reps, Double weight, int restTime,
			String notes, Integer orderInWorkout) {
		this.id = id;
		this.workoutId = workoutId;
		this.exerciseId = exerciseId;
		this.sets = sets;
		this.reps = reps;
		this.weight = weight;
		this.restTime = restTime;
		this.notes = notes;
		this.orderInWorkout = orderInWorkout;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWorkoutId() {
		return workoutId;
	}

	public void setWorkoutId(Long workoutId) {
		this.workoutId = workoutId;
	}

	public Long getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(Long exerciseId) {
		this.exerciseId = exerciseId;
	}

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public int getRestTime() {
		return restTime;
	}

	public void setRestTime(int restTime) {
		this.restTime = restTime;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getOrderInWorkout() {
		return orderInWorkout;
	}

	public void setOrderInWorkout(Integer orderInWorkout) {
		this.orderInWorkout = orderInWorkout;
	}

	
}
