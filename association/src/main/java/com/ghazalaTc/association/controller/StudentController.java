package com.ghazalaTc.association.controller;

import com.ghazalaTc.association.exception.NotFoundException;
import com.ghazalaTc.association.jpa.StudentRepository;
import com.ghazalaTc.association.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class StudentController  {

    private StudentRepository studentRepository;
     Student student1=new Student();
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("/student/ajouter")
    public Student createStudent(@RequestBody Student s){

        return studentRepository.save(s);

    }

    // select student by id
    @GetMapping("student/retirer/{id}")

    public Student trouerStudent(@PathVariable Long id){

        Student student=new Student();
        Optional<Student> studentOptional=studentRepository.findById(id);

        if (studentOptional.isPresent()) {
            student = studentOptional.get();


            return student;
        }else {
            throw new NotFoundException("student is not exist with ID:"+id);
        }
    }
    // select list of student
    @GetMapping("student/getall")

    public List<Student> getAllStudent(){

        List<Student> students=studentRepository.findAll();
        return students;
    }

    // select student by name
    @GetMapping("/student/byName/{name}")
    public Student  afficheByName(@PathVariable String name){
        Student student=new Student();
        Optional<Student>  optional = Optional.ofNullable(studentRepository.findByName(name));

        if (optional.isPresent())
            student=optional.get();
        return  student;
      /*  return studentRepository.findByName(name);*/
    }
    // select student by age
     @GetMapping("/student/byAge/{age}")
     public  Student afficheByAge(@PathVariable int age){

        return studentRepository.findByAge(age);
     }

    // delete student of list
   @DeleteMapping("student/delete/{id}")

    public void deleteStudent(@PathVariable Long id){

        Student student=new Student();
        Optional<Student> optional=studentRepository.findById(id);

        if (optional.isPresent())
            student=optional.get();
        studentRepository.delete(student);


}
    // delete 2eme approche par map
/*
    @DeleteMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable Long id){

        return studentRepository.findById(id).map(student1 ->  {
                                    studentRepository.delete(student1);
                                    return "succes de suppression";
        }  ).orElseThrow(()->new NotFoundException(""));
    }
*/

    // update student from list
    @PutMapping("/update/{id}")

    public Student updateStudent (@RequestBody Student student,@PathVariable Long id){

        Student student1=new Student();
        Optional<Student> optional=studentRepository.findById(id);
        if(optional.isPresent())
        {
            student1=optional.get();
            student1.setName(student.getName());
            student1.setAge(student.getAge());

            studentRepository.save(student1);
        }else {
            throw  new  NotFoundException("student not found with ID  :"+id);
        }

return student1;

    }

    // 2 eme approche par  hashmap
 /*   public Student updateStudent (@RequestBody Student student,@PathVariable Long id){

        return studentRepository.findById(id).map(student1 -> {
            student1.setName(student.getName());
            student1.setAge(student.getAge());
            return studentRepository.save(student1);
        }).orElseThrow(()->new NotFoundException(""));

    }

*/
}
