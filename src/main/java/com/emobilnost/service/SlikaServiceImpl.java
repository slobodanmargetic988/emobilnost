/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.service;

import com.emobilnost.model.Photo;
import com.emobilnost.model.Proizvodi;
import com.emobilnost.model.Slika;
import com.emobilnost.repository.PhotoRepository;
import com.emobilnost.repository.SlikaRepository;
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
public class SlikaServiceImpl implements SlikaService {

    @Autowired
    private SlikaRepository slikaRepository;

    @Override
    public List<Slika> findAllBy() {
        return slikaRepository.findAllBy();
    }
  @Override
    public Page<Slika> findAllBy(Pageable pageable) {
        return slikaRepository.findAllBy(pageable);
    }
    
  
    
  @Override
    public void save(Slika slika){
        slikaRepository.save(slika);
    }
    
 
  @Override
    public Slika findFirstById(Integer id){
    return slikaRepository.findFirstById(id);
    
    }
}
