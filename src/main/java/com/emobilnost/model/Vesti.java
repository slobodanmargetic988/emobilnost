/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.model;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Aleksandra
 */
@Entity
@Table(name = "vesti")
//@EntityListeners(AuditingEntityListener.class)
public class Vesti {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "tekst")
    private String tekst;

     @Column(name = "datum")
    private LocalDate datum;
    
    @Column(name = "naslov")
    private String naslov;
    
   @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "slika", referencedColumnName = "id")
    private Slika slika;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public Slika getSlika() {
        return slika;
    }

    public void setSlika(Slika slika) {
        this.slika = slika;
    }

    
 
}
