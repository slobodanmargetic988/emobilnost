/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.controller;

import com.emobilnost.configuration.EmobilityUserPrincipal;
import com.emobilnost.model.Clanovi;
import com.emobilnost.model.ColorPaleta;
import com.emobilnost.model.Korpa;
import com.emobilnost.model.KorpaProizvodi;
import com.emobilnost.model.Photo;
import com.emobilnost.model.Proizvodi;
import com.emobilnost.model.ResetTokeni;
import com.emobilnost.model.Users;
import com.emobilnost.model.ZavrsenePorudzbine;
import com.emobilnost.service.ClanoviService;
import com.emobilnost.service.ColorPaletaService;
import com.emobilnost.service.KorpaService;
import com.emobilnost.service.PhotoService;
import com.emobilnost.service.ProizvodiService;
import com.emobilnost.service.ResetTokeniService;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.emobilnost.service.UsersService;
import com.emobilnost.service.ZavrsenePorudzbineService;
import com.emobilnost.storage.StorageService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Aleksandra
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
public class MainController {

    @GetMapping(value = "/admin-pocetna")
    public String adminC4bHome(final Model model) {

        return "main/admin-pocetna";
    }

     @GetMapping(value = "/mercedes-benz-vs-mercedes-electric")
    public String mercedesBenzVsMercedesElectric(final Model model) {
        return "main/mercedes-benz-vs-mercedes-electric";
    }
    
    @GetMapping(value = "/enyaq-iv-globalna-prica-o-uspehu-brenda-skoda")
    public String skodaEnyaqGlobalnaPricaOUspehu(final Model model) {
        return "main/enyaq-iv-globalna-prica-o-uspehu-brenda-skoda";
    }

    @GetMapping(value = "/skoda-enyaq-iv-osvojio-nagradu-zlatni-volan-za-najbolji-električni-suv-2021")
    public String skodaEnyaqNagrada(final Model model) {
        return "main/skoda-enyaq-iv-osvojio-nagradu-zlatni-volan-za-najbolji-električni-suv-2021";
    }

    @GetMapping(value = "/dodaj-clana")
    public String dodajClana(final Model model) {
//        model.addAttribute("listaClanova", clanoviService.findAllBy());

        return "main/dodaj-clana";
    }

    @PostMapping(value = "/napraviClana")
    public String napraviVest(final Model model, final HttpServletRequest request,
            RedirectAttributes redirectAttributes,
            @RequestParam(name = "ime", defaultValue = "/") String ime,
            @RequestParam(name = "prezime", defaultValue = "/") String prezime,
            @RequestParam(name = "email", defaultValue = "/") String email,
            @RequestParam(name = "adresa", defaultValue = "/") String adresa,
            @RequestParam(name = "mesto", defaultValue = "/") String mesto,
            @RequestParam(name = "postanski_broj", defaultValue = "/") String postanski_broj,
            @RequestParam(name = "drzava", defaultValue = "/") String drzava,
            @RequestParam(name = "broj_telefona", defaultValue = "/") String broj_telefona,
            @RequestParam(name = "jmbg", defaultValue = "/") String jmbg,
            @RequestParam(name = "naziv_pravne_osobe", defaultValue = "/") String naziv_pravne_osobe,
            @RequestParam(name = "pib", defaultValue = "/") Integer pib,
            //            @RequestParam(name = "is_pravno_lice") Boolean is_pravno_lice,
            @RequestParam(name = "datum_pocetka_clanstva") Calendar datum_pocetka_clanstva,
            //                        @RequestParam(name = "datum_isteka_clanstva") LocalDate datum_isteka_clanstva,
            @RequestParam(value = "action", required = true) String action
    ) {

        Clanovi clan = new Clanovi();
        clan.setIme(ime);
        clan.setPrezime(prezime);
        clan.setEmail(email);
        clan.setAdresa(adresa);
        clan.setMesto(mesto);
        clan.setPostanski_broj(postanski_broj);
        clan.setDrzava(drzava);
        clan.setBroj_telefona(broj_telefona);
        clan.setJmbg(jmbg);
        clan.setNaziv_pravne_osobe(naziv_pravne_osobe);
        clan.setPib(pib);
//        clan.setDatum_pocetka_clanstva(datum_pocetka_clanstva);
//        clan.setDatum_isteka_clanstva(datum_isteka_clanstva);

        clan.setDatum_pocetka_clanstva(datum_pocetka_clanstva.getTime());
        datum_pocetka_clanstva.add(Calendar.YEAR, 1);
        clan.setDatumisteka(datum_pocetka_clanstva.getTime());

        try {

            clanoviService.save(clan);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

            return "redirect:/admin-pocetna";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Uspešno ste dodali novog člana!");

        return "redirect:/admin-pocetna";
    }

    @GetMapping(value = "/pregled-clanova")
    public String pregledClanova(final Model model) {
        model.addAttribute("listaClanova", clanoviService.findAllByOrderByDatumistekaclanstvaAsc());

        return "main/pregled-clanova";
    }

    @GetMapping("/prvih-30000km-na-struju")
    public String prvih30000kmNaStruju(Model model) {
        return "main/prvih-30000km-na-struju";
    }

    @GetMapping("/miodrag-makaric-mica")
    public String miodragMakaricMica(Model model) {
        return "main/miodrag-makaric-mica";
    }

    @GetMapping("/bonton-za-vozace-elektricnih-vozila")
    public String jednaVest(Model model) {
        return "main/bonton-za-vozace-elektricnih-vozila";
    }

    @GetMapping("/mreza-punjaca")
    public String mrezaPunjaca(Model model) {
        return "main/mreza-punjaca";
    }

    @GetMapping("/newsletterOdjava")
    public String newsletterOdjava(Model model,
            RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")) {
            Users myUser = ((EmobilityUserPrincipal) authentication.getPrincipal()).getUser();

            Users user = userService.findFirstByEmail(myUser.getEmail());
        }

        redirectAttributes.addFlashAttribute("successMessage", "Uspešno ste se odjavili sa newsletter-a.");

        return "main/profil";
    }

    @GetMapping("/promena-lozinke")
    public String changePassword(Model model,
            RedirectAttributes redirectAttributes
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")) {
            Users myUser = ((EmobilityUserPrincipal) authentication.getPrincipal()).getUser();

            Users user = userService.findFirstByEmail(myUser.getEmail());
            model.addAttribute("userLogedIn", user);
        }

        return "main/promena-lozinke";
    }

    @RequestMapping(value = "/promena-lozinke/save", method = RequestMethod.POST)
    public String profilSave(final Model model, final HttpServletRequest request,
            RedirectAttributes redirectAttributes,
            @RequestParam(name = "oldPassword", required = false) String oldPassword,
            @RequestParam(name = "lozinkaRepeat", required = false) String lozinkaRepeat,
            @RequestParam(name = "newPassword", required = false) String newPassword
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Users myUser = ((EmobilityUserPrincipal) authentication.getPrincipal()).getUser();

        Users user = userService.findFirstByEmail(myUser.getEmail());

        if (bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {

            if (!newPassword.equals("")) {
                if (lozinkaRepeat.equals(newPassword)) {
                    if (newPassword.length() >= 8) {
                        user.setPassword(bCryptPasswordEncoder.encode(newPassword));

                    } else {
                        redirectAttributes.addFlashAttribute("errorMessage", "Lozinka mora imati najmanje 8 karaktera.");

                        return "redirect:/promena-lozinke";
                    }

                } else {
                    redirectAttributes.addFlashAttribute("errorMessage", "Ponovljena lozinka nije ista kao lozinka.");

                    return "redirect:/promena-lozinke";
                }
            }
            try {

                userService.save(user);

            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

                return "redirect:/promena-lozinke";
            }
            redirectAttributes.addFlashAttribute("successMessage", "Lozinka je uspešno izmenjena.");

            return "redirect:/promena-lozinke";

        } else {
            //System.out.println(oldPassword+"    "+oldPasswordEncrypted+"  "+user.getPassword());
            redirectAttributes.addFlashAttribute("errorMessage", "Stara lozinka se ne poklapa sa lozinkom u bazi.");

            return "redirect:/promena-lozinke";
        }
    }

    @GetMapping("/profil-edit")
    public String profilEdit(Model model,
            RedirectAttributes redirectAttributes
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")) {
            Users myUser = ((EmobilityUserPrincipal) authentication.getPrincipal()).getUser();

            Users user = userService.findFirstByEmail(myUser.getEmail());
            model.addAttribute("userLogedIn", user);
            Clanovi clan = clanoviService.findFirstByEmail(myUser.getEmail());
            model.addAttribute("clan", clan);
        }

        return "main/profil-edit";
    }

    @GetMapping("/u-pripremi")
    public String emobilnostUpripremi(Model model) {
        return "main/u-pripremi";
    }

    @GetMapping("/politika-kolacica")
    public String politikaKolacica(Model model) {
        return "main/politika-kolacica";
    }

    @GetMapping("/politika-privatnosti")
    public String politikaPrivatnosti(Model model) {
        return "main/politika-privatnosti";
    }

    @GetMapping("/statut")
    public String statut(Model model) {
        return "main/statut";
    }

    @GetMapping("/uclani-se")
    public String uclaniSe(Model model) {
        return "main/uclani-se";
    }

    @GetMapping("/saveti")
    public String saveti(Model model) {
        return "main/saveti";
    }

    @GetMapping("/subvencije")
    public String subvencije(Model model) {
        return "main/subvencije";
    }

    @GetMapping("/vesti")
    public String vesti(Model model) {
        return "main/vesti";
    }

    @GetMapping("/pogodnosti")
    public String pogodnosti(Model model) {
        return "main/pogodnosti";
    }

    @GetMapping("/loginTry")
    public String login(final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return "main/registracija";
    }

    @GetMapping("/")
    public String homePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")) {
            Users myUser = ((EmobilityUserPrincipal) authentication.getPrincipal()).getUser();
            model.addAttribute("user", myUser);
        }

        return "main/home";
    }

    @GetMapping("/uspesanlogin")
    public String homePageLogin(Model model,
            RedirectAttributes redirectAttributes
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")) {
            Users myUser = ((EmobilityUserPrincipal) authentication.getPrincipal()).getUser();

            Users user = userService.findFirstByEmail(myUser.getEmail());
            model.addAttribute("userLogedIn", user);
        }

        redirectAttributes.addFlashAttribute("successMessageLogin", "Korisnik je uspesno prijavljen.");

        return "redirect:/";
    }

    @GetMapping("/profil")
    public String profil(Model model,
            RedirectAttributes redirectAttributes
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")) {
            Users myUser = ((EmobilityUserPrincipal) authentication.getPrincipal()).getUser();

            Users user = userService.findFirstByEmail(myUser.getEmail());
            model.addAttribute("userLogedIn", user);
            Clanovi clan = clanoviService.findFirstByEmail(myUser.getEmail());
            model.addAttribute("clan", clan);
        }

        return "/main/profil";
    }

    @RequestMapping(value = "/profil-edit/save", method = RequestMethod.POST)
    public String profilSave(final Model model, final HttpServletRequest request,
            RedirectAttributes redirectAttributes,
            @RequestParam(name = "prezime", required = false) String prezime,
            @RequestParam(name = "ime", required = false) String ime,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "adresa", required = false) String adresa,
            @RequestParam(name = "mesto", required = false) String mesto,
            @RequestParam(name = "postanskibroj", required = false) String postanskibroj,
            @RequestParam(name = "telefon", required = false) String telefon,
            @RequestParam(name = "drzava", required = false) String drzava,
            @RequestParam(name = "jmbg", required = false) String jmbg,
            @RequestParam(name = "naziv_pravne_osobe", defaultValue = "/") String naziv_pravne_osobe,
            @RequestParam(name = "pib", defaultValue = "0") Integer pib
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Users myUser = ((EmobilityUserPrincipal) authentication.getPrincipal()).getUser();

        Users user = userService.findFirstByEmail(myUser.getEmail());
        Clanovi clan = clanoviService.findFirstByEmail(myUser.getEmail());
        user.setIme(ime);
        user.setPrezime(prezime);
        user.setEmail(email);

        user.setAdresa(adresa);
        user.setPostanski_broj(postanskibroj);
        user.setMesto(mesto);
        user.setBroj_telefona(telefon);
        clan.setDrzava(drzava);
        clan.setJmbg(jmbg);
        clan.setNaziv_pravne_osobe(naziv_pravne_osobe);
        clan.setPib(pib);

        try {

            userService.save(user);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

            return "redirect:/profil-edit";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Profil je uspešno izmenjen.");

        return "redirect:/profil-edit";
    }

    @GetMapping(value = "/shop")
    public String publicShopMargotekstil(final Model model, @PageableDefault(value = 12) final Pageable pageable) {

        model.addAttribute("listaProizvoda", proizvodiService.findAllByActiveOrderByImeAsc(true, pageable));
        model.addAttribute("listakategorija", proizvodiService.findListaKategorija());
        model.addAttribute("trenutnaKategorija", "sveKategorije");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) {
            return "neregistrovani/shop";

        }

        return "main/shop";
    }

    @GetMapping(value = "/shop/{kategorija}")
    public String shopKategorijaMargotekstil(final Model model, @PageableDefault(value = 12) final Pageable pageable,
            @PathVariable final String kategorija
    ) {
        model.addAttribute("listaProizvoda", proizvodiService.findByKategorijaOrderByActiveDescImeAsc(kategorija, pageable));
        model.addAttribute("listakategorija", proizvodiService.findListaKategorija());
        model.addAttribute("trenutnaKategorija", kategorija);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) {
            return "neregistrovani/shop";
        }
        return "main/shop";
    }

    @GetMapping("/test")
    public String testPage(Model model) {
        return "main/test";
    }

    @GetMapping("/uslovi-koriscenja")
    public String usloviKoriscenja(Model model) {
        return "main/uslovi-koriscenja";
    }

    @GetMapping("/infoDostava")
    public String infoDostava(Model model) {
        return "main/infoDostava";
    }

    @GetMapping(value = "/kontakt")
    public String publicContact(final Model model) {

        return "main/kontakt";
    }

    @GetMapping(value = "/o-nama")
    public String publicAbout(final Model model) {

        return "main/o-nama";
    }

    @GetMapping(value = "/galerija")
    public String publicGalerijaMargotekstil(final Model model, @PageableDefault(value = 12) final Pageable pageable) {

        model.addAttribute("listaSlika", photoService.findByProizvodIsNullAndGlavnazaproizvodIsNullAndActive(true, pageable));

        return "main/galerija";
    }

    @GetMapping(value = "/photo/{photoId}")
    public ResponseEntity<Resource> servePhoto(@PathVariable(name = "photoId") final Integer photoId) {

        Photo photo = photoService.findFirstById(photoId);
        String filename = photo.getFilename();
        Resource file = storageService.loadAsResource(0, filename);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }

    @Autowired
    ColorPaletaService colorPaletaService;

    @GetMapping(value = "/boja/{bojaId}")
    public ResponseEntity<Resource> serveBoja(@PathVariable(name = "bojaId") final Integer bojaId) {

        ColorPaleta boja = colorPaletaService.findFirstById(bojaId);
        String filename = boja.getFilename();
        Resource file = storageService.loadAsResource(boja.getProizvod().getId(), filename);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }

    @GetMapping(value = "/photo/{proizvodId}/{photoId}")
    public ResponseEntity<Resource> servePhotoProizvod(@PathVariable(name = "proizvodId") final Integer proizvodId,
            @PathVariable(name = "photoId") final Integer photoId
    ) {

        Photo photo = photoService.findFirstById(photoId);

        String filename = photo.getFilename();
        Resource file = storageService.loadAsResource(proizvodId, filename);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }

    @Autowired
    PhotoService photoService;

    @Autowired
    StorageService storageService;

    @Autowired
    ProizvodiService proizvodiService;

    @GetMapping(value = "/proizvod/{proizvodId}")
    public String publicProizvodMargotekstil(final Model model,
            @PathVariable final Integer proizvodId
    ) {
        model.addAttribute("proizvod", proizvodiService.findFirstById(proizvodId));
        List<Proizvodi> first3 = proizvodiService.findFirstFew(4);
        hocemoSamoTri(first3, proizvodId);
        model.addAttribute("prvaTriProizvoda", first3);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) {
            return "neregistrovani/proizvod";
        }

        return "main/proizvod";
    }

    void hocemoSamoTri(List<Proizvodi> first3, Integer proizvodId) {
        if (first3.get(0).getId() == proizvodId) {
            first3.remove(first3.get(0));
        } else {
            if (first3.get(1).getId() == proizvodId) {
                first3.remove(first3.get(1));
            } else {
                if (first3.get(2).getId() == proizvodId) {
                    first3.remove(first3.get(2));
                } else {
                    first3.remove(first3.get(2));
                }
            }
        }

    }

    @GetMapping(value = "/registracija")
    public String publicRegistrationMargotekstil(final Model model) {

        return "main/registracija";
    }

    @GetMapping(value = "/error404")
    public String publicErrorMargotekstil(final Model model) {

        return "main/error404";
    }

    @GetMapping(value = "/dekorativniProgram")
    public String publicDekorativniProgramMargotekstil(final Model model) {

        return "main/dekorativniProgram";
    }

    @GetMapping(value = "/petmania")
    public String publicPetmaniaMargotekstil(final Model model) {

        return "main/petmania";
    }

    @GetMapping(value = "/horeca-tekstil")
    public String publicHorecaTekstilMargotekstil(final Model model) {

        return "main/horeca-tekstil";
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsersService userService;
    @Autowired
    private KorpaService korpaService;

    //  @PostMapping(value = "/posaljiPoruku")
    @RequestMapping(value = "/posaljiPoruku", method = RequestMethod.POST)
    public String posaljiPoruku(final Model model,
            final HttpServletRequest request,
            RedirectAttributes redirectAttributes,
            @RequestParam(name = "prezime") String prezime,
            @RequestParam(name = "ime") String ime,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "telefon") String telefon,
            @RequestParam(name = "poruka") String poruka
    ) {
        try {
            EmailController.SendEmailPoruka(ime, email, telefon, poruka);
            EmailController.SendEmailPorukaPoslata(email, ime, prezime);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

            return "redirect:/";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(final Model model, final HttpServletRequest request,
            RedirectAttributes redirectAttributes,
            @RequestParam(name = "prezime") String prezime,
            @RequestParam(name = "ime") String ime,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String lozinka,
            @RequestParam(name = "lozinkaRepeat") String lozinkaRepeat,
            @RequestParam(name = "adresa") String adresa,
            @RequestParam(name = "mesto") String mesto,
            @RequestParam(name = "postanskibroj") String postanskibroj,
            @RequestParam(name = "telefon") String telefon
    ) {
        if (userService.findFirstByEmail(email) != null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Korisnik sa unetom email adresom već postoji.");

            return "redirect:/registracija";
        }

        Users user = new Users();
        user.setIme(ime);
        user.setPrezime(prezime);
        user.setEmail(email);

        user.setAdresa(adresa);
        user.setPostanski_broj(postanskibroj);
        user.setMesto(mesto);
        user.setBroj_telefona(telefon);
        user.setRole("SHOPPER");

        if (lozinka.equals(lozinkaRepeat)) {
            if (lozinka.length() >= 8) {
                user.setPassword(bCryptPasswordEncoder.encode(lozinka));
                try {
                    Korpa korpa = new Korpa();

                    korpaService.save(korpa);
                    user.setKorpa(korpa);
                    userService.save(user);
                    EmailController.SendEmailRegistracija(user);
                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

                    return "redirect:/registracija";
                }
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Lozinka mora imati najmanje 8 karaktera");

                return "redirect:/registracija";
            }

        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Ponovljena lozinka nije ista kao lozinka");

            return "redirect:/registracija";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Uspešno ste registrovali novi nalog.");

        return "redirect:/registracija";
    }

    @Autowired
    private ResetTokeniService resetTokeniService;

    @PostMapping(value = "/reset-lozinke")
    public String publicResetPasswordMargotekstil(final Model model,
            @RequestParam(name = "email") String email,
            RedirectAttributes redirectAttributes
    ) {

        if (userService.findFirstByEmail(email) == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Korisnik sa unetim emailom ne postoji.");
            return "redirect:/registracija";
        }

        Users user = userService.findFirstByEmail(email);
        ResetTokeni resetTokeni = new ResetTokeni();
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 30;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        resetTokeni.setVrednost(generatedString);
        resetTokeni.setEmail(email);
        resetTokeniService.save(resetTokeni);
        try {
            EmailController.SendResetPassword(email, generatedString, user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        redirectAttributes.addFlashAttribute("successMessage", "Poslat vam je link na email za resetovanje lozinke.");

        return "redirect:/registracija";
    }

    @GetMapping(value = "/reset-lozinke")
    public String resetPasswordMargotekstil(final Model model) {

        return "main/reset-lozinke";
    }

    @GetMapping(value = "/reset-lozinke/{resetToken}")
    public String resetPassword2Margotekstil(final Model model,
            @PathVariable final String resetToken
    ) {
        ResetTokeni resetTokeni = resetTokeniService.findFirstByVrednost(resetToken);
        if (resetTokeni != null) {
            model.addAttribute("email", resetTokeni.getEmail());
        }
        resetTokeniService.delete(resetTokeni);
        return "main/reset-lozinke";
    }

    @PostMapping(value = "/reset-lozinke/{email}")
    public String resetPassword3Margotekstil(final Model model,
            RedirectAttributes redirectAttributes,
            @PathVariable final String email,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "passwordrepeat") String passwordrepeat
    ) {

        if (password.equals(passwordrepeat)) {
            if (password.length() >= 8) {
                Users user = userService.findFirstByEmail(email);
                user.setPassword(bCryptPasswordEncoder.encode(password));
                try {
                    userService.save(user);

                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

                    return "redirect:/registracija";
                }
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Lozinka mora imati najmanje 8 karaktera");

                return "redirect:/registracija";
            }

        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Ponovljena lozinka nije ista kao lozinka");

            return "redirect:/registracija";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Lozinka je uspešno promenjena.");

        return "redirect:/registracija";
    }

    @Autowired
    private ZavrsenePorudzbineService zavrsenePorudzbineService;

    @GetMapping("/istorijaKupovine")
    public String istorija(Model model,
            RedirectAttributes redirectAttributes
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Users myUser = ((EmobilityUserPrincipal) authentication.getPrincipal()).getUser();

        Users user = userService.findFirstByEmail(myUser.getEmail());
        model.addAttribute("userLogedIn", user);

        List<ZavrsenePorudzbine> sveporudzbine = zavrsenePorudzbineService.findAllByUser(user);
        List<Proizvodi> kupljeniproizvodi = new ArrayList();

        //pravimo listu svih kupljenih proizvoda
        for (ZavrsenePorudzbine zavrsenaporudzbina : sveporudzbine) {
            List<KorpaProizvodi> korpaproizvodi = zavrsenaporudzbina.getKorpa().getKorpaproizvodi();
            for (KorpaProizvodi korpaProizvod : korpaproizvodi) {

                if (!kupljeniproizvodi.contains(korpaProizvod.getProizvod())) {
                    kupljeniproizvodi.add(korpaProizvod.getProizvod());
                };

            }
        }

        model.addAttribute("kupljeniproizvodi", kupljeniproizvodi);

        return "/main/istorijaKupovine";
    }

    @Autowired
    UsersService usersService;
    @Autowired
    ClanoviService clanoviService;

    @PostMapping(value = "/noviClan")
    public String noviClan(final Model model,
            @RequestParam(name = "ime", defaultValue = "/") String ime,
            @RequestParam(name = "prezime", defaultValue = "/") String prezime,
            @RequestParam(name = "email", defaultValue = "/") String email,
            @RequestParam(name = "adresa", defaultValue = "/") String adresa,
            @RequestParam(name = "mesto", defaultValue = "/") String mesto,
            @RequestParam(name = "postanski_broj", defaultValue = "/") String postanski_broj,
            @RequestParam(name = "drzava", defaultValue = "/") String drzava,
            @RequestParam(name = "broj_telefona", defaultValue = "/") String broj_telefona,
            @RequestParam(name = "password", defaultValue = "/") String password,
            @RequestParam(name = "lozinkaRepeat", defaultValue = "/") String lozinkaRepeat,
            @RequestParam(name = "jmbg", defaultValue = "/") String jmbg,
            @RequestParam(name = "naziv_pravne_osobe", defaultValue = "/") String naziv_pravne_osobe,
            @RequestParam(name = "pib", defaultValue = "0") Integer pib,
            @RequestParam(name = "fizickoilipravnolice", defaultValue = "fizickolice") String fizickoilipravnolice,
            @RequestParam(name = "placanje", defaultValue = "uplatnica") String placanje,
            RedirectAttributes redirectAttributes
    ) {

        Clanovi clan = new Clanovi();
        clan.setAdresa(adresa);
        clan.setBroj_telefona(broj_telefona);
        clan.setDatumisteka(java.sql.Date.valueOf(LocalDate.now().plusYears(1)));
        clan.setDatum_pocetka_clanstva(java.sql.Date.valueOf(LocalDate.now()));
        clan.setDrzava(drzava);
        clan.setEmail(email);
        clan.setIme(ime);
        clan.setJmbg(jmbg);
        clan.setNaziv_pravne_osobe(naziv_pravne_osobe);
        clan.setPib(pib);
        clan.setPostanski_broj(postanski_broj);
        clan.setMesto(mesto);
        clan.setPrezime(prezime);

        if (fizickoilipravnolice.equals("pravnolice")) {
            clan.setIs_pravno_lice(Boolean.TRUE);
        } else {
            clan.setIs_pravno_lice(Boolean.FALSE);

        }
        Users user = userService.findFirstByEmail(email);
        if (user == null) {

            user = new Users();

            user.setIme(ime);
            user.setPrezime(prezime);
            user.setEmail(email);
            user.setAdresa(adresa);
            user.setPostanski_broj(postanski_broj);
            user.setMesto(mesto);
            user.setBroj_telefona(broj_telefona);
            user.setRole("SHOPPER");

            if (password.equals(lozinkaRepeat)) {

                user.setPassword(bCryptPasswordEncoder.encode(password));

            } else {

                redirectAttributes.addFlashAttribute("errorMessage", "Ponovljena lozinka nije ista kao lozinka");

                return "redirect:/uclani-se";
            }

        }
        try {

            userService.save(user);
            clanoviService.save(clan);
            EmailController.SendEmailUclanjen(clan);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

            return "redirect:/registracija";
        }

        if (placanje.equals("uplatnica")) {
            try {

                EmailController.SendEmailUclanjenUplatnica(clan);
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

            }

        } else {
            if (placanje.equals("faktura")) {
                //poslati fakturu
                try {
                    EmailController.SendEmailUclanjenFaktura(clan);
                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

                }
            } else {
                if (placanje.equals("kartica")) {
                    //treba resiti kartice
                }
            }

        }

        redirectAttributes.addFlashAttribute("successMessage", "Uspešno ste se uclanili.");

        return "redirect:/";

    }
}
