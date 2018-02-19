package service;

import java.util.Date;
//import java.sql.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import metier.BanqueglLocal;
import metier.entities.Compte;
import metier.entities.Depot;
import metier.entities.Retrait;
import metier.entities.Transaction;

@WebService

public class BanqueglService {
	@EJB
	private BanqueglLocal metier;
	
	@WebMethod
	public Compte addCompte(
			@WebParam(name="PID")Long persId, 
			@WebParam(name="Titulaire")String nomTitulaire,
			@WebParam(name="Solde")Double solde,
			@WebParam(name="TauxInteret")Double tauxInteret){
		Compte cp=new Compte();
		cp.setPersId(persId);
		cp.setNomTitulaire(nomTitulaire);
		cp.setSolde(solde);
		cp.setTauxInteret(tauxInteret);
		cp.setDateCreation(new Date());
		return metier.addCompte(cp, tauxInteret);
	}

	@WebMethod
	public Compte getCompte(
			@WebParam(name="IDCpte")Long idCpte) {
		return metier.getCompte(idCpte);
	}
	@WebMethod
	public List<Compte> getComptes() {
		return metier.getComptes();
	}
	@WebMethod
	public List<Compte> getComptesByClient(
			@WebParam(name="PID")Long persId) {
		return metier.getComptesByClient(persId);
	}
	@WebMethod
	public void Depot(
			@WebParam(name="IDCpte")Long idCpte,
			@WebParam(name="Montant")Double montant){
		Compte cp=new Compte();
		Depot dpt=new Depot();
		dpt.setMontant(montant);
		cp.setIdCpte(idCpte);
		dpt.setDateTransaction(new Date());
		metier.Depot(dpt, idCpte);
		//System.out.print("Montant depose: "+dpt.getMontant()+"--------"+"Solde actuel"+cp.getSolde());
	}
	@WebMethod
	public void Retrait(
			@WebParam(name="IDCpte")Long idCpte, 
			@WebParam(name="Montant")Double montant) {
		Compte cp=new Compte();
		Retrait rt=new Retrait();
		rt.setMontant(montant);
		cp.setIdCpte(idCpte);
		rt.setDateTransaction(new Date());
		metier.Retrait(rt, idCpte);
		//System.out.print("Montant depose: "+rt.getMontant()+"--------"+"Solde actuel"+cp.getSolde());
	}
	@WebMethod
	public List<Transaction> getTransactionsByComptes(
			@WebParam(name="IDCpte")Long idCpte) {
		return metier.getTransactionsByComptes(idCpte);
	}
	
}
