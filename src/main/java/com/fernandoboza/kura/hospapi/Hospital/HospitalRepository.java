package com.fernandoboza.kura.hospapi.Hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}