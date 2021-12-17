/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.service;

import com.emobilnost.model.Photo;
import com.emobilnost.model.Proizvodi;
import com.emobilnost.model.Spameri;
import com.emobilnost.repository.PhotoRepository;
import com.emobilnost.repository.SpameriRepository;
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
public class SpameriServiceImpl implements SpameriService {

    @Autowired
    private SpameriRepository spameriRepository;

    @Override
    public List<Spameri> findAllBy() {
        return spameriRepository.findAllBy();
    }
  
  
    
  @Override
    public void save(Spameri spameri){
        spameriRepository.save(spameri);
    }
    
 
  @Override
    public Spameri findByIpadresa(String ipadresa){
    return spameriRepository.findByIpadresa(ipadresa);
    
    }
}
