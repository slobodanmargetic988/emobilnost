/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.service;

import com.emobilnost.model.ColorPaleta;
import com.emobilnost.model.Korpa;
import com.emobilnost.model.KorpaProizvodi;
import com.emobilnost.model.Proizvodi;
import com.emobilnost.repository.KorpaProizvodiRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aleksandra
 */
@Service
public class KorpaProizvodiServiceImpl implements KorpaProizvodiService {

    @Autowired
    private KorpaProizvodiRepository korpaProizvodiRepository;

    @Override
    public List<KorpaProizvodi> findAllBy() {
        return korpaProizvodiRepository.findAllBy();
    }

    @Override
    public void save(KorpaProizvodi korpaProizvodi) {

        korpaProizvodiRepository.save(korpaProizvodi);
    }

    @Override
    public void delete(KorpaProizvodi korpaProizvodi) {
        korpaProizvodiRepository.delete(korpaProizvodi);
    }

    @Override
    public KorpaProizvodi findFirstByKorpaAndProizvod(Korpa korpa, Proizvodi proizvod) {
        return korpaProizvodiRepository.findFirstByKorpaAndProizvod(korpa, proizvod);
    }

    @Override
    public KorpaProizvodi findFirstByKorpaAndProizvodAndBoja(Korpa korpa, Proizvodi proizvod, ColorPaleta boja) {
        return korpaProizvodiRepository.findFirstByKorpaAndProizvodAndBoja(korpa, proizvod, boja);
    }

    @Override
    public void saveAndFlush(KorpaProizvodi korpaProizvodi) {

        korpaProizvodiRepository.saveAndFlush(korpaProizvodi);
    }

    @Override
    public KorpaProizvodi findOne(Integer kproizvod_id) {
        return korpaProizvodiRepository.findFirstById(kproizvod_id);
    }
}
