/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.repository;

import com.emobilnost.model.ColorPaleta;
import com.emobilnost.model.Korpa;
import com.emobilnost.model.KorpaProizvodi;
import com.emobilnost.model.Proizvodi;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Aleksandra
 */
@Repository("korpaProizvodiRepository")
public interface KorpaProizvodiRepository extends JpaRepository<KorpaProizvodi, Integer> {

    List<KorpaProizvodi> findAllBy();
    KorpaProizvodi findFirstById(Integer kproizvod_id);
    KorpaProizvodi findFirstByKorpaAndProizvod(Korpa korpa, Proizvodi proizvod);
KorpaProizvodi findFirstByKorpaAndProizvodAndBoja(Korpa korpa,Proizvodi proizvod,ColorPaleta  boja);
}
