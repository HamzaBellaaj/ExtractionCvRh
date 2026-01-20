package com.CvExt.CvExtIsetKeyrus;

import com.CvExt.CvExtIsetKeyrus.Repository.*;
import com.CvExt.CvExtIsetKeyrus.Service.*;
import com.CvExt.CvExtIsetKeyrus.entities.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ServiceIntegrationTests {

    @Autowired
    private CVService cvService;

    @Autowired
    private CompetenceService competenceService;

    @Autowired
    private ExperienceService experienceService;

    @Autowired
    private FormationService formationService;

    @Autowired
    private LangueService langueService;

    @Autowired
    private AnalyseService analyseService;

    private static Integer testCvId;

    // =============== TESTS CV ===============
    @Test
    @Order(1)
    void testCreerCV() {
        CV cv = new CV("Dupont", "Jean", "jean.dupont@email.com", "0612345678", 
                       "Paris, France", "Développeur Java", "Développeur passionné avec 5 ans d'expérience",
                       5, "Senior", "Informatique");
        
        CV cvCree = cvService.creer(cv);
        
        assertNotNull(cvCree);
        assertNotNull(cvCree.getIdCv());
        assertEquals("Dupont", cvCree.getNom());
        assertEquals("Jean", cvCree.getPrenom());
        
        testCvId = cvCree.getIdCv();
        System.out.println("✅ CV créé avec ID: " + testCvId);
    }

    @Test
    @Order(2)
    void testLireCV() {
        CV cv = cvService.lire(testCvId);
        
        assertNotNull(cv);
        assertEquals("Dupont", cv.getNom());
        System.out.println("✅ CV lu: " + cv.getNom() + " " + cv.getPrenom());
    }

    @Test
    @Order(3)
    void testRechercherTousLesCVs() {
        List<CV> cvs = cvService.rechercher();
        
        assertNotNull(cvs);
        assertFalse(cvs.isEmpty());
        System.out.println("✅ Nombre de CVs trouvés: " + cvs.size());
    }

    // =============== TESTS COMPETENCE ===============
    @Test
    @Order(4)
    void testCreerCompetence() {
        Competence competence = new Competence("Java", "Technique", "Expert", testCvId);
        
        Competence compCree = competenceService.creer(competence);
        
        assertNotNull(compCree);
        assertNotNull(compCree.getIdCompetence());
        assertEquals("Java", compCree.getNomCompetence());
        assertEquals(testCvId, compCree.getIdCv());
        System.out.println("✅ Compétence créée: " + compCree.getNomCompetence());
    }

    @Test
    @Order(5)
    void testRechercherCompetencesParCV() {
        List<Competence> competences = competenceService.rechercherParCV(testCvId);
        
        assertNotNull(competences);
        assertFalse(competences.isEmpty());
        System.out.println("✅ Compétences trouvées pour CV " + testCvId + ": " + competences.size());
    }

    // =============== TESTS EXPERIENCE ===============
    @Test
    @Order(6)
    void testCreerExperience() {
        Experience experience = new Experience("Développeur Senior", "TechCorp", 
                                               LocalDate.of(2020, 1, 1), LocalDate.of(2023, 12, 31),
                                               "Développement d'applications web", testCvId);
        
        Experience expCree = experienceService.creer(experience);
        
        assertNotNull(expCree);
        assertNotNull(expCree.getIdExperience());
        assertEquals("Développeur Senior", expCree.getPoste());
        assertEquals(testCvId, expCree.getIdCv());
        System.out.println("✅ Expérience créée: " + expCree.getPoste() + " chez " + expCree.getEntreprise());
    }

    @Test
    @Order(7)
    void testRechercherExperiencesParCV() {
        List<Experience> experiences = experienceService.rechercherParCV(testCvId);
        
        assertNotNull(experiences);
        assertFalse(experiences.isEmpty());
        System.out.println("✅ Expériences trouvées pour CV " + testCvId + ": " + experiences.size());
    }

    // =============== TESTS FORMATION ===============
    @Test
    @Order(8)
    void testCreerFormation() {
        Formation formation = new Formation("Master Informatique", "Université Paris", 
                                           LocalDate.of(2015, 9, 1), LocalDate.of(2017, 6, 30), testCvId);
        
        Formation formCree = formationService.creer(formation);
        
        assertNotNull(formCree);
        assertNotNull(formCree.getIdFormation());
        assertEquals("Master Informatique", formCree.getDiplome());
        assertEquals(testCvId, formCree.getIdCv());
        System.out.println("✅ Formation créée: " + formCree.getDiplome());
    }

    @Test
    @Order(9)
    void testRechercherFormationsParCV() {
        List<Formation> formations = formationService.rechercherParCV(testCvId);
        
        assertNotNull(formations);
        assertFalse(formations.isEmpty());
        System.out.println("✅ Formations trouvées pour CV " + testCvId + ": " + formations.size());
    }

    // =============== TESTS LANGUE ===============
    @Test
    @Order(10)
    void testCreerLangue() {
        Langue langue = new Langue("Anglais", "Courant", testCvId);
        
        Langue langueCree = langueService.creer(langue);
        
        assertNotNull(langueCree);
        assertNotNull(langueCree.getIdLangue());
        assertEquals("Anglais", langueCree.getNomLangue());
        assertEquals(testCvId, langueCree.getIdCv());
        System.out.println("✅ Langue créée: " + langueCree.getNomLangue());
    }

    @Test
    @Order(11)
    void testRechercherLanguesParCV() {
        List<Langue> langues = langueService.rechercherParCV(testCvId);
        
        assertNotNull(langues);
        assertFalse(langues.isEmpty());
        System.out.println("✅ Langues trouvées pour CV " + testCvId + ": " + langues.size());
    }

    // =============== TESTS ANALYSE ===============
    @Test
    @Order(12)
    void testCreerAnalyse() {
        Analyse analyse = new Analyse("Java, Spring, SQL", 85.5, 90.0, 80.0, testCvId);
        
        Analyse analyseCree = analyseService.creer(analyse);
        
        assertNotNull(analyseCree);
        assertNotNull(analyseCree.getIdAnalyse());
        assertEquals(85.5, analyseCree.getScoreGlobal());
        assertEquals(testCvId, analyseCree.getIdCv());
        System.out.println("✅ Analyse créée avec score global: " + analyseCree.getScoreGlobal());
    }

    @Test
    @Order(13)
    void testRechercherAnalysesParCV() {
        List<Analyse> analyses = analyseService.rechercherParCV(testCvId);
        
        assertNotNull(analyses);
        assertFalse(analyses.isEmpty());
        System.out.println("✅ Analyses trouvées pour CV " + testCvId + ": " + analyses.size());
    }

    // =============== TESTS MISE A JOUR ===============
    @Test
    @Order(14)
    void testMettreAJourCV() {
        CV cv = cvService.lire(testCvId);
        cv.setNom("Dupont-Martin");
        cv.setAnneesExperience(6);
        
        CV cvMaj = cvService.mettre_a_jour(cv);
        
        assertNotNull(cvMaj);
        assertEquals("Dupont-Martin", cvMaj.getNom());
        assertEquals(6, cvMaj.getAnneesExperience());
        System.out.println("✅ CV mis à jour: " + cvMaj.getNom());
    }

    // =============== TESTS SUPPRESSION ===============
    @Test
    @Order(15)
    void testSupprimerCompetence() {
        List<Competence> competences = competenceService.rechercherParCV(testCvId);
        if (!competences.isEmpty()) {
            Integer idComp = competences.get(0).getIdCompetence();
            boolean supprime = competenceService.supprimer(idComp);
            assertTrue(supprime);
            System.out.println("✅ Compétence supprimée: " + idComp);
        }
    }

    @Test
    @Order(16)
    void testSupprimerExperience() {
        List<Experience> experiences = experienceService.rechercherParCV(testCvId);
        if (!experiences.isEmpty()) {
            Integer idExp = experiences.get(0).getIdExperience();
            boolean supprime = experienceService.supprimer(idExp);
            assertTrue(supprime);
            System.out.println("✅ Expérience supprimée: " + idExp);
        }
    }

    @Test
    @Order(17)
    void testSupprimerFormation() {
        List<Formation> formations = formationService.rechercherParCV(testCvId);
        if (!formations.isEmpty()) {
            Integer idForm = formations.get(0).getIdFormation();
            boolean supprime = formationService.supprimer(idForm);
            assertTrue(supprime);
            System.out.println("✅ Formation supprimée: " + idForm);
        }
    }

    @Test
    @Order(18)
    void testSupprimerLangue() {
        List<Langue> langues = langueService.rechercherParCV(testCvId);
        if (!langues.isEmpty()) {
            Integer idLang = langues.get(0).getIdLangue();
            boolean supprime = langueService.supprimer(idLang);
            assertTrue(supprime);
            System.out.println("✅ Langue supprimée: " + idLang);
        }
    }

    @Test
    @Order(19)
    void testSupprimerAnalyse() {
        List<Analyse> analyses = analyseService.rechercherParCV(testCvId);
        if (!analyses.isEmpty()) {
            Integer idAnalyse = analyses.get(0).getIdAnalyse();
            boolean supprime = analyseService.supprimer(idAnalyse);
            assertTrue(supprime);
            System.out.println("✅ Analyse supprimée: " + idAnalyse);
        }
    }

    @Test
    @Order(20)
    void testSupprimerCV() {
        boolean supprime = cvService.supprimer(testCvId);
        assertTrue(supprime);
        
        CV cvSupprime = cvService.lire(testCvId);
        assertNull(cvSupprime);
        System.out.println("✅ CV supprimé: " + testCvId);
    }
}
