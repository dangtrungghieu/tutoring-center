package com.example.tutoring_center.service.impl;

import com.example.tutoring_center.custom_exception.DuplicateException;
import com.example.tutoring_center.custom_exception.InvalidStateException;
import com.example.tutoring_center.dto.*;
import com.example.tutoring_center.entity.Teacher;
import com.example.tutoring_center.entity.User;
import com.example.tutoring_center.repository.TeacherRepository;
import com.example.tutoring_center.repository.UserRepository;
import com.example.tutoring_center.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    public TeacherServiceImpl(TeacherRepository teacherRepository, UserRepository userRepository) {
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
    }
    private TeacherResponse mapToTeacherResponse(Teacher teacher) {
        UserResponseToTeacher userResponseToTeacher = new UserResponseToTeacher(teacher.getUser().getEmail(),teacher.getUser().getRole());
        return new TeacherResponse(teacher.getId(),teacher.getFullName(),teacher.getPhone(),userResponseToTeacher);
    }
    @Override
    public TeacherResponse createTeacher(TeacherRequest teacherRequest){
        User user = userRepository.findById(teacherRequest.userId()).orElseThrow(()->new RuntimeException("Không tìm thấy tài khoản liên kết!"));
        if (teacherRepository.existsByUserId(user.getId())){
            throw new DuplicateException("Tài khoản đã được liên kết với giáo viên khác!");
        }
        if (!user.getRole().equals("TEACHER")){
            throw new InvalidStateException("Không đủ quyền hạn thực hiện thao tác!");
        }
        Teacher teacher = new Teacher();
        teacher.setFullName(teacherRequest.fullName());
        teacher.setPhone(teacherRequest.phone());
        teacher.setUser(user);
        return  mapToTeacherResponse(teacherRepository.save(teacher));
    }
    @Override
    public List<TeacherResponse> findAllTeachersToAdmin(){
        return teacherRepository.findAll().stream().map(this::mapToTeacherResponse).toList();
    }
    @Override
    public TeacherResponse findTeacherByIdToAdmin(Long id){
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy giáo viên có ID là: " + id));
        return mapToTeacherResponse(teacher);
    }
    @Transactional
    @Override
    public void deleteTeacherById(Long id){
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy giáo viên có ID là: " + id));
        teacher.setDeleted(true);
        teacher.getUser().setRole("INACTIVE_TEACHER");
    }
    @Transactional
    @Override
    public TeacherResponse updateTeacherByIdToTeacher(Long id, TeacherRequestUpdate teacherRequestUpdate){
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy giáo viên có ID là: " + id));
        teacher.setFullName(teacherRequestUpdate.fullName());
        teacher.setPhone(teacherRequestUpdate.phone());
        return  mapToTeacherResponse(teacher);
    }
    @Override
    public List<TeacherResponse> findAllTeachersToStudent() {
        return teacherRepository.findAllByIsDeletedFalse().stream().map(this::mapToTeacherResponse).toList();
    }
    @Override
    public TeacherResponse findTeacherByIdToStudent(Long id){
        Teacher teacher = teacherRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new RuntimeException("Không tìm thấy giáo viên!"));
        return  mapToTeacherResponse(teacher);
    }
    @Transactional
    @Override
    public TeacherResponse updateTeacherByAdmin(Long id, TeacherRequest teacherRequest){
        User user = userRepository.findById(teacherRequest.userId()).orElseThrow(()->new RuntimeException("Không tìm thấy tài khoản liên kết!"));
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy giáo viên có ID là: " + id));
        if (teacherRepository.existsByUser_IdAndIdNot(teacherRequest.userId(), id)) {
            throw new DuplicateException("Tài khoản này đã được liên kết với giáo viên khác!");
        }
        if (!user.getRole().equals("TEACHER")){
            throw new InvalidStateException("Không đủ quyền hạn thực hiện thao tác!");
        }
        teacher.setFullName(teacherRequest.fullName());
        teacher.setPhone(teacherRequest.phone());
        teacher.setUser(user);
        return  mapToTeacherResponse(teacher);
    }
}
