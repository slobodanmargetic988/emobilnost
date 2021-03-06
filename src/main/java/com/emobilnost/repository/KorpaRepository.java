/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.repository;

import com.emobilnost.model.Korpa;
import com.emobilnost.model.KorpaProizvodi;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Aleksandra
 */


@Repository("korpaRepository")
public interface KorpaRepository extends JpaRepository<Korpa, Integer> {

    List<Korpa> findAllBy();
    
     Korpa findFirstById(int id);
  
  
   
}
