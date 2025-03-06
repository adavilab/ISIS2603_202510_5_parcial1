package uniandes.dse.examen1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import uniandes.dse.examen1.entities.CourseEntity;
import uniandes.dse.examen1.entities.StudentEntity;
import uniandes.dse.examen1.entities.RecordEntity;
import uniandes.dse.examen1.exceptions.InvalidRecordException;
import uniandes.dse.examen1.repositories.CourseRepository;
import uniandes.dse.examen1.repositories.StudentRepository;
import uniandes.dse.examen1.repositories.RecordRepository;

@Slf4j
@Service
public class RecordService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    RecordRepository recordRepository;

    public RecordEntity createRecord(String loginStudent, String courseCode, Double grade, String semester)
            throws InvalidRecordException {
        // TODO
        Optional<StudentEntity> student = studentRepository.findByLogin(loginStudent);
        Optional<CourseEntity> course = courseRepository.findByCourseCode(courseCode);

        if (student.isEmpty() || course.isEmpty()) {
            throw new InvalidRecordException("Tiene que existir el estudiante y el curso.");
        }

        if (grade < 1.5 || grade > 5.0) {
            throw new InvalidRecordException("Nota no está entre 1.5 y 5.0.");
        }

        List<RecordEntity> records = recordRepository.findAll();
        for (RecordEntity record : records) {
            if (record.getStudentEntity().getLogin().equals(loginStudent) && record.getFinalGrade() >= 3.0) {
                throw new InvalidRecordException("Ya pasó el curso.");
            }
        }

        RecordEntity record = new RecordEntity();
        record.setStudentEntity(student.get());
        record.setCourseEntity(course.get());
        record.setFinalGrade(grade);
        record.setSemester(semester);

        return recordRepository.save(record);

    }
}
