package com.carlosholanda.fitness_ai_api.adapters.outbound.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.carlosholanda.fitness_ai_api.domain.workout.WorkoutStatus;
import com.carlosholanda.fitness_ai_api.domain.workout.WorkoutType;

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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "workouts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JpaWorkoutEntity {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private JpaUserEntity user;
    
	@Column(nullable = false, length = 100)
	private String name;
    
	@Column(nullable = false, length = 500)
	private String description;
    
	@Enumerated(EnumType.STRING)
	private WorkoutType type;
    
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private WorkoutStatus status;
    
	@Column(nullable = false)
	private Boolean aiGenerated = false;
    
	@OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("orderInWorkout ASC")
	private List<JpaWorkoutExerciseEntity> exercises;

	@Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

	@PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        
        if (exercises == null) {
            exercises = new ArrayList<>();
        }
        
        if (aiGenerated == null) {
            aiGenerated = false;
        }
        
        if (status == null) {
            if (exercises.isEmpty()) {
                status = WorkoutStatus.DRAFT;
            } else {
                status = WorkoutStatus.ACTIVE;
            }
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
