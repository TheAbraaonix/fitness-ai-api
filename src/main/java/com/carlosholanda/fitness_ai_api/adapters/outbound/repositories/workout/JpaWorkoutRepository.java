package com.carlosholanda.fitness_ai_api.adapters.outbound.repositories.workout;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carlosholanda.fitness_ai_api.adapters.outbound.entities.JpaWorkoutEntity;

@Repository
public interface JpaWorkoutRepository extends JpaRepository<JpaWorkoutEntity, Long> {
	@Query("SELECT w FROM JpaWorkoutEntity w WHERE w.user.id = :userId")
	List<JpaWorkoutEntity> findByUserId(@Param("userId") UUID userId);

	@Query("SELECT w FROM JpaWorkoutEntity w LEFT JOIN FETCH w.exercises WHERE w.id = :id")
	Optional<JpaWorkoutEntity> findByIdWithExercises(@Param("id") Long id);
}
