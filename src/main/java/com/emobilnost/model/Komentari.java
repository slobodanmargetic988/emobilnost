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
@Table(name = "komentari")
//@EntityListeners(AuditingEntityListener.class)
public class Komentari {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    
 

    @Column(name = "tekst")
    private String tekst;

     @Column(name = "datum")
    private LocalDate datum;
    
    @Column(name = "ime")
    private String ime;
    
     @Column(name = "aktivan")
    private Boolean aktivan;

     @ManyToOne
     @JoinColumn(name="vest_id", nullable = false)
    private Vesti vest; 
     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vesti getVest() {
        return vest;
    }

    public void setVest(Vesti vest) {
        this.vest = vest;
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

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Boolean getAktivan() {
        return aktivan;
    }

    public void setAktivan(Boolean aktivan) {
        this.aktivan = aktivan;
    }

    
    
 
}
