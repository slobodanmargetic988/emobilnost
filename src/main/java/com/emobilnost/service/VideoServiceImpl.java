/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.service;

import com.emobilnost.model.Photo;
import com.emobilnost.model.Proizvodi;
import com.emobilnost.model.Slika;
import com.emobilnost.model.Video;
import com.emobilnost.repository.PhotoRepository;
import com.emobilnost.repository.SlikaRepository;
import com.emobilnost.repository.VideoRepository;
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
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public List<Video> findAllBy() {
        return videoRepository.findAllBy();
    }
  @Override
    public Page<Video> findAllBy(Pageable pageable) {
        return videoRepository.findAllBy(pageable);
    }
    
  
    
  @Override
    public void save(Video slika){
        videoRepository.save(slika);
    }
    
 
  @Override
    public Video findFirstById(Integer id){
    return videoRepository.findFirstById(id);
    
    }
}
