package com.benito.dalmeida.app.arrange.movie;



import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
public class AlloCineMovieInfo {
   private String filename;
   private String titleKey;
   private String titre;
   private String movieLink;
   private String titreOriginal;
   private String dateDeSortie;
   private String anneeDeSortie;
   private String publicType;
   private String duree;
   private String genre;
   private String realisateur;
   private String acteurs;
   private String notePresse;
   private String noteSpec;
   private String synopsis;
   private String affiche;
   private File movieFile;

   public AlloCineMovieInfo copy(File contentFile) {
      AlloCineMovieInfo alloCineMovieInfo =  new AlloCineMovieInfo();
      alloCineMovieInfo.setFilename(contentFile.getName());
      alloCineMovieInfo.setMovieFile(contentFile);
      alloCineMovieInfo.setTitleKey(this.titleKey);
      alloCineMovieInfo.setActeurs(this.acteurs);
      alloCineMovieInfo.setAffiche(this.affiche);
      alloCineMovieInfo.setAnneeDeSortie(this.anneeDeSortie);
      alloCineMovieInfo.setDateDeSortie(this.dateDeSortie);
      alloCineMovieInfo.setDuree(this.duree);
      alloCineMovieInfo.setGenre(this.genre);
      alloCineMovieInfo.setMovieLink(this.movieLink);
      alloCineMovieInfo.setNotePresse(this.notePresse);
      alloCineMovieInfo.setNoteSpec(this.noteSpec);
      alloCineMovieInfo.setPublicType(this.publicType);
      alloCineMovieInfo.setRealisateur(this.realisateur);
      alloCineMovieInfo.setSynopsis(this.synopsis);
      alloCineMovieInfo.setTitre(this.titre);
      alloCineMovieInfo.setTitreOriginal(this.titreOriginal);
      return alloCineMovieInfo;
   }
//
//   public String getFilename() {
//      return filename;
//   }
//
//   public void setFilename(String filename) {
//      this.filename = filename;
//   }
//
//   public String getTitleKey() {
//      return titleKey;
//   }
//
//   public void setTitleKey(String titleKey) {
//      this.titleKey = titleKey;
//   }
//
//   public String getTitre() {
//      return titre;
//   }
//
//   public void setTitre(String titre) {
//      this.titre = titre;
//   }
//
//   public String getMovieLink() {
//      return movieLink;
//   }
//
//   public void setMovieLink(String movieLink) {
//      this.movieLink = movieLink;
//   }
//
//   public String getTitreOriginal() {
//      return titreOriginal;
//   }
//
//   public void setTitreOriginal(String titreOriginal) {
//      this.titreOriginal = titreOriginal;
//   }
//
//   public String getDateDeSortie() {
//      return dateDeSortie;
//   }
//
//   public void setDateDeSortie(String dateDeSortie) {
//      this.dateDeSortie = dateDeSortie;
//   }
//
//   public String getAnneeDeSortie() {
//      return anneeDeSortie;
//   }
//
//   public void setAnneeDeSortie(String anneeDeSortie) {
//      this.anneeDeSortie = anneeDeSortie;
//   }
//
//   public String getPublicType() {
//      return publicType;
//   }
//
//   public void setPublicType(String publicType) {
//      this.publicType = publicType;
//   }
//
//   public String getDuree() {
//      return duree;
//   }
//
//   public void setDuree(String duree) {
//      this.duree = duree;
//   }
//
//   public String getGenre() {
//      return genre;
//   }
//
//   public void setGenre(String genre) {
//      this.genre = genre;
//   }
//
//   public String getRealisateur() {
//      return realisateur;
//   }
//
//   public void setRealisateur(String realisateur) {
//      this.realisateur = realisateur;
//   }
//
//   public String getActeurs() {
//      return acteurs;
//   }
//
//   public void setActeurs(String acteurs) {
//      this.acteurs = acteurs;
//   }
//
//   public String getNotePresse() {
//      return notePresse;
//   }
//
//   public void setNotePresse(String notePresse) {
//      this.notePresse = notePresse;
//   }
//
//   public String getNoteSpec() {
//      return noteSpec;
//   }
//
//   public void setNoteSpec(String noteSpec) {
//      this.noteSpec = noteSpec;
//   }
//
//   public String getSynopsis() {
//      return synopsis;
//   }
//
//   public void setSynopsis(String synopsis) {
//      this.synopsis = synopsis;
//   }
//
//   public String getAffiche() {
//      return affiche;
//   }
//
//   public void setAffiche(String affiche) {
//      this.affiche = affiche;
//   }
//
//   public List<File> getMovieFiles() {
//      return movieFiles;
//   }
}
