/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.service;

import com.emobilnost.model.ColorPaleta;
import com.emobilnost.model.Photo;
import com.emobilnost.model.Proizvodi;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Aleksandra
 */
public interface ColorPaletaService {

    List<ColorPaleta> findAllBy();
     Page<ColorPaleta> findAllBy(Pageable pageable);
//     Page<ColorPaleta> findByProizvodIsNullAndGlavnazaproizvodIsNullAndActive(boolean active, Pageable pageable);
    
void save(ColorPaleta colorPaleta);
    ColorPaleta findFirstById(Integer id);

}
