package uniandes.dse.examen1.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class CourseEntity {

    @PodamExclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The unique code for the course
     */
    private String courseCode;

    /**
     * The standard name for the course
     */
    private String name;

    /**
     * The number of credits for the course: a number between 1 and 9
     */
    private Integer credits;

    /**
     * A list with the students that have been enrolled in this course.
     * No student should appear more than once in this list
     */
    // TODO

    @ManyToMany
    @JoinTable(name = "curso_estudiante", 
            joinColumns = @jakarta.persistence.JoinColumn(name = "curso_id"), 
            inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "estudiante_id"))
    List<StudentEntity> students = new ArrayList<StudentEntity>();

    @OneToMany(mappedBy = "records")
    List<RecordEntity> records = new ArrayList<RecordEntity>();

}
