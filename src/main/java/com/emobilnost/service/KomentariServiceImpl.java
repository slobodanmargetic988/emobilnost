/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.service;

import com.emobilnost.model.Komentari;
import com.emobilnost.model.Vesti;
import com.emobilnost.repository.KomentariRepository;
import com.emobilnost.repository.VestiRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aleksandra
 */
@Service
public class KomentariServiceImpl implements KomentariService {

    @Autowired
    private KomentariRepository komentariRepository;

    @Override
    public List<Komentari> findAllBy() {
        return komentariRepository.findAllBy();
    }
  @Override
    public Page<Komentari> findAllBy(Pageable pageable) {
        return komentariRepository.findAllBy(pageable);
    }
    
  
  @Override
    public void save(Komentari komentari){
        komentariRepository.save(komentari);
    }
    
 
  @Override
    public Komentari findFirstById(Integer id){
    return komentariRepository.findFirstById(id);
    
    }
    
  @Override
    public List<Komentari> findAllByVestAndAktivan(Vesti vest, boolean aktivan){
    return komentariRepository.findAllByVestAndAktivan(vest, aktivan);
    
    }

    @Override
    public Page<Komentari> findAllByIdOrderByIdDesc(Pageable pageable) {
         return komentariRepository.findAllByOrderByDatumDesc(pageable);
        
    }
    
    
}
