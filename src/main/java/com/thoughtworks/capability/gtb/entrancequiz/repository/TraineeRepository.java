package com.thoughtworks.capability.gtb.entrancequiz.repository;

import com.thoughtworks.capability.gtb.entrancequiz.domain.GtbGroup;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface TraineeRepository extends CrudRepository<Trainee, Long>{
    List<Trainee> findAll();

    List<Trainee> findAllByGroupIn(List<GtbGroup> groups);

    List<Trainee> findAllByGroupEquals(GtbGroup group);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE trainee SET group_id = NULL", nativeQuery = true)
    void clearGroupForeignKey();
}
