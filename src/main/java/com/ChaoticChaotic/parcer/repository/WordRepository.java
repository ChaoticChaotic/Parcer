package com.ChaoticChaotic.parcer.repository;

import com.ChaoticChaotic.parcer.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    @Query(nativeQuery = true, value = "select * from word where value = :value")
    List<Word> findAllByValue(@Param("value") String value);

    @Query(nativeQuery = true, value = "select * from word where url_address = :url_address")
    List<Word> findAllByUrl(@Param("url_address") String urlAdress);
}

