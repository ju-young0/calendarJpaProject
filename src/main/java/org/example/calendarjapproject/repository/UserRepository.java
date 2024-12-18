package org.example.calendarjapproject.repository;

import org.example.calendarjapproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface UserRepository extends JpaRepository<User, Long> {

    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Does not exist id : " + id));
    }

    User findByEmail(String email);

}
