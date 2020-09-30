package com.thoughtworks.capability.gtb.entrancequiz.repository;

import com.thoughtworks.capability.gtb.entrancequiz.domain.GtbGroup;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainee;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Trainer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends CrudRepository<Trainer, Long> {
    List<Trainer> findAll();

    List<Trainer> findAllByGroupIn(List<GtbGroup> groups);

    List<Trainer> findAllByGroupEquals(GtbGroup group);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE trainee SET group_id = NULL", nativeQuery = true)
    void clearGroupForeignKey();
}
