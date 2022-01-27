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
import com.emobilnost.repository.PhotoRepository;
import com.emobilnost.repository.SlikaRepository;
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
public class VestiServiceImpl implements VestiService {

    @Autowired
    private VestiRepository vestiRepository;

    @Override
    public List<Vesti> findAllBy() {
        return vestiRepository.findAllBy();
    }
  @Override
    public Page<Vesti> findAllBy(Pageable pageable) {
        return vestiRepository.findAllBy(pageable);
    }
    
       @Override
    public   Page<Vesti> findAllByOrderByDatumDesc(Pageable pageable) {
        return vestiRepository.findAllByOrderByDatumDesc(pageable);
    }

  
  @Override
    public void save(Vesti vesti){
        vestiRepository.save(vesti);
    }
    
 
  @Override
    public Vesti findFirstById(Integer id){
    return vestiRepository.findFirstById(id);
    
    }
    
      @Override
    public Vesti findFirstByNaslovduzi(String naslov){
    return vestiRepository.findFirstByNaslovduzi(naslov);
    }
    
      @Override
    public  List<Vesti> findLastFew( Integer koliko,Integer bezOvogId){
    return vestiRepository.findLastFew(koliko,bezOvogId);
    }
    
    
    
    
    
}
