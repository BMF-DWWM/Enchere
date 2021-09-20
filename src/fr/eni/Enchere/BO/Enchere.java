package fr.eni.Enchere.BO;

import java.sql.Date;

public class Enchere {
	int noUtilisateur;
	Date dateEnchere;
	int noArticle;
	int montantEnchere;
	/**
	 * @param noUtilisateur
	 * @param dateEnchere
	 * @param noArticle
	 * @param montantEnchere
	 */
	public Enchere(int noUtilisateur, Date dateEnchere, int noArticle, int montantEnchere) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.dateEnchere = dateEnchere;
		this.noArticle = noArticle;
		this.montantEnchere = montantEnchere;
	}
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	public Date getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public int getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	
	

}
