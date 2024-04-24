package org.empire.springboot_mongodb;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // list of all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // get student by id
    public Student getStudentById(String id) {
        return studentRepository.findById(id).orElse(null);
    }

    // add student
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // update student
    public Student updateStudent(String id, Student student) {
        return studentRepository.findById(id)
                .map(student1 -> {
                    student1.setFirstName(student.getFirstName());
                    student1.setLastName(student.getLastName());
                    student1.setEmail(student.getEmail());
                    student1.setGender(student.getGender());
                    student1.setAddress(student.getAddress());
                    student1.setFavouriteSubjects(student.getFavouriteSubjects());
                    student1.setTotalSpentInBooks(student.getTotalSpentInBooks());
                    student1.setCreated(student.getCreated());
                    return studentRepository.save(student1);
                })
                .orElseGet(() -> {
                    student.setId(id);
                    return studentRepository.save(student);
                });
    }

    // delete a student by id
    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}
