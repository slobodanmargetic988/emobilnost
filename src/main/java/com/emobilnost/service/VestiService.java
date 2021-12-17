/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.service;

import com.emobilnost.model.Photo;
import com.emobilnost.model.Proizvodi;
import com.emobilnost.model.Slika;
import com.emobilnost.model.Vesti;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Aleksandra
 */
public interface VestiService {

    List<Vesti> findAllBy();
     Page<Vesti> findAllBy(Pageable pageable);
    
void save(Vesti vesti);
    Vesti findFirstById(Integer id);

}
