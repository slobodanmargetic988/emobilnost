/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.repository;

import com.emobilnost.model.Clanovi;
import com.emobilnost.model.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Aleksandra
 */


@Repository("clanoviRepository")
public interface ClanoviRepository extends JpaRepository<Clanovi, Integer> {

    List<Clanovi> findAllBy();
    Clanovi findFirstByEmail(String email);
    Clanovi findFirstByIme(String ime);
    List<Clanovi> findAllByOrderByDatumistekaAsc();
     Clanovi findFirstById(Integer id);
}
