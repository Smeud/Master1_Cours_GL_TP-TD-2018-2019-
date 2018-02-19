package metier.entities;

import java.io.Serializable;
import java.lang.Long;
import java.util.*;
import javax.persistence.*;

@Entity //annotation pour la persistance de l'entite Compte.

@Table(name="COMPTES") //annotation declarant le nom de la table dans la base de donnees.

/*Creation de l'entite Serializable Compte par l'implementation des variables, 
son(ses) constructeur(s), leurs getters et setters.*/
public class Compte implements Serializable {   
	@Id //Annotation pour la clef primaire
	@GeneratedValue(strategy=GenerationType.IDENTITY) //annotation pour l'auto-incrementation du champs clef primaire.
	@Column(name="ID_COMPTE") //annotation declarant le nom du champs.
	private Long idCpte; //declaration de la variable.
	@Column(name="TITULAIRE")
	private String nomTitulaire;
	@Column(name="PID")
	private Long persId;
	@Column(name="SOLDE")
	private double solde;
	@Column(name="TAUX_INTERET")
	private double tauxInteret;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="idCpte")
	private Collection<Transaction> transactions;
	
	//les constructeurs
	public Compte() {
		super();
	}
	public Compte(Long idCpte, String nomTitulaire, Long persId, double solde, double tauxInteret, Date dateCreation) {
		super();
		this.idCpte = idCpte;
		this.nomTitulaire = nomTitulaire;
		this.persId = persId;
		this.solde = solde;
		this.tauxInteret = tauxInteret;
		this.dateCreation = dateCreation;
	}
	public Compte(String nomTitulaire, Long persId) {
		super();
		this.nomTitulaire = nomTitulaire;
		this.persId = persId;
	}
	/*public Compte(Long idCpte, String nomTitulaire, Long persId, double solde, double tauxInteret, Date dateCreation,
			Collection<Transaction> transactions) {
		super();
		this.idCpte = idCpte;
		this.nomTitulaire = nomTitulaire;
		this.persId = persId;
		this.solde = solde;
		this.tauxInteret = tauxInteret;
		this.dateCreation = dateCreation;
		this.transactions = transactions;
	}*/
	//les Getters et Setters
	public Collection<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(Collection<Transaction> transactions) {
		this.transactions = transactions;
	}
	public Long getIdCpte() {
		return idCpte;
	}
	public void setIdCpte(Long idCpte) {
		this.idCpte = idCpte;
	}
	public String getNomTitulaire() {
		return nomTitulaire;
	}
	public void setNomTitulaire(String nomTitulaire) {
		this.nomTitulaire = nomTitulaire;
	}
	public Long getPersId() {
		return persId;
	}
	public void setPersId(Long persId) {
		this.persId = persId;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public double getTauxInteret() {
		return tauxInteret;
	}
	public void setTauxInteret(double tauxInteret) {
		this.tauxInteret = tauxInteret;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	} 
}
