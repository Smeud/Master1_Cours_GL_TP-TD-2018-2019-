package metier.entities;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Long;
import java.util.*;
import javax.persistence.*;


@Entity //annotation pour la persistance de l'entit� Compte.
@Table(name="TRANSACTIONS")//annotation pour le nom de la table
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)//annotation pour la relation d'h�ritage 
@DiscriminatorColumn(name="TYPE_TRANSACT", discriminatorType=DiscriminatorType.STRING,length=10) //declaration du champs type de la transaction au niveau de BD.

/*Cr�ation de l'entit� Serializable Compte par l'impl�mentation des variables, 
leur(s) constructeur(s), leurs getters et setters.*/
public class Transactions implements Serializable {
	@Id //annotation pour la clef primaire
	@GeneratedValue(strategy=GenerationType.IDENTITY) //annotation pour l'auto-incr�mentation du champs clef primaire.
	@Column(name="ID_TRANSACTION") //annotation d�clarant le nom du champs.
	private Long idTransaction; //d�claration de la variable.
	@Column(name="MONTANT")
	private Double montant;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTransaction;
	@ManyToOne 
	@JoinColumn(name="ID_COMPTE")
	private Comptes compte;
	private static final long serialVersionUID = 1L;
	
	//Constructeurs
	public Transactions() {
		super();	
	}
	
	public Transactions(Long idTransaction, Double montant, Date dateTransaction, Comptes compte) {
		super();
		this.idTransaction = idTransaction;
		this.montant = montant;
		this.dateTransaction = dateTransaction;
		this.compte = compte;
	}

	public Transactions(Long idTransaction, Double montant, Date dateTransaction) {
		super();
		this.idTransaction = idTransaction;
		this.montant = montant;
		this.dateTransaction = dateTransaction;
	}
	public Transactions(Double montant) {
		super();
		this.montant = montant;
	}
	
	//Getters et Setters
	public Long getIdTransaction() {
		return idTransaction;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setIdTransaction(Long idTransaction) {
		this.idTransaction = idTransaction;
	}
	public Comptes getCompte() {
		return compte;
	}

	public void setIdCpte(Comptes compte) {
		this.compte = compte;
	}
	public Double getMontant() {
		return montant;
	}

	public void setMontant(Double montant) {
		this.montant = montant;
	}

	public Date getDateTransaction() {
		return dateTransaction;
	}

	public void setDateTransaction(Date dateTransaction) {
		this.dateTransaction = dateTransaction;
	}	
   
}