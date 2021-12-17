/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.repository;

import com.emobilnost.model.Photo;
import com.emobilnost.model.Spameri;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Aleksandra
 */


@Repository("spameriRepository")
public interface SpameriRepository extends JpaRepository<Spameri, Integer> {

    List<Spameri> findAllBy();
  
    
    Spameri findByIpadresa(String ipadresa);
    
  
   
}
