/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.model;

import com.emobilnost.repository.AnketaRepository;
import com.emobilnost.service.AnketaService;
import com.emobilnost.service.AnketaServiceImpl;
import com.emobilnost.service.ColorPaletaService;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Aleksandra
 */
@Entity
@Table(name = "clanovi")
//@EntityListeners(AuditingEntityListener.class)
public class Clanovi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "ime")
    private String ime;

    @Column(name = "prezime")
    private String prezime;

    @Column(name = "email")
    private String email;

    @Column(name = "adresa")
    private String adresa;

    @Column(name = "mesto")
    private String mesto;

    @Column(name = "postanski_broj")
    private String postanski_broj;

    @Column(name = "drzava")
    private String drzava;

    @Column(name = "broj_telefona")
    private String broj_telefona;

    @Column(name = "jmbg")
    private String jmbg;

    @Column(name = "naziv_pravne_osobe")
    private String naziv_pravne_osobe;

    @Column(name = "pib")
    private Integer pib;

    @Column(name = "is_pravno_lice")
    private Boolean is_pravno_lice;

    @Column(name = "datum_pocetka_clanstva")
    private Date datum_pocetka_clanstva;

    @Column(name = "datum_isteka_clanstva")
    private Date datumisteka;
    
     @Column(name = "newsletter")
    private Boolean newsletter;
     
           @Column(name = "marka")
    private String marka;
               @Column(name = "registracija")
    private String registracija;
                    @Column(name = "imaev")
    private boolean imaev;

    @OneToOne(mappedBy = "clan")
    private Anketa anketa;

    
    
    public Anketa getAnketa() {
        return anketa;
    }

    public void setAnketa(Anketa anketa) {
        this.anketa = anketa;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getRegistracija() {
        return registracija;
    }

    public void setRegistracija(String registracija) {
        this.registracija = registracija;
    }

    public boolean isImaev() {
        return imaev;
    }

    public void setImaev(boolean imaev) {
        this.imaev = imaev;
    }
    
    
    
    
    public Boolean getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(Boolean newsletter) {
        this.newsletter = newsletter;
    }


     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getPostanski_broj() {
        return postanski_broj;
    }

    public void setPostanski_broj(String postanski_broj) {
        this.postanski_broj = postanski_broj;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getBroj_telefona() {
        return broj_telefona;
    }

    public void setBroj_telefona(String broj_telefona) {
        this.broj_telefona = broj_telefona;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getNaziv_pravne_osobe() {
        return naziv_pravne_osobe;
    }

    public void setNaziv_pravne_osobe(String naziv_pravne_osobe) {
        this.naziv_pravne_osobe = naziv_pravne_osobe;
    }

    public Integer getPib() {
        return pib;
    }

    public void setPib(Integer pib) {
        this.pib = pib;
    }

    public Boolean getIs_pravno_lice() {
        return is_pravno_lice;
    }

    public void setIs_pravno_lice(Boolean is_pravno_lice) {
        this.is_pravno_lice = is_pravno_lice;
    }

    public Date getDatum_pocetka_clanstva() {
        return datum_pocetka_clanstva;
    }

    public void setDatum_pocetka_clanstva(Date datum_pocetka_clanstva) {
        this.datum_pocetka_clanstva = datum_pocetka_clanstva;
    }

    public Date getDatumisteka() {
        return datumisteka;
    }

    public void setDatumisteka(Date datumisteka) {
        this.datumisteka = datumisteka;
    }


}
