/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.service;

import com.emobilnost.model.Clanovi;
import com.emobilnost.model.Users;
import com.emobilnost.repository.ClanoviRepository;
import com.emobilnost.repository.UsersRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aleksandra
 */
@Service
public class ClanoviServiceImpl implements ClanoviService {

    @Autowired
    private ClanoviRepository clanoviRepository;

    @Override
    public List<Clanovi> findAllBy() {
        return clanoviRepository.findAllBy();
    }

    @Override
    public Clanovi findFirstByEmail(String Email) {
        return clanoviRepository.findFirstByEmail(Email);
    }
    @Override
    public Clanovi findFirstByIme(String ime) {
        return clanoviRepository.findFirstByIme(ime);
    }

  @Override
    public  List<Clanovi> findAllByOrderByDatumistekaclanstvaAsc(){
     return clanoviRepository.findAllByOrderByDatumistekaAsc();
    };

    @Override
    public void save(Clanovi clan) {
      
        clanoviRepository.save(clan);
    }
      @Override
    public void saveAndFlush(Clanovi clan) {
      
       clanoviRepository.saveAndFlush(clan);
    }


}
