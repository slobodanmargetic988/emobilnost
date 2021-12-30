/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.repository;

import com.emobilnost.model.Komentari;
import com.emobilnost.model.Vesti;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Aleksandra
 */


@Repository("komentariRepository")
public interface KomentariRepository extends JpaRepository<Komentari, Integer> {

    List<Komentari> findAllBy();
    Page<Komentari> findAllBy(Pageable pageable);
        Page<Komentari> findAllByOrderByDatumDesc(Pageable pageable);
    
    Komentari findFirstById(Integer id);
    
    List<Komentari> findAllByVestAndAktivan(Vesti vest, boolean aktivan);
    
   
  
}
