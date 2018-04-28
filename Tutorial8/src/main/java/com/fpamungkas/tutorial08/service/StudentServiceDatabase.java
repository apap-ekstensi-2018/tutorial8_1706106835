package com.fpamungkas.tutorial08.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpamungkas.tutorial08.dao.StudentMapper;
import com.fpamungkas.tutorial08.model.StudentModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentServiceDatabase implements StudentService
{
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public StudentModel selectStudent (String npm)
    {
        log.info ("select student with npm {}", npm);
        return studentMapper.selectStudent (npm);
    }


    @Override
    public List<StudentModel> selectAllStudents ()
    {
        log.info ("select all students");
        return studentMapper.selectAllStudents ();
    }


    @Override
    public void addStudent (StudentModel student)
    {
        studentMapper.addStudent (student);
    }


    @Override
    public void deleteStudent (String npm)
    {
    		studentMapper.deleteStudent(npm);
    		log.info("student "+ npm + " deleted");
    }
    
//    public void updateStudent (String npm, String name, double gpa)
//    {
//    		studentMapper.updateStudent(npm, name,gpa);
//    		log.info("student "+ npm + " update");
//    }
    
    public void updateStudent (StudentModel student)
    {
    		studentMapper.updateStudent(student);
    		log.info("student "+ student.getNpm() + " update");
    }

}
