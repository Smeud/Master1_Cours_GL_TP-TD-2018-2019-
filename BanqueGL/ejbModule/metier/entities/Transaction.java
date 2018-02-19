package metier.entities;

import java.io.Serializable;
import java.lang.Double;
import java.lang.Long;
import java.util.*;
import javax.persistence.*;


@Entity //annotation pour la persistance de l'entite Compte.
@Table(name="TRANSACTIONS")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_TRANSACT", discriminatorType=DiscriminatorType.STRING,length=10) //declaration du champs type de la transaction au niveau de BD.

/*Creation de l'entite Serializable Compte par l'implementation des variables, 
son(ses) constructeur(s), leurs getters et setters.*/
public class Transaction implements Serializable {
	@Id //Annotation pour la clef primaire
	@GeneratedValue(strategy=GenerationType.IDENTITY) //annotation pour l'auto-incrementation du champs clef primaire.
	@Column(name="ID_TRANSACTION") //annotation declarant le nom du champs.
	private Long idTransaction; //declaration de la variable.
	@Column(name="MONTANT")
	private Double montant;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTransaction;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne 
	@JoinColumn(name="ID_COMPTE")
	private Compte idCpte;
	
	//les constructeurs
	public Transaction() {
		super();	
	}
	
	public Transaction(Long idTransaction, Double montant, Date dateTransaction, Compte idCpte) {
		super();
		this.idTransaction = idTransaction;
		this.montant = montant;
		this.dateTransaction = dateTransaction;
		this.idCpte = idCpte;
	}

	public Transaction(Long idTransaction, Double montant, Date dateTransaction) {
		super();
		this.idTransaction = idTransaction;
		this.montant = montant;
		this.dateTransaction = dateTransaction;
	}
	
	
	public Transaction(Double montant) {
		super();
		this.montant = montant;
	}
	//les Getters et Setters
	
	public Long getIdTransaction() {
		return idTransaction;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setIdTransaction(Long idTransaction) {
		this.idTransaction = idTransaction;
	}
	public Compte getIdCpte() {
		return idCpte;
	}

	public void setIdCpte(Compte idCpte) {
		this.idCpte = idCpte;
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
