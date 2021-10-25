/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.service;

import com.emobilnost.model.Photo;
import com.emobilnost.model.Proizvodi;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Aleksandra
 */
public interface PhotoService {

    List<Photo> findAllBy();
     Page<Photo> findAllBy(Pageable pageable);
     Page<Photo> findByProizvodIsNullAndGlavnazaproizvodIsNullAndActive(boolean active, Pageable pageable);
    
void save(Photo photo);
    Photo findFirstById(Integer id);

}
