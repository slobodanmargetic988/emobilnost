/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.service;

import com.emobilnost.model.Clanovi;
import java.util.List;

/**
 *
 * @author Aleksandra
 */
public interface ClanoviService {

    List<Clanovi> findAllBy();

    Clanovi findFirstByEmail(String Email);
  Clanovi findFirstByIme(String ime);
List<Clanovi> findAllByOrderByDatumistekaclanstvaAsc();
    void save(Clanovi clan);
        void saveAndFlush(Clanovi clan);

}
