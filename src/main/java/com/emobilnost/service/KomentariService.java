/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.service;

import com.emobilnost.model.Komentari;
import com.emobilnost.model.Vesti;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Aleksandra
 */
public interface KomentariService {

    List<Komentari> findAllBy();
     Page<Komentari> findAllBy(Pageable pageable);
    
void save(Komentari komentari);
    Komentari findFirstById(Integer id);

     List<Komentari> findAllByVestAndAktivan(Vesti vest, boolean aktivan);
  Page<Komentari> findAllByIdOrderByIdDesc(Pageable pageable);
    
    
    
}
