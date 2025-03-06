package uniandes.dse.examen1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import uniandes.dse.examen1.entities.RecordEntity;
import uniandes.dse.examen1.repositories.CourseRepository;
import uniandes.dse.examen1.repositories.StudentRepository;
import uniandes.dse.examen1.repositories.RecordRepository;

@Slf4j
@Service
public class StatsService {

    @Autowired
    StudentRepository estudianteRepository;

    @Autowired
    CourseRepository cursoRepository;

    @Autowired
    RecordRepository inscripcionRepository;

    public Double calculateStudentAverage(String login) {
        // TODO
        double sum_grade = 0;
        double num_cursos = 0;

        List<RecordEntity> records = inscripcionRepository.findAll();
        for (RecordEntity record : records) {
            if (record.getStudentEntity().getLogin().equals(login)) {
                sum_grade = sum_grade + record.getFinalGrade();
                num_cursos = num_cursos + 1;
            }
        }

        return sum_grade / num_cursos;
    }

    public Double calculateCourseAverage(String courseCode) {
        // TODO

        double sum_grade = 0;
        double num_est = 0;

        List<RecordEntity> records = inscripcionRepository.findAll();
        for (RecordEntity record : records) {
            if (record.getCourseEntity().getCourseCode().equals(courseCode)) {
                sum_grade = sum_grade + record.getFinalGrade();
                num_est = num_est + 1;
            }
        }

        return sum_grade / num_est;
    }

}
