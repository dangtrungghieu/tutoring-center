package com.example.tutoring_center.service.impl;

import com.example.tutoring_center.custom_exception.DuplicateException;
import com.example.tutoring_center.custom_exception.InvalidStateException;
import com.example.tutoring_center.dto.*;
import com.example.tutoring_center.entity.Student;
import com.example.tutoring_center.entity.User;
import com.example.tutoring_center.repository.StudentRepository;
import com.example.tutoring_center.repository.UserRepository;
import com.example.tutoring_center.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }
    private StudentResponse mapStudentToStudentResponse(Student student) {
        UserResponseToStudent userResponseToStudent = new UserResponseToStudent(student.getUser().getEmail(),student.getUser().getRole());
        return new StudentResponse(student.getId(),student.getFullName(),student.getPhone(),userResponseToStudent);
    }
    @Override
    public  StudentResponse createStudent(StudentRequest studentRequest){
        User user = userRepository.findById(studentRequest.userId()).orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản liên kết!"));
        if(!user.getRole().equals("STUDENT")){
            throw new InvalidStateException("Quyền hạn không đủ!");
        }
        if(studentRepository.existsByUserId(user.getId())){
            throw new DuplicateException("Tài khoản đã được liên kết với học sinh khác!");
        }
        Student student = new Student();
        student.setFullName(studentRequest.fullName());
        student.setPhone(studentRequest.phone());
        student.setUser(user);
        return mapStudentToStudentResponse(studentRepository.save(student));
    }
    @Override
    public List<StudentResponse> findAllStudents(){
        return studentRepository.findAll().stream().map(this::mapStudentToStudentResponse).toList();
    }
    @Override
    public StudentResponse findStudentById(Long id){
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy học sinh có ID là: " + id));
        return mapStudentToStudentResponse(student);
    }
    @Override
    public List<StudentResponse> findAllStudentByName(String name){
        return studentRepository.findByFullNameContainingIgnoreCase(name).stream().map(this::mapStudentToStudentResponse).toList();
    }
    @Override
    public List<StudentResponse> findAllStudentByPhone(String phone){
        return studentRepository.findByPhoneContainingIgnoreCase(phone).stream().map(this::mapStudentToStudentResponse).toList();
    }
    @Override
    public List<StudentResponse> findStudentByUserEmail(String userEmail){
        return studentRepository.findByUserEmail(userEmail).stream().map(this::mapStudentToStudentResponse).toList();
    }
    @Override
    @Transactional
    public StudentResponse updateStudentForStudent(Long id, StudentRequestUpdate studentRequestUpdate){
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy học sinh có ID là: " + id));
        student.setFullName(studentRequestUpdate.fullName());
        student.setPhone(studentRequestUpdate.phone());
        return mapStudentToStudentResponse(student);
    }
    @Override
    @Transactional
    public StudentResponse updateStudentForAdmin(Long id, StudentRequest studentRequest){
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy học sinh có ID là: " + id));
        User user = userRepository.findById(studentRequest.userId()).orElseThrow(() -> new RuntimeException("Tài khoản liên kết không hợp lệ!"));
        if(!user.getRole().equals("STUDENT")){
            throw new InvalidStateException("Quyền hạn không đủ!");
        }
        if(studentRepository.existsByUser_IdAndIdNot(user.getId(), id)){
            throw new DuplicateException("Tài khoản đã được liên kết với học sinh khác!");
        }
        student.setFullName(studentRequest.fullName());
        student.setPhone(studentRequest.phone());
        student.setUser(user);
        return mapStudentToStudentResponse(student);
    }
    @Override
    @Transactional
    public void deleteStudentByIdForAdmin(Long id){
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy học sinh có ID là: " + id));
        student.setDeleted(true);
        student.getUser().setRole("INACTIVE_STUDENT");
    }
}
