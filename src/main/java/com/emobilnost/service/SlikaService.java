/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.service;

import com.emobilnost.model.Photo;
import com.emobilnost.model.Proizvodi;
import com.emobilnost.model.Slika;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Aleksandra
 */
public interface SlikaService {

    List<Slika> findAllBy();
     Page<Slika> findAllBy(Pageable pageable);
    
void save(Slika slika);
    Slika findFirstById(Integer id);
    
    
       List<Slika> findAllByGalerija(Boolean da);
    Page<Slika> findAllByGalerija(Pageable pageable,Boolean da);
       List<Slika> findAllByGalerijaAndActive(Boolean galerija, Boolean active);
    Page<Slika> findAllByGalerijaAndActive(Pageable pageable,Boolean galerija, Boolean active);
}
