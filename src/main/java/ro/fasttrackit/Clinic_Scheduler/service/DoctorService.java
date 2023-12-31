package ro.fasttrackit.Clinic_Scheduler.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.Clinic_Scheduler.exception.ResourceNotFoundException;
import ro.fasttrackit.Clinic_Scheduler.model.Doctor;
import ro.fasttrackit.Clinic_Scheduler.model.Appointment;
import ro.fasttrackit.Clinic_Scheduler.repository.DoctorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    public List<Appointment> getDoctorSchedule(long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

        return doctor.getAppointments();
    }

    public Doctor getDoctor(Long id){
        return getOrThrow(id);
    }

    public Doctor addDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Long id, Doctor doctorToUpdate) {
        Doctor doctor = getOrThrow(id);
        Doctor updatedDoctor = doctor
                .withName(doctorToUpdate.getName() == null ? doctor.getName() : doctorToUpdate.getName())
                .withSpecialization(doctorToUpdate.getSpecialization() == null ? doctor.getSpecialization() : doctorToUpdate.getSpecialization());

        return doctorRepository.save(updatedDoctor);
    }

    public Doctor fireDoctor(Long id){
        Doctor fired = getOrThrow(id);
        doctorRepository.delete(fired);
        return fired;
    }
    private Doctor getOrThrow(Long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find Doctor with ID: %s".formatted(doctorId)));
    }

}
