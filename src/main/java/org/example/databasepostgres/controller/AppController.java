package org.example.databasepostgres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.example.databasepostgres.entities.StudentEntity;
import org.example.databasepostgres.service.DBService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class AppController {

    @Autowired
    private DBService dbService;

    @GetMapping("/students")
    public String getStudents(Model  model){
        List<StudentEntity> students = dbService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }


    @GetMapping("/add-student")
    public String addStudent(Model  model){
        return "add-student";
    }


    @PostMapping("/add-student")
    public String addStudentPost(@ModelAttribute StudentEntity student){
        dbService.addStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/get-student-info")
    public String getStudentInfo(@RequestParam(name = "id") int id, Model  model){
       StudentEntity student =  dbService.getStudentById(id);
       model.addAttribute("student", student);
       return "student-info";
    }

    @GetMapping("/edit-student")
    public String editStudent(@RequestParam(name = "id") int id, Model  model){
        StudentEntity student = dbService.getStudentById(id);
        model.addAttribute("student", student);
        return "edit-student";
    }

    @PostMapping("/delete-student")
    public String deleteStudent(@RequestParam(name = "id") int id, Model  model){
       dbService.deleteStudentById(id);
       return "redirect:/students";
    }

    @PostMapping("/update-student")
    public String updateStudent(@ModelAttribute StudentEntity student){
        dbService.updateStudent(student);
        return "redirect:/students";
    }


}
