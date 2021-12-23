/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.repository;

import com.emobilnost.model.Photo;
import com.emobilnost.model.Proizvodi;
import com.emobilnost.model.Slika;
import com.emobilnost.model.Vesti;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Aleksandra
 */


@Repository("vestiRepository")
public interface VestiRepository extends JpaRepository<Vesti, Integer> {

    List<Vesti> findAllBy();
    Page<Vesti> findAllBy(Pageable pageable);
    
    
    Vesti findFirstById(Integer id);
    
   Vesti findFirstByNaslovduzi(String naslov);
   
   @Modifying
    @Query(value = "SELECT * FROM vesti where id<> :noId ORDER BY id DESC LIMIT :koliko", nativeQuery = true)
    List<Vesti> findLastFew(@Param("koliko") Integer koliko,@Param("noId")Integer bezOvogId);
   
}
