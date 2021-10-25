/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.service;

import com.emobilnost.model.Korpa;
import com.emobilnost.model.KorpaProizvodi;
import com.emobilnost.model.Proizvodi;
import com.emobilnost.model.Users;
import com.emobilnost.model.ZavrsenePorudzbine;
import java.util.List;

/**
 *
 * @author Aleksandra
 */
public interface ZavrsenePorudzbineService {

    List<ZavrsenePorudzbine> findAllBy();


    void save(ZavrsenePorudzbine zavrsenePorudzbine);
    void delete(ZavrsenePorudzbine zavrsenePorudzbine);
    ZavrsenePorudzbine findFirstById(Integer id);
  List<ZavrsenePorudzbine> findAllByUser(Users user);
   void saveAndFlush(ZavrsenePorudzbine zavrsenePorudzbine);
}
