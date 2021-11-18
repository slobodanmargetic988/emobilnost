/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.repository;

import com.emobilnost.model.Anketa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Aleksandra
 */


@Repository("anketaRepository")
public interface AnketaRepository extends JpaRepository<Anketa, Integer> {

    List<Anketa> findAllBy();
    Anketa findFirstByEmail(String email);
  
    
   
}
