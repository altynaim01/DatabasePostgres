package org.example.databasepostgres.service;

import lombok.AllArgsConstructor;
import org.example.databasepostgres.entities.ItemEntity;
import org.example.databasepostgres.entities.StudentEntity;
import org.example.databasepostgres.entities.UserEntity;
import org.example.databasepostgres.repos.ItemRepository;
import org.example.databasepostgres.repos.StudentRepository;
import org.example.databasepostgres.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepo userRepository;

    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(StudentEntity student){
        studentRepository.save(student);
    }

    public StudentEntity getStudentById(int id){
        return studentRepository.findAll().stream().filter(s -> s.getId() == id).findFirst().get();
    }

    public void deleteStudentById(int id){
        studentRepository.delete(getStudentById(id));
    }

    public void updateStudent(StudentEntity student){
        StudentEntity student1 = studentRepository.findAll().stream().filter(s -> s.getId() == student.getId()).findFirst().get();
        student1.setName(student.getName());
        student1.setSurname(student.getSurname());
        student1.setCity(student.getCity());
        student1.setBirthDate(student.getBirthDate());
        studentRepository.save(student1);
    }

    public List<ItemEntity> getAllItems() {
        return itemRepository.findAll();
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

}
