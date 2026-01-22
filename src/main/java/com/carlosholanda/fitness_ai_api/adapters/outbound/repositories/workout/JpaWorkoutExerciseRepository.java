package com.carlosholanda.fitness_ai_api.adapters.outbound.repositories.workout;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carlosholanda.fitness_ai_api.adapters.outbound.entities.JpaWorkoutExerciseEntity;

@Repository
public interface JpaWorkoutExerciseRepository extends JpaRepository<JpaWorkoutExerciseEntity, Long> {
	List<JpaWorkoutExerciseEntity> findByWorkoutIdOrderByOrderInWorkoutAsc(Long workoutId);
	void deleteByWorkoutId(Long workoutId);
}
