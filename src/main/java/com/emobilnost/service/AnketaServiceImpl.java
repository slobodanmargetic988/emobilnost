/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.service;

import com.emobilnost.model.Anketa;
import com.emobilnost.repository.AnketaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aleksandra
 */
@Service
public class AnketaServiceImpl implements AnketaService {

    @Autowired
    private AnketaRepository anketaRepository;

    @Override
    public List<Anketa> findAllBy() {
        return anketaRepository.findAllBy();
    }

    @Override
    public Anketa findFirstByEmail(String Email) {
        return anketaRepository.findFirstByEmail(Email);
    }

   

    @Override
    public void save(Anketa anketa) {
      
        anketaRepository.save(anketa);
    }
      @Override
    public void saveAndFlush(Anketa anketa) {
      
       anketaRepository.saveAndFlush(anketa);
    }


}
