/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.service;

import com.emobilnost.model.Anketa;
import java.util.List;

/**
 *
 * @author Aleksandra
 */
public interface AnketaService {

    List<Anketa> findAllBy();

    Anketa findFirstByEmail(String Email);

    void save(Anketa anketa);
        void saveAndFlush(Anketa anketa);

}
