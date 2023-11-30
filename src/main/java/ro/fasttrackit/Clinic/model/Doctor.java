package ro.fasttrackit.Clinic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String name;
    private Specialization specialization;
}
