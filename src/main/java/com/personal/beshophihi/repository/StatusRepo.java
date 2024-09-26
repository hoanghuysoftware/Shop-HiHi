package com.personal.beshophihi.repository;

import com.personal.beshophihi.model.Status;
import com.personal.beshophihi.utils.StatusName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepo extends JpaRepository<Status, Long> {
   Optional<Status> getStatusByName(StatusName name);
   boolean existsByName(StatusName statusName);
}
