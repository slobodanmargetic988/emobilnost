/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emobilnost.controller;

import static com.emobilnost.controller.EmailController.uplatnicaSlika;
import com.emobilnost.model.Clanovi;
import com.emobilnost.model.Komentari;
import com.emobilnost.model.Korpa;
import com.emobilnost.model.KorpaProizvodi;
import com.emobilnost.model.Users;
import com.emobilnost.model.Vesti;
import com.emobilnost.model.ZavrsenePorudzbine;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

/**
 *
 * @author Aleksandra
 */
//@Controller
//@Configuration
public class EmailController {

//@Value("${serverip}")
//    private static String serverip;
//    
//    public static String serverip = "https://emobilnost.com";
    public static String serverip = "http://localhost:8082";
    static final String FROM = "slobodanmargetic988@gmail.com";
    static final String FROMNAME = "E-Mobilnost";
    static final String brojtekucegracuna = "160-6000001242211-59";
    static final String pathtouplatnicatemplate = "classpath:/static/img/uplatnicatemplate.pdf";

    static final String TO = "slobagamer@hotmail.com";
    static final String PRIMALACPORUKA = "nebojsa@emobilnost.rs";

    static final String SMTP_USERNAME = "slobodanmargetic988@gmail.com";
    static final String SMTP_PASSWORD = "plujxpfcbjabxpfj";
    // The name of the Configuration Set to use for this message.
    // If you comment out or remove this variable, you will also need to
    // comment out or remove the header below.
    static final String CONFIGSET = "ConfigSet";
    // Amazon SES SMTP host name. This example uses the US West (Oregon) region.
    // See https://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html#region-endpoints
    // for more information.
    static final String HOST = "smtp.gmail.com";
    // The port you will connect to on the Amazon SES SMTP endpoint.
    static final int PORT = 587;
    static final String SUBJECTC = "Confirmation email";
    static final String SUBJECTR = "Reset password";
    static final String SUBJECTPORUDZBINA = "Potvrda porudžbine";
    static final String SUBJECTPORUCENAROBA = "Poručeni proizvodi";
    static final String SUBJECTPrimljena = "Primljena poruka";
    public static String welcomeEmaillink = "https://emobilnost.com";
    public static String resetEmaillink = "https://emobilnost.com/reset-lozinke/";

    //kada bilo ko posalje komentar
    public static void SendKomentarAnyone(Komentari komentar, String ime) throws Exception {

        System.out.println(serverip);

        // Create a Properties object to contain connection configuration information.
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        // Create a Session object to represent a mail session with the specified properties.
        Session session = Session.getDefaultInstance(props);
        // Create a message with the specified information.
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(FROM));

        msg.setSubject(SUBJECTPrimljena);
        String BODY = String.join(
                System.getProperty("line.separator"),
                emailzaglavlje
                + emailsadrzajContainer
                + sendEmailKomentar(komentar, ime)
                + emailsadrzajContainerClose
                + emailfooter
        );
        msg.setContent(BODY, "text/html;charset=utf-8");
        // Add a configuration set header. Comment or delete the
        // next line if you are not using a configuration set
        msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
        // Create a transport.
        Transport transport = session.getTransport();
        // Send the message.
        try {
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (Exception ex) {
            String err = ex.getMessage();
        } finally {
            // Close and terminate the connection.
            transport.close();
        }
    }

    //registracija novog korisnika, pa tom korisniku stize poruka na email
    public static void SendEmailRegistracija(Users user) throws Exception {
        // Create a Properties object to contain connection configuration information.
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        // Create a Session object to represent a mail session with the specified properties.
        Session session = Session.getDefaultInstance(props);
        // Create a message with the specified information.
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
        msg.setSubject(SUBJECTC);
        String BODY = String.join(
                System.getProperty("line.separator"),
                emailzaglavlje
                + emailsadrzajContainer
                + napravljenNalog(user.getIme(), user.getPrezime(), user.getEmail())
                + emailsadrzajContainerClose
                + emailfooter
        );
        msg.setContent(BODY, "text/html;charset=utf-8");
        // Add a configuration set header. Comment or delete the
        // next line if you are not using a configuration set
        msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
        // Create a transport.
        Transport transport = session.getTransport();
        // Send the message.
        try {
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (Exception ex) {
            String err = ex.getMessage();
        } finally {
            // Close and terminate the connection.
            transport.close();
        }
    }

    public static void SendVasaPorudzbinaiUplatnicaEmail(Users user, ZavrsenePorudzbine zavrsenePorudzbine) throws Exception {
//        String proizvodid = "170";
//        String photoid = "27";
//        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
// Create a Properties object to contain connection configuration information.

        String recipient = zavrsenePorudzbine.getEmail();
        Korpa korpa = zavrsenePorudzbine.getKorpa();
        String nacinisporuke = zavrsenePorudzbine.getNacin_placanja();
        String komentar = zavrsenePorudzbine.getNapomena();

        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        Transport transport = session.getTransport();
        try {
            // Create a Session object to represent a mail session with the specified properties.

            // Create a message with the specified information.
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM, FROMNAME));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            msg.setSubject(SUBJECTPORUDZBINA);
            String BODY = String.join(
                    System.getProperty("line.separator"),
                    emailzaglavlje
                    + emailsadrzajContainer
                    + standardnaporuka(user.getIme(), user.getPrezime(), nacinisporuke)
                    + ""
                    + podaciokupcuiprodavcu(user, komentar, zavrsenePorudzbine)
                    + ""
                    + listaproizvoda(korpa)
                    + "           "
                    + cenaSumaproizvodasegment(korpa)
                    + uplatnicaSlika()
                    + emailsadrzajContainerClose
                    + emailfooter
            );
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setContent(BODY, "text/html;charset=utf-8");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            ClassLoader cl = ClassLoader.class.getClassLoader();
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
            Resource resource = resolver.getResource(pathtouplatnicatemplate);

            InputStream is = resource.getInputStream();

            PDDocument pDDocument = PDDocument.load(is);
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
            PDField fieldName = pDAcroForm.getField("uplatilac");
            fieldName.setValue(user.getIme() + " " + user.getPrezime() + "\n" + user.getAdresa() + "\n" + user.getPostanski_broj() + " " + user.getMesto()); // <-- Replacement
            PDField fieldName2 = pDAcroForm.getField("svrha");
            fieldName2.setValue("porudžbina br. 210" + korpa.getId());
            PDField fieldName3 = pDAcroForm.getField("primalac");
            fieldName3.setValue("Margotekstil doo \nČaslava Veljića 53");
            PDField fieldName4 = pDAcroForm.getField("sifra");
            fieldName4.setValue("221");
            PDField fieldName5 = pDAcroForm.getField("valuta");
            fieldName5.setValue("RSD");
            PDField fieldName6 = pDAcroForm.getField("iznos");
            fieldName6.setValue("" + String.format("%.2f", cenaSumaproizvoda(korpa)));
            PDField fieldName7 = pDAcroForm.getField("racun");
            fieldName7.setValue(brojtekucegracuna);
            PDField fieldName8 = pDAcroForm.getField("model");
            //fieldName8.setValue("2");
            PDField fieldName9 = pDAcroForm.getField("poziv");
            fieldName9.setValue("210" + korpa.getId());
            pDDocument.save(outputStream);
//pDDocument.save("uplatnicatest.pdf");
//pDDocument.close();

            byte[] bytes = outputStream.toByteArray();

            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");

            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("uplatnica.pdf");

            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);

            msg.setContent(mimeMultipart, "text/html;charset=utf-8");
            // Add a configuration set header. Comment or delete the
            // next line if you are not using a configuration set
            msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
            // Create a transport.

            // Send the message.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (Exception ex) {
            String err = ex.getMessage();
            System.out.println(ex);
        } finally {
            // Close and terminate the connection.
            transport.close();
        }
    }

    public static void SendEmailUclanjenUplatnica(Clanovi clan) throws Exception {
        // Create a Properties object to contain connection configuration information.
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        // Create a Session object to represent a mail session with the specified properties.
        Session session = Session.getDefaultInstance(props);
        // Create a message with the specified information.
        Transport transport = session.getTransport();
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM, FROMNAME));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(clan.getEmail()));
            msg.setSubject(SUBJECTC);
            String BODY = String.join(
                    System.getProperty("line.separator"),
                    emailzaglavlje
                    + emailsadrzajContainer
                    + napravljenNalog(clan.getIme(), clan.getPrezime(), clan.getEmail())
                    + emailsadrzajContainerClose
                    + emailfooter
            );
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setContent(BODY, "text/html;charset=utf-8");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            ClassLoader cl = ClassLoader.class.getClassLoader();
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
            Resource resource = resolver.getResource(pathtouplatnicatemplate);

            InputStream is = resource.getInputStream();

            PDDocument pDDocument = PDDocument.load(is);
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
            PDField fieldName = pDAcroForm.getField("uplatilac");
            String uplatilac = clan.getIme() + " " + clan.getPrezime() + "\n" + clan.getAdresa();
            if (!clan.getPostanski_broj().equals("/")) {
                uplatilac += "\n" + clan.getPostanski_broj();
                if (!clan.getMesto().equals("/")) {
                    uplatilac += " " + clan.getMesto();
                }
            }
            fieldName.setValue(uplatilac); // <-- Replacement
            PDField fieldName2 = pDAcroForm.getField("svrha");
            fieldName2.setValue("Članarina za udruženje građana \nE-Mobilnost");
            PDField fieldName3 = pDAcroForm.getField("primalac");
            fieldName3.setValue("Udruženje E-Mobilnost \nČaslava Veljića 53\nBeograd-Čukarica");
            PDField fieldName4 = pDAcroForm.getField("sifra");
            fieldName4.setValue("");
            PDField fieldName5 = pDAcroForm.getField("valuta");
            fieldName5.setValue("RSD");
            PDField fieldName6 = pDAcroForm.getField("iznos");
            fieldName6.setValue("6000");
            PDField fieldName7 = pDAcroForm.getField("racun");
            fieldName7.setValue(brojtekucegracuna);
            PDField fieldName8 = pDAcroForm.getField("model");
            //fieldName8.setValue("2");
            PDField fieldName9 = pDAcroForm.getField("poziv");
            fieldName9.setValue("");
            pDDocument.save(outputStream);
//pDDocument.save("uplatnicatest.pdf");
//pDDocument.close();

            byte[] bytes = outputStream.toByteArray();

            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");

            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("uplatnica.pdf");

            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);

            msg.setContent(mimeMultipart, "text/html;charset=utf-8");

            // Add a configuration set header. Comment or delete the
            // next line if you are not using a configuration set
            msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);

            // Send the message.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (Exception ex) {
            String err = ex.getMessage();
        } finally {
            // Close and terminate the connection.
            transport.close();
        }
    }

    public static void SendEmailUclanjenFaktura(Clanovi clan) throws Exception {
        // Create a Properties object to contain connection configuration information.
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        // Create a Session object to represent a mail session with the specified properties.
        Session session = Session.getDefaultInstance(props);
        // Create a message with the specified information.
        Transport transport = session.getTransport();
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM, FROMNAME));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(clan.getEmail()));
            msg.setSubject(SUBJECTC);
            String BODY = String.join(
                    System.getProperty("line.separator"),
                    emailzaglavlje
                    + emailsadrzajContainer
                    + napravljenNalog(clan.getIme(), clan.getPrezime(), clan.getEmail())
                    + emailsadrzajContainerClose
                    + emailfooter
            );
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setContent(BODY, "text/html;charset=utf-8");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            ClassLoader cl = ClassLoader.class.getClassLoader();
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
            Resource resource = resolver.getResource(pathtouplatnicatemplate);

            InputStream is = resource.getInputStream();

            PDDocument pDDocument = PDDocument.load(is);
            PDAcroForm pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
            PDField fieldName = pDAcroForm.getField("uplatilac");
            fieldName.setValue("");//clan.getIme()+" "+clan.getPrezime()+"\n"+clan.getAdresa()+"\n"+clan.getPostanski_broj()+" "+clan.getMesto()); // <-- Replacement
            PDField fieldName2 = pDAcroForm.getField("svrha");
            fieldName2.setValue("Članarina za udruženje građana \nE-Mobilnost");
            PDField fieldName3 = pDAcroForm.getField("primalac");
            fieldName3.setValue("Udruženje E-Mobilnost \nČaslava Veljića 53\nBeograd-Čukarica");
            PDField fieldName4 = pDAcroForm.getField("sifra");
            fieldName4.setValue("");
            PDField fieldName5 = pDAcroForm.getField("valuta");
            fieldName5.setValue("RSD");
            PDField fieldName6 = pDAcroForm.getField("iznos");
            fieldName6.setValue("35000");
            PDField fieldName7 = pDAcroForm.getField("racun");
            fieldName7.setValue(brojtekucegracuna);
            PDField fieldName8 = pDAcroForm.getField("model");
            //fieldName8.setValue("2");
            PDField fieldName9 = pDAcroForm.getField("poziv");
            fieldName9.setValue("");
            pDDocument.save(outputStream);
//pDDocument.save("uplatnicatest.pdf");
//pDDocument.close();

            byte[] bytes = outputStream.toByteArray();

            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");

            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("uplatnica.pdf");

            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);

            msg.setContent(mimeMultipart, "text/html;charset=utf-8");

            // Add a configuration set header. Comment or delete the
            // next line if you are not using a configuration set
            msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);

            // Send the message.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (Exception ex) {
            String err = ex.getMessage();
        } finally {
            // Close and terminate the connection.
            transport.close();
        }
    }
    
    
    public static void SendEmailUclanjenBesplatno(Clanovi clan) throws Exception {
        // Create a Properties object to contain connection configuration information.
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        // Create a Session object to represent a mail session with the specified properties.
        Session session = Session.getDefaultInstance(props);
        // Create a message with the specified information.
        Transport transport = session.getTransport();
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM, FROMNAME));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(clan.getEmail()));
            msg.setSubject(SUBJECTC);
            String BODY = String.join(
                    System.getProperty("line.separator"),
                    emailzaglavlje
                    + emailsadrzajContainer
                    + napravljenNalog(clan.getIme(), clan.getPrezime(), clan.getEmail())
                    + emailsadrzajContainerClose
                    + emailfooter
            );
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setContent(BODY, "text/html;charset=utf-8");
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            msg.setContent(mimeMultipart, "text/html;charset=utf-8");
            // Add a configuration set header. Comment or delete the
            // next line if you are not using a configuration set
            msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
            // Send the message.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (Exception ex) {
            String err = ex.getMessage();
        } finally {
            // Close and terminate the connection.
            transport.close();
        }
    }

    public static void SendEmailUclanjen(Clanovi clan) throws Exception {
        // Create a Properties object to contain connection configuration information.
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        // Create a Session object to represent a mail session with the specified properties.
        Session session = Session.getDefaultInstance(props);
        // Create a message with the specified information.
        Transport transport = session.getTransport();
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM, FROMNAME));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(PRIMALACPORUKA));
            msg.setSubject("Novi clan");
            String BODY = String.join(
                    System.getProperty("line.separator"),
                    emailzaglavlje
                    + emailsadrzajContainer
                    + napravljenNalogAdmin(clan)
                    + emailsadrzajContainerClose
                    + emailfooter
            );
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setContent(BODY, "text/html;charset=utf-8");

            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);

            msg.setContent(mimeMultipart, "text/html;charset=utf-8");

            // Add a configuration set header. Comment or delete the
            // next line if you are not using a configuration set
            msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);

            // Send the message.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (Exception ex) {
            String err = ex.getMessage();
        } finally {
            // Close and terminate the connection.
            transport.close();
        }
    }

    //kada neregistrovani korisnik posalje poruku preko forme na home ili kontakt strani - stize email Violeti
    public static void SendEmailPoruka(String ime, String telefon, String email, String poruka) throws Exception {
        // Create a Properties object to contain connection configuration information.

        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        // Create a Session object to represent a mail session with the specified properties.
        Session session = Session.getDefaultInstance(props);
        // Create a message with the specified information.
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));
        //    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(PRIMALACPORUKA));

        msg.setSubject(SUBJECTPrimljena);
        String BODY = String.join(
                System.getProperty("line.separator"),
                emailzaglavlje
                + emailsadrzajContainer
                + sendEmailPoruka(ime, email, telefon, poruka)
                + emailsadrzajContainerClose
                + emailfooter
        );
        msg.setContent(BODY, "text/html;charset=utf-8");
        // Add a configuration set header. Comment or delete the
        // next line if you are not using a configuration set
        msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
        // Create a transport.
        Transport transport = session.getTransport();
        // Send the message.
        try {
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (Exception ex) {
            String err = ex.getMessage();
        } finally {
            // Close and terminate the connection.
            transport.close();
        }
    }

    //kada neregistrovani korisnik posalje poruku preko forme na home ili kontakt strani - stize email korsniku
    public static void SendEmailPorukaPoslata(String email, String ime) throws Exception {
        // Create a Properties object to contain connection configuration information.
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        // Create a Session object to represent a mail session with the specified properties.
        Session session = Session.getDefaultInstance(props);
        // Create a message with the specified information.
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));

        msg.setSubject(SUBJECTPrimljena);
        String BODY = String.join(
                System.getProperty("line.separator"),
                emailzaglavlje
                + emailsadrzajContainer
                + sendEmailPorukaPoslata(email, ime)
                + emailsadrzajContainerClose
                + emailfooter
        );
        msg.setContent(BODY, "text/html;charset=utf-8");
        // Add a configuration set header. Comment or delete the
        // next line if you are not using a configuration set
        msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
        // Create a transport.
        Transport transport = session.getTransport();
        // Send the message.
        try {
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (Exception ex) {
            String err = ex.getMessage();
        } finally {
            // Close and terminate the connection.
            transport.close();
        }
    }

    //kada je korisnik zaboravio lozinku, pa da mu stigne email da je resetuje
    public static void SendResetPassword(String recipient, String resetToken, Users user) throws Exception {
        // Create a Properties object to contain connection configuration information.
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        // Create a Session object to represent a mail session with the specified properties.
        Session session = Session.getDefaultInstance(props);
        // Create a message with the specified information.
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        msg.setSubject(SUBJECTR);
        String BODY = String.join(
                System.getProperty("line.separator"),
                emailzaglavlje
                + emailsadrzajContainer
                + resetujLozinku(user.getIme(), user.getPrezime(), resetToken)
                + emailsadrzajContainerClose
                + emailfooter
        );
        msg.setContent(BODY, "text/html;charset=utf-8");
        // Add a configuration set header. Comment or delete the
        // next line if you are not using a configuration set
        msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
        // Create a transport.
        Transport transport = session.getTransport();
        // Send the message.
        try {
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (Exception ex) {
            String err = ex.getMessage();
        } finally {
            // Close and terminate the connection.
            transport.close();
        }
    }

    //kada ulogovani korisnik naruci proizvode, pa mu na email stigne "izvestaj" o tome / licno preuzimanje ili placanje prilikom preuzimanja
    public static void SendVasaPorudzbinaEmail(Users user, ZavrsenePorudzbine zavrsenePorudzbine) throws Exception {

        String recipient = zavrsenePorudzbine.getEmail();
        Korpa korpa = zavrsenePorudzbine.getKorpa();
        String nacinisporuke = zavrsenePorudzbine.getNacin_placanja();
        String komentar = zavrsenePorudzbine.getNapomena();
// Create a Properties object to contain connection configuration information.
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        // Create a Session object to represent a mail session with the specified properties.
        Session session = Session.getDefaultInstance(props);
        // Create a message with the specified information.
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        msg.setSubject(SUBJECTPORUDZBINA);

        String BODY = String.join(
                System.getProperty("line.separator"),
                emailzaglavlje
                + emailsadrzajContainer
                + standardnaporuka(user.getIme(), user.getPrezime(), nacinisporuke)
                + ""
                + podaciokupcuiprodavcu(user, komentar, zavrsenePorudzbine)
                + ""
                + listaproizvoda(korpa)
                + "           "
                + cenaSumaproizvodasegment(korpa)
                + emailsadrzajContainerClose
                + emailfooter
        );

        msg.setContent(BODY, "text/html;charset=utf-8");
        // Add a configuration set header. Comment or delete the
        // next line if you are not using a configuration set
        msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
        // Create a transport.
        Transport transport = session.getTransport();
        // Send the message.
        try {
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (Exception ex) {
            String err = ex.getMessage();
        } finally {
            // Close and terminate the connection.
            transport.close();
        }
    }

    //email da stigne Violeti o tome sta je kupac narucio
    public static void SendkorisnikPorucioEmail(Users user, ZavrsenePorudzbine zavrsenePorudzbine) throws Exception { //String recipient,
        // Create a Properties object to contain connection configuration information.

        String recipient = zavrsenePorudzbine.getEmail();
        Korpa korpa = zavrsenePorudzbine.getKorpa();
        String nacinisporuke = zavrsenePorudzbine.getNacin_placanja();
        String komentar = zavrsenePorudzbine.getNapomena();

        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        // Create a Session object to represent a mail session with the specified properties.
        Session session = Session.getDefaultInstance(props);
        // Create a message with the specified information.
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));
        // msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(FROM));

        msg.setSubject(SUBJECTPORUCENAROBA, "UTF-8");
        String BODY = String.join(
                System.getProperty("line.separator"),
                emailzaglavlje
                + emailsadrzajContainer
                + standardnaporukazafirmu(nacinisporuke, user.getIme(), user.getPrezime())
                + ""
                + podaciOKupcu(user, zavrsenePorudzbine)
                + ""
                + listaproizvoda(korpa)
                + "           "
                + cenaSumaproizvodasegment(korpa)
                + emailsadrzajContainerClose
                + emailfooter
        );

        msg.setContent(BODY, "text/html;charset=utf-8");
        // Add a configuration set header. Comment or delete the
        // next line if you are not using a configuration set
        msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);
        // Create a transport.
        Transport transport = session.getTransport();
        // Send the message.
        try {
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (Exception ex) {
            String err = ex.getMessage();
        } finally {
            // Close and terminate the connection.
            transport.close();
        }
    }

    public static String listaproizvoda(Korpa korpa) {
        String poruceno = "            <tr style=\"background:#ffffff\">"
                + "              <td style=\"padding: 30px 30px 30px 12px\">"
                + "                <table style=\"background:#fff\" width=\"100%\">"
                + "                  <tbody>";

        //   String jedanred="";
        for (KorpaProizvodi korpaproizvod : korpa.getKorpaproizvodi()) {
            String boja = "Osnovna";
            if (korpaproizvod.getBoja() != null) {
                boja = korpaproizvod.getBoja().getAlt_text();
            }

            poruceno += "                    <tr>"
                    + "                      <td style=\"padding:0;text-align:center\" width=\"20%\" valign=\"top\">"
                    + "                        <img style=\"background:transparent; object-fit:cover;\" src=\"https://" + serverip + "/photo/" + korpaproizvod.getProizvod().getId() + "/" + korpaproizvod.getProizvod().getGlavnaslika().getId() + "\" width=\"100\" height=\"100\" border=\"0\" >"
                    + "                      </td>"
                    + "                      <td style=\"color:#000000;font-size:14px;float:left\" width=\"100%\" valign=\"top\">"
                    + "                        <table style=\"border-top:1px solid #892f2b;border-bottom:1px solid #892f2b;padding:26px 0; margin-bottom:20px\" width=\"100%\">"
                    + "                          <tbody>"
                    + "                            <tr>"
                    + "                              <td style=\"padding:5px;text-align:left\" width=\"60%\">"
                    + "                                <p style=\"margin:0;padding:0;font-size:14px;color:#000000;font-weight:400\">" + korpaproizvod.getProizvod().getIme() + "</p>"
                    + ""
                    + "                              </td>"
                    + ""
                    + "                              <td style=\"text-align:center\" width=\"20%\">"
                    + "                                <center>"
                    + "                                  <p style=\"margin:0;padding:0;text-align:center;font-size:14px;color:#000000;font-weight:400\">Boja/dezen: <strong>" + boja + " </strong>"
                    + "                                  </p>"
                    + "                                </center>"
                    + "                              </td>"
                    + "                              <td style=\"text-align:center\" width=\"20%\">"
                    + "                                <center>"
                    + "                                  <p style=\"margin:0;padding:0;text-align:center;font-size:14px;color:#000000;font-weight:400\">Količina: <br> <strong>" + korpaproizvod.getKolicina() + " </strong>"
                    + "                                  </p>"
                    + "                                </center>"
                    + "                              </td>"
                    + "                              <td style=\"text-align:center\" width=\"20%\">"
                    + "                                <center>"
                    + "                                  <p style=\"margin:0;padding:0;text-align:center;font-size:14px;color:#000000;font-weight:400\"><strong>" + (korpaproizvod.getProizvod().getCena() * korpaproizvod.getKolicina()) + " RSD</strong></p>"
                    + "                                </center>"
                    + "                              </td>"
                    + "                            </tr>"
                    + "                          </tbody>"
                    + "                        </table>"
                    + "                      </td>"
                    + "                      "
                    + "                    </tr>";

        }
        poruceno += "                  </tbody>"
                + "                </table>"
                + "              </td>"
                + "            </tr>"
                + "            ";
        return poruceno;
    }

    public static double cenaSumaproizvoda(Korpa korpa) {
        double suma = 0;
        for (KorpaProizvodi korpaproizvod : korpa.getKorpaproizvodi()) {
            suma += korpaproizvod.getKolicina() * korpaproizvod.getProizvod().getCena();
        }
        return suma;
    }

    public static String cenaSumaproizvodasegment(Korpa korpa) {

        return "            <tr>"
                + "              <td style=\"padding:30px;background-color:#fff\" valign=\"top\">"
                + "                <table style=\"border-top:2px solid #892f2b\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">"
                + "                  <tbody>"
                + "                    <tr>"
                + "                      <td style=\"text-align:left;width:50%;padding:10px\">"
                + ""
                + "                        <p style=\"margin:0;padding:0;color:#000000;font-size:20px\">Ukupna vrednost porudžbine: <br>"
                + "                          <span style=\"font-size:12px\">*PDV je uračunat u cenu.</span>"
                + "                        </p>"
                + "                      </td>"
                + "                      <td style=\"text-align:right;width:50%;padding-right:10px\">"
                + "                        <p style=\"margin:0;padding:0;font-size:20px;color:#000000;font-weight:600\">"
                + ""
                + "                   " + cenaSumaproizvoda(korpa) + " RSD<br>"
                + ""
                + "                        </p>"
                + "                      </td>"
                + "                    </tr>"
                + "                  </tbody>"
                + ""
                + "                </table>"
                + "              </td>"
                + "            </tr>";
    }

    public static String uplatnicaSlika() {

        String uplatnicasegment = "            <tr>"
                + "              <td style=\"padding:30px;background-color:#fff\" valign=\"top\">"
                + "                <table style=\"border-top:2px solid #892f2b\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">"
                + "                  <tbody>"
                + "                    <tr>"
                + "                      <p>Uplatnicu možete pronaći u attachmentu ovog emaila.</p>"
                + "                    </tr>"
                + "                  </tbody>"
                + ""
                + "                </table>"
                + "              </td>"
                + "            </tr>";

        return uplatnicasegment;
    }

    public static String napravljenNalog(String ime, String prezime, String email) {
        return " <tbody> "
                + "            <tr> "
                + "              <td style=\"background:#ffffff;padding:30px\"> "
                + "                <p style=\"margin:0;padding:0;text-align:left;margin-top:10px;margin-bottom:20px;font-size:24px;color:#000000;font-weight:400;font-weight:600\"> "
                + "                 Dobrodošli na E-Mobilnost "
                + "               </p> "
                + "               <p style=\"margin:0;padding:0;text-align:left;margin-top:10px;font-size:18px;color:#000000;font-weight:400\"> "
                + "                 Zdravo " + ime + " " + prezime + ","
                + "               </p> "
                + "               <p style=\"margin:0;padding:0;text-align:left;margin-top:10px;font-size:18px;color:#000000;\"> "
                + "                 Hvala što ste učlanili na E-Mobilnost. Vaše korisničko ime je <span style=\"font-weight:normal\"> " + email + "</span>. Svom nalogu možete pristupiti da pogledate status članstva, promenite lozinku i u druge svrhe, na adresi: <a href=\"https://" + serverip + "/registracija/\" rel=\"nofollow\" style=\"color:#3a4da1;font-weight:normal;text-decoration:underline\" target=\"_blank\" data-saferedirecturl=\"\">https://www.emobilnost.rs/<wbr>moj-nalog/</a>  "
                + "                 <br> <br> "
                + "                 Nadamo se da ćemo vas uskoro videti. "
                + "                </p> "
                + "            </td> "
                + "           </tr> "
                + "          </tbody> ";
    }

    public static String napravljenNalogAdmin(Clanovi clan) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String telo = " <tbody> "
                + "            <tr> "
                + "              <td style=\"background:#ffffff;padding:30px\"> "
                + "                <p style=\"margin:0;padding:0;text-align:left;margin-top:10px;margin-bottom:20px;font-size:24px;color:#000000;font-weight:400;font-weight:600\"> "
                + "               <p style=\"margin:0;padding:0;text-align:left;margin-top:10px;font-size:18px;color:#000000;font-weight:400\"> "
                + "                 Zdravo Nebojša, <br>  "
                + "                 Novi član je pristupio udruženju E-Mobilnost."
                + "               </p> "
                + "               <p style=\"margin:0;padding:0;text-align:left;margin-top:10px;font-size:18px;color:#000000;\"> ";
        if (clan.getIs_pravno_lice()) {
            telo += "Novi član je pravno lice.<br>"
                    + "Naziv pravnog lica: " + clan.getNaziv_pravne_osobe();
        }

        telo += " Ime i prezime: " + clan.getIme() + " " + clan.getPrezime() + "," + " <br>  "
                + " Email adresa: " + clan.getEmail() + " <br>  "
                + " Adresa: " + clan.getAdresa() + " <br>  "
                + " Poštanski broj i mesto: " + clan.getPostanski_broj() + " " + clan.getMesto() + " <br>  "
                + " Država: " + clan.getDrzava() + " <br>  "
                + " Telefon: " + clan.getBroj_telefona() + " <br>  "
                + " JMBG: " + clan.getJmbg() + " <br>  "
                + " PIB: " + clan.getPib() + " <br>  "
                //  + " Datum registracije:" + clan.getDatum_pocetka_clanstva() + " <br>  "
                + " Datum registracije: " + formatter.format(date) + " <br>  "
                //  + " " + formatter.format(date) + ""

                + "                </p> "
                + "            </td> "
                + "           </tr> "
                + "          </tbody> ";

        return telo;
    }

    public static String sendEmailKomentar(Komentari komentar, String ime) {
        Vesti vest = komentar.getVest();
        String naslovVesti = vest.getNaslov();
        String autorKomentara = komentar.getIme();
        String tekstKomentara = komentar.getTekst();
        return " <tbody> "
                + "            <tr> "
                + "              <td style=\"background:#ffffff;padding:30px;line-height: 1.5;\"> "
                + "               <p style=\"margin:0;padding:0;text-align:left;margin-top:10px;font-size:18px;color:#000000;\"> "
                + "                Stigao je komentar od " + autorKomentara + " za vest " + naslovVesti + ". <br> Komentar glasi: " + tekstKomentara + ". <br> Kliknite na dugme 'Odobri' ako želite da ga odobrite.  "
               + "                 <table width=\"180\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin-bottom:30px;margin-left:30px;text-align: center;    margin-top: 1.2rem;display: inline-table;>"
                + "                 <tbody>"
                + "                   <tr>"
                + "                     <td bgcolor=\"#f16922\" height=\"50\" align=\"center\" valign=\"middle\" style=\"font-family:Gotham,Arial,sans-serif;font-size:16px;background-color:#8cc63f;color:#ffffff;border-radius:3px\">"
                + "                       <div id=\"m_-1272894485461758630button\"><a href=\"" + serverip + "/admin/odobrenKomentar/" + komentar.getId() + "\" style=\"text-decoration:none;color:#ffffff;display:block;line-height:49px;letter-spacing:.05rem\" target=\"_blank\" data-saferedirecturl=\"\">Odobri komentar</a></div>"
                + "                     </td>"
                + "                   </tr>"
                + "                 </tbody>"
                + "                 </table>"
                + "                </p> "
                + "            </td> "
                + "           </tr> "
                + "          </tbody> ";
    }

    public static String sendEmailPorukaPoslata(String email, String ime) {
        return "<tr>"
                + "  <td style=\"background:#ffffff;padding:30px\">"
                + "    <p style=\"margin:0;padding:0;text-align:left;margin-top:10px;margin-bottom:20px;font-size:24px;color:#000000;font-weight:400;color:#892F2B;font-weight:600\">"
                + "      Dobrodošli na E-Mobilnost"
                + "    </p>"
                + "   <p style=\"margin:0;padding:0;text-align:left;margin-top:10px;font-size:18px;color:#000000;font-weight:400\">"
                + "      Zdravo " + ime + ","
                + "    </p>"
                + "    <br>"
                + "    <p style=\"margin:0;padding:0;text-align:left;margin-top:10px;font-size:18px;color:#000000;line-height:1.4\">"
                + "      Hvala što ste poslali poruku na E-Mobilnost. Kontaktiraćemo Vas u najkraćem mogućem roku. "
                + "    </p>"
                + "  </td>"
                + "  </tr>";
    }

    public static String sendEmailPoruka(String ime, String email, String telefon, String poruka) {
        return "<tr>"
                + "<td style=\"background:#ffffff;padding:30px\">"
                + "  <p style=\"margin:0;padding:0;text-align:left;margin-top:10px;margin-bottom:20px;font-size:24px;color:#000000;font-weight:400;font-weight:600\">"
                + "   Primljena je poruka"
                + "  </p>"
                + "  <p style=\"margin:0;padding:0;text-align:left;margin-top:10px;font-size:18px;color:#000000;font-weight:400\">"
                + "   Primljena je poruka od korisnika: " + ime + ","
                + "  </p>"
                + "  <br>"
                + "   <p style=\"margin:0;padding:0;text-align:left;margin-top:10px;font-size:18px;color:#000000;line-height:1.4\">"
                + "    <span style=\"font-style: italic\">Email adresa:</span> " + telefon + "<br>"
                + "    <span style=\"font-style: italic\">Telefon:</span> " + email + "<br><br>"
                + "     sa sadržajem: <br>"
                + "   </p>"
                + "   <p style=\"margin:0;padding:0;text-align:left;margin-top:10px;font-size:18px;color:#000000;line-height:1.4\">"
                + "    " + poruka + " "
                + "   </p>"
                + "  </td>"
                + " </tr>";
    }

    public static String resetujLozinku(String ime, String prezime, String resetToken) {
        return "             <tr>"
                + "             <td style=\"background:#ffffff;padding:30px\">"
                + "               <p style=\"margin:0;padding:0;text-align:left;margin-top:10px;font-size:18px;color:#000000;font-weight:400\">"
                + "                  Zdravo " + ime + " " + prezime + ","
                + "               </p>"
                + "               <br>"
                + "               <p style=\"margin:0;padding:0;text-align:left;margin-top:10px;font-size:18px;color:#000000;\">"
                + "                 Zaboravili ste lozinku? Kliknite na link ispod kako biste resetovali lozinku.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "               </p>"
                + "           <tr>"
                + "             <td valign=\"top\">"
                + "               <table width=\"180\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin-bottom:30px; margin-left:30px\">"
                + "                 <tbody>"
                + "                   <tr>"
                + "                     <td bgcolor=\"#f16922\" height=\"50\" align=\"center\" valign=\"middle\" style=\"font-family:Gotham,Arial,sans-serif;font-size:16px;background-color:#8cc63f;color:#ffffff;border-radius:3px\">"
                + "                       <div id=\"m_-1272894485461758630button\"><a href=\"https://" + serverip + "/reset-lozinke/" + resetToken + "\" style=\"text-decoration:none;color:#ffffff;display:block;line-height:49px;letter-spacing:.05rem\" target=\"_blank\" data-saferedirecturl=\"\">Resetuj lozinku</a></div>"
                + "                     </td>"
                + "                   </tr>"
                + "                 </tbody>"
                + "               </table>"
                + "             </td>"
                + "           </tr>"
                + "           <tr>"
                + "             <td valign=\"top\" style=\"font-family:'Helvetica Neue',Helvetica,Arial,sans-serif;font-size:11px;line-height:28px;color:#363636\">"
                + "               <div id=\"m_-1272894485461758630training\" style=\"margin-left: 30px;\">*Ako niste podneli ovaj zahtev ili ste ga podneli greškom, zanemarite ovaj email. Vaša lozinka će ostati takva kakva je bila.</div>"
                + "             </td>"
                + "           </tr>"
                + "           <tr>"
                + "             <td height=\"30\"> </td>"
                + "           </tr>"
                + "     </td>"
                + "   </tr>";
    }

    public static String standardnaporuka(String ime, String prezime, String nacinisporuke) {
        return "            <tr>"
                + "              <td style=\"background:#ffffff;padding:30px\">"
                + ""
                + "                <p style=\"margin:0;padding:0;text-align:left;margin-top:10px;font-size:18px;color:#000000;font-weight:400\">"
                + "                  Poštovani/a  " + ime + " " + prezime + ","
                + "                </p>"
                + "                <p style=\"margin:0;padding:0;text-align:left;margin-top:10px;font-size:18px;color:#000000;font-weight:600\">"
                + "                  Vaša porudžbina je uspešno primljena."
                + "                </p>"
                + ""
                + "                <p style=\"margin:0;padding:0;text-align:left;margin-top:20px;font-size:16px;color:#000000;font-weight:400;line-height:26px\">"
                + "                  Preuzimanje porudžbine: " + nacinisporuke + "  "
                + "                  "
                + ""
                + "                </p>"
                + ""
                + "                <p style=\"margin:0;padding:0;text-align:left;margin-top:20px;font-size:16px;color:#000000;font-weight:400;line-height:26px\">"
                + "                  <strong>Važno:</strong>"
                + "                  Isporuka kupljenih proizvoda se obavlja u saradnji sa kurirskim službama,"
                + "                  Margotekstil čini sve što je u njenoj moći kako bi proizvodi koje ste kupili"
                + "                  što pre došli do Vas. Ukoliko se roba isporučuje na adresu, molimo Vas da"
                + "                  robu pri prijemu od strane kurirske službe pregledate na licu mesta i sve uočene nepravilnosti prijavite u roku od 24 sata."
                + "                  <br>"
                + "                  <br>"
                + "                  Molimo Vas za strpljenje i zahvaljujemo se na ukazanom poverenju i razumevanju."
                + "                </p>"
                + ""
                + "              </td>"
                + "            </tr>";
    }

    public static String standardnaporukazafirmu(String nacinisporuke, String ime, String prezime) {
        return "            <tr>"
                + "              <td style=\"background:#ffffff;padding: 30px 30px 0 30px;\">"
                + ""
                + "                <p style=\"margin:0;padding:0;text-align:left;margin-top:10px;font-size:18px;color:#000000;font-weight:600\">\n"
                + "                  Porudžbina kupca " + ime + " " + prezime + " je uspešno primljena.\n"
                + "                </p>"
                + "                <p style=\"margin:0;padding:0;text-align:left;margin-top:20px;font-size:16px;color:#000000;font-weight:400;line-height:26px\">"
                + "                  Preuzimanje porudžbine: " + nacinisporuke + "  "
                + "                  "
                + ""
                + "                </p>"
                + ""
                + "              </td>"
                + "            </tr>";
    }

    public static String podaciokupcuiprodavcu(Users user, String komentar, ZavrsenePorudzbine zavrsenePorudzbine) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String ime = user.getIme();
        String prezime = user.getPrezime();
        String telefon = user.getBroj_telefona();
        String email = user.getEmail();
        String adresa = user.getAdresa();
        String postanskibroj = user.getPostanski_broj();
        String grad = user.getMesto();

        return "            <tr>"
                + "              <td style=\"padding:20px\">"
                + "                <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">"
                + "                  <tbody>"
                + "                    <tr>"
                + "                      <td style=\"padding:10px;margin:0;\" width=\"50%\">"
                + "                        <p style=\"margin:0;padding:0;text-align:left;font-size:13px;color:#892f2b;font-weight:400\">"
                + "                          <b>Datum naručivanja</b>"
                + "                        </p>"
                + "                        <p style=\"margin:0;padding:0;text-align:left;font-size:14px;color:#000000;font-weight:400\">"
                + "                         " + formatter.format(date) + ""
                + "                        </p>"
                + "                        <p style=\"margin: 15px 0 0 0;padding:0;text-align:left;font-size:13px;color:#892f2b;font-weight:400\">"
                + "                          <b>Referenca porudžbine</b>"
                + "                        </p>"
                + "                        <p style=\"margin:0;padding:0;text-align:left;font-size:14px;color:#000000;font-weight:400\">"
                + "                          " + "210" + zavrsenePorudzbine.getKorpa().getId() + "</a>"
                + "                        </p>"
                + "                        <p style=\"margin: 15px 0 0 0;padding:0;text-align:left;font-size:13px;color:#892f2b;font-weight:400\">"
                + "                          <b>Podaci o kupcu</b>"
                + "                        </p>"
                + "                        <p style=\"margin:0;padding:0;text-align:left;font-size:14px;color:#000000;font-weight:400\">"
                + "                     <span>     " + ime + " " + prezime + "</span> <br>"
                + "                     <span>     " + adresa + ", " + postanskibroj + " " + grad + "</span> "
                + "<br>"
                + "                          <span>  " + telefon + " </span><br>"
                + "                          <a href=\"mailto:" + email + "\" target=\"_blank\">" + email + "</a> <br>"
                + "                           <span style=\"font-style: italic\">Napomena: </span> " + komentar + " "
                + "                        </p>"
                + "                      </td>"
                + "                      <td style=\"padding: 0 10px 25px 10px;\" width=\"50%\">"
                + "                        <p style=\"margin-top:0;padding:0;text-align:left;font-size:13px;color:#892f2b;font-weight:400\">"
                + "                          <b>Podaci o prodavcu</b>"
                + "                        </p>"
                + "                        <p style=\"margin:0;padding:0;text-align:left;font-size:14px;color:#000000;font-weight:400\">"
                + "                          Margo Tekstil d.o.o <br>"
                + "                          Časlava Veljića 53, 11000 Beograd <br>"
                + "                          0164/44-14-000<br>"
                + "                          <a href=\"mailto:margoteks.doo@gmail.com\" target=\"_blank\">margoteks.doo@gmail.com</a><br>"
                + "                        </p>"
                + "                        <p style=\"margin-top:20px;text-align:left;font-size:12px;color:#000000;font-weight:400;box-shadow: 1px 2px 11px 5px rgba(112,112,112,0.2);\">"
                + "                          PIB: <strong>102778428</strong> <br>"
                + "                          BR. RAČUNA: <strong>325-9500700034631-92</strong> <br>"
                + "                          MATIČNI BROJ: <strong>21608491</strong>"
                + "                        </p>"
                + "                      </td>"
                + "                    </tr>"
                + "                  </tbody>"
                + "                </table>"
                + "              </td>"
                + "            </tr>";
    }

    public static String podaciOKupcu(Users user, ZavrsenePorudzbine zavrsenePorudzbine) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String ime = user.getIme();
        String prezime = user.getPrezime();
        String telefon = user.getBroj_telefona();
        String email = user.getEmail();
        String adresa = user.getAdresa();
        String postanskibroj = user.getPostanski_broj();
        String grad = user.getMesto();
        String komentar = zavrsenePorudzbine.getNapomena();
        return "            <tr>"
                + "              <td style=\"padding:20px\">"
                + "                <table style=\"padding:10px\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">"
                + "                  <tbody>"
                + "                    <tr>"
                + "                      <td style=\"margin:0;\" width=\"50%\">"
                + "                        <p style=\"margin:0;padding:0;text-align:left;font-size:15px;color:#892f2b;font-weight:400\">"
                + "                          <b>Datum naručivanja</b>"
                + "                        </p>"
                + "                        <p style=\"margin:0;padding:0;text-align:left;font-size:14px;color:#000000;font-weight:400\">"
                + "                         " + formatter.format(date) + ""
                + "                        </p>"
                + "                        <p style=\"margin: 15px 0 0 0;padding:0;text-align:left;font-size:15px;color:#892f2b;font-weight:400\">"
                + "                          <b>Referenca porudžbine</b>"
                + "                        </p>"
                + "                        <p style=\"margin:0;padding:0;text-align:left;font-size:14px;color:#000000;font-weight:400\">"
                + "                         <a href=\"https://" + serverip + "/admin/adminPregledPorudzbine/" + zavrsenePorudzbine.getId() + "\">" + zavrsenePorudzbine.getId() + "</a>"
                + "                        </p>"
                + "                        <p style=\"margin: 15px 0 0 0;padding:0;text-align:left;font-size:15px;color:#892f2b;font-weight:400\">"
                + "                          <b>Podaci o kupcu</b>"
                + "                        </p>"
                + "                        <p style=\"margin:0;padding:0;text-align:left;font-size:14px;color:#000000;font-weight:400\">"
                + "                     <span>     " + ime + " " + prezime + "</span> <br>"
                + "                     <span>     " + adresa + ", " + postanskibroj + " " + grad + "</span> "
                + "<br>"
                + "    <span>                      " + telefon + " </span><br>"
                + "                          <a href=\"mailto:" + email + "\" target=\"_blank\">" + email + "</a> <br>"
                + "                           <span style=\"font-style: italic\">Napomena: </span> " + komentar + " "
                + "                        </p>"
                + "                      </td>"
                + "                    </tr>"
                + "                  </tbody>"
                + "                </table>"
                + "              </td>"
                + "            </tr>";
    }

    //kada ulogovani korisnik naruci proizvode, pa mu na email stigne "izvestaj" o tome - uplata na tekuci racun/uplatnica
    public static String emailzaglavlje = "<table style=\"border-spacing:0!important;border-collapse:collapse!important;table-layout:fixed!important;\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"750\">"
            + "  <tbody>"
            + "    <tr>"
            + "      <td style=\"background-color:#000000\" valign=\"top\">"
            + "        <table style=\"border-spacing:0!important;border-collapse:collapse!important;table-layout:fixed!important;margin:0 auto!important\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">"
            + "          <tbody>"
            + "            <tr>"
            + "              <td style=\"background-color:#000000;padding:15px;text-align:center!important;float:none!important;display:block!important;margin-left:auto!important;margin-right:auto!important\" align=\"center\" valign=\"middle\">"
            + ""
            + "     <a style=\"text-decoration:none\" href=\"https://emobilnost.rs/\" target=\"_blank\" data-saferedirecturl=\"https://emobilnost.rs/\">"
            + " <img style=\"height:auto;background:transparent\" src=\"https://i.imgur.com/4OgunKr.png\" width=\"203\" height=\"43\" alt=\"alt_text\" border=\"0\" class=\"CToWUd\">"
            + "        </a>"
            + "              </td>"
            + "            </tr>"
            + "          </tbody>"
            + "        </table>"
            + "      </td>"
            + "    </tr>"
            + "  </tbody>"
            + "</table>";

    public static String emailfooter = "<table style=\"border-spacing:0!important;border-collapse:collapse!important;table-layout:fixed!important;\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"750\">"
            + "  <tbody>"
            + "    <tr>"
            + "      <td valign=\"top\">"
            + "        <table style=\"border-spacing:0!important;border-collapse:collapse!important;table-layout:fixed!important;margin:0 auto!important\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">"
            + "          <tbody>"
            + "            <tr>"
            + "              <td style=\"padding:10px;background-color:#000000;border-bottom:1px solid white;text-align:left\" dir=\"ltr\" valign=\"top\" width=\"100%\">"
            + "                <table style=\"table-layout:auto;border-spacing:0!important;border-collapse:collapse!important;table-layout:fixed!important;margin:0 auto!important\" width=\"100%\">"
            + "                  <tbody>"
            + "                    <tr>"
            + "                      <td style=\"color:white\" width=\"50%\">"
            + "                        <p style=\"font-size:12px;letter-spacing:1px;font-weight:200;margin:0;padding:5px;float:left;font-family:Roboto, sans-serif;color:white\">"
            + "                          Kontakt telefon: <span style=\"margin:0;padding:0;color:#8cc63f;font-weight:400;font-family:Roboto, sans-serif;font-size:14px\"> +381 63 76 38 333</span>"
            + "                        </p>"
            + ""
            + "                      </td>"
            + ""
            + "                      <td style=\"color:white\" width=\"50%\">"
            + "                        <table style=\"table-layout:auto;border-spacing:0!important;border-collapse:collapse!important;table-layout:fixed!important;margin:0 auto!important;text-align:right\" width=\"100%\">"
            + "                          <tbody>"
            + "                            <tr>"
            + "                              <td width=\"70%\">"
            + "                                <a style=\"text-decoration:none\" href=\"https://www.facebook.com/emobilnost/\" target=\"_blank\" data-saferedirecturl=\"https://www.facebook.com/emobilnost/\">"
            + "                                  <img src=\"https://i.imgur.com/MN7BORL.png\" width=\"20\" height=\"20\" alt=\"Facebook\" border=\"0\" >"
            + "                                </a>"
            + "                              </td>"
            + "                              <td width=\"8%\">"
            + "                                <a style=\"text-decoration:none\" href=\"https://www.instagram.com/emobilnost.rs/\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.instagram.com/emobilnost.rs/&source=gmail&ust=1617998358037000&usg=AFQjCNF8rA5BAGs6Ib6GIaNs0khZHfgs1A\">"
            + "                                  <img src=\"https://i.imgur.com/e75MFvR.png\" width=\"20\" height=\"20\" alt=\"Instagram\" border=\"0\" >"
            + "                                </a>"
            + "                              </td>"
            + ""
            + "                            </tr>"
            + "                          </tbody>"
            + "                        </table>"
            + "                      </td>"
            + "                    </tr>"
            + "                  </tbody>"
            + "                </table>"
            + "              </td>"
            + "            </tr>"
            + "          </tbody>"
            + "        </table>"
            + "      </td>"
            + "    </tr>"
            + "    <tr>"
            + "      <td valign=\"top\">"
            + "        <table style=\"border-spacing:0!important;border-collapse:collapse!important;table-layout:fixed!important;margin:0 auto!important\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">"
            + "          <tbody>"
            + "            <tr>"
            + "              <td style=\"background-color:#000000;text-align:left\" dir=\"ltr\" valign=\"top\" width=\"100%\">"
            + "                <table style=\"table-layout:auto;border-spacing:0!important;border-collapse:collapse!important;table-layout:fixed!important;font-family:Roboto, sans-serif;margin:0 auto!important\" width=\"100%\">"
            + "                  <tbody>"
            + "                    <tr>"
            + "                      <td style=\"color:white;font-weight:200\">"
            + "                        <h2 style=\"margin:0;padding:0;color:#ffffff;text-align:center;line-height:50px;font-size:14px;font-weight:200\">2021 E-Mobilnost"
            + "                        </h2>"
            + "                      </td>"
            + "                    </tr>"
            + "                  </tbody>"
            + "                </table>"
            + "              </td>"
            + "            </tr>"
            + "          </tbody>"
            + "        </table>"
            + "      </td>"
            + "    </tr>"
            + "  </tbody>"
            + "</table>";

    public static String emailsadrzajContainer = "<table style=\"border-spacing:0!important;border-collapse:collapse!important;font-family:Roboto, sans-serif;table-layout:fixed!important\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"750\">"
            + "  <tbody>"
            + "    <tr style=\"background:#f0f0f0\">"
            + ""
            + "      <td style=\"padding:30px;font-size:15px;line-height:20px;color:#555555\">"
            + "        <table style=\"background:#ffffff;width:690px;\">"
            + "          <tbody>";
    public static String emailsadrzajContainerClose = "          </tbody>"
            + "        </table>"
            + "      </td>"
            + "    </tr>"
            + "  </tbody>"
            + "</table>";
}
