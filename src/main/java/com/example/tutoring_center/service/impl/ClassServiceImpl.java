package com.example.tutoring_center.service.impl;

import com.example.tutoring_center.custom_exception.DuplicateException;
import com.example.tutoring_center.dto.ClassRequest;
import com.example.tutoring_center.dto.ClassResponse;
import com.example.tutoring_center.entity.Room;
import com.example.tutoring_center.entity.Shift;
import com.example.tutoring_center.repository.ClassRepository;
import com.example.tutoring_center.repository.ShiftRepository;
import com.example.tutoring_center.service.ClassService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.tutoring_center.entity.Class;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;
    private final ShiftRepository shiftRepository;
    public ClassServiceImpl(ClassRepository classRepository, ShiftRepository shiftRepository) {
        this.classRepository = classRepository;
        this.shiftRepository = shiftRepository;
    }
    private ClassResponse mapToClassResponse(Class clazz) {
        return new ClassResponse(clazz.getId(), clazz.getSubject(),clazz.getGrade(), clazz.getShift().getDayOfWeek(),clazz.getShift().getStartTime(),clazz.getShift().getEndTime(),clazz.getShift().getRoom().getAddress());
    }
    @Override
    public ClassResponse createClass(ClassRequest classRequest){
        Shift shift = shiftRepository.findById(classRequest.shiftId()).orElseThrow(() -> new RuntimeException("Không tìm thấy ca học!"));
        Class clazz = new Class();
        clazz.setSubject(classRequest.subject());
        clazz.setGrade(classRequest.grade());
        clazz.setShift(shift);
        return  mapToClassResponse(this.classRepository.save(clazz));
    }
    @Override
    public List<ClassResponse> findAllClassesForAdmin(){
        return classRepository.findAll().stream().map(this::mapToClassResponse).toList();
    }
    @Override
    public List<ClassResponse> findAllClassesForStudent(){
        return classRepository.findAllClassesByIsDeletedFalse().stream().map(this::mapToClassResponse).toList();
    }
    @Override
    public ClassResponse findClassById(Long id){
        Class clazz = classRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy lớp học!"));
        return mapToClassResponse(clazz);
    }
    @Override
    public List<ClassResponse> findClassByRoomAddress(String roomAddress){
        return classRepository.findClassesByShift_Room_Address(roomAddress).stream().map(this::mapToClassResponse).toList();
    }
    @Override
    public List<ClassResponse> findClassByShiftDayOfWeek(Integer shiftDayOfWeek){
        return classRepository.findClassesByShiftDayOfWeek(shiftDayOfWeek).stream().map(this::mapToClassResponse).toList();
    }
    @Transactional
    @Override
    public ClassResponse updateClass(Long id, ClassRequest request){
        Class clazz = classRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy lớp học này!"));
        Shift shift = shiftRepository.findById(request.shiftId()).orElseThrow(() -> new RuntimeException("Không tìm thấy ca học này!"));
        if (classRepository.existsByShiftId_IdAndIdNot(request.shiftId(),id)){
            throw new DuplicateException("Ca học này đã được sử dụng!");
        }
        clazz.setSubject(request.subject());
        clazz.setGrade(request.grade());
        clazz.setShift(shift);
        return  mapToClassResponse(clazz);
    }
    @Transactional
    @Override
    public void deleteClass(Long id){
        Class clazz = classRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy lớp học!"));
        clazz.setDeleted(true);
    }
}
