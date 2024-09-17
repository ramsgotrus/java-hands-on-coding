package com.example.SIMPLECRUDApp.repo;

import com.example.SIMPLECRUDApp.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<Guest,Long> {
    @Query("SELECT u FROM Guest u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(u.displayName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Guest> findByFirstNameORLastName(@Param("searchTerm")String searchTerm);
}
