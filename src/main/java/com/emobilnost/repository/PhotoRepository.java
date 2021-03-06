/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.repository;

import com.emobilnost.model.Photo;
import com.emobilnost.model.Proizvodi;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Aleksandra
 */


@Repository("photoRepository")
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    List<Photo> findAllBy();
    Page<Photo> findAllBy(Pageable pageable);
    Page<Photo> findByProizvodIsNullAndGlavnazaproizvodIsNullAndActive(boolean active,Pageable pageable);
    
    
    Photo findFirstById(Integer id);
    
  
   
}
