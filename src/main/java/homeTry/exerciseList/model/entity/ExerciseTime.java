package homeTry.exerciseList.model.entity;

import homeTry.common.constants.DateTimeUtil;
import homeTry.common.converter.DurationToLongConverter;
import homeTry.common.entity.BaseEntity;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "exercise_time",
    indexes = {
        @Index(name = "idx_exercise_time_exercise_start_time", columnList = "exercise_id, startTime"),
    }
)
public class ExerciseTime extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    @Convert(converter = DurationToLongConverter.class)
    private Duration exerciseTime = Duration.ZERO;

    @Column(nullable = false)
    private boolean isActive = false;

    @OneToOne
    @JoinColumn(nullable = false)
    private Exercise exercise;

    protected ExerciseTime() {
    }

    public ExerciseTime(Exercise exercise) {
        this.exercise = exercise;
        this.startTime = DateTimeUtil.getStartOfDay(LocalDate.now());
    }

    public void startExercise() {
        this.startTime = LocalDateTime.now();
        this.isActive = true;
    }

    public void stopExercise() {
        if (isActive) {
            Duration timeElapsed = Duration.between(this.startTime, LocalDateTime.now());
            this.exerciseTime = this.exerciseTime.plus(timeElapsed);
            this.isActive = false;
        }
    }

    public void stopExerciseWithoutSavingTime() {
        if (isActive) {
            this.isActive = false;  // 운동을 종료하지만 시간을 더하지 않음
        }
    }

    public void resetDailyExercise() {
        this.exerciseTime = Duration.ZERO;
        this.isActive = false;
        this.startTime = DateTimeUtil.getStartOfDay(LocalDate.now());
    }

    public Long getId() {
        return id;
    }

    public Duration getExerciseTime() {
        return exerciseTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public Exercise getExercise() {
        return exercise;
    }

}
