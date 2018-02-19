package metier;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.Compte;
import metier.entities.Transaction;
import metier.entities.Depot;
import metier.entities.Retrait;

@Stateless(name="BKGL")
public class BanqueGLImpl implements BanqueglLocal,BanqueglRemote {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Compte addCompte(Compte cp, double taux) {
		cp.setTauxInteret(taux);
		cp.setDateCreation(new Date());
		em.persist(cp);
		return cp;
	}

	@Override
	public Compte getCompte(Long idCpte) {
		Compte cp=em.find(Compte.class, idCpte);
		if(cp==null) throw new RuntimeException("Compte introuvable!");
		return cp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Compte> getComptesByClient(Long persId) {
		Query req=em.createQuery("SELECT c FROM Compte c WHERE persId=:x");
		req.setParameter("x", persId);
		List<Compte> listComptes=req.getResultList();
		return listComptes;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Transaction> getTransactionsByComptes(Long idCpte) {
		Query req=em.createQuery("SELECT t FROM Transaction t WHERE idCpte=:x");
		req.setParameter("x", idCpte);
		List<Transaction> listTransact=req.getResultList();
		return listTransact;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Compte> getComptes() {
		Query req=em.createQuery("select c from Compte c");
		//List<Compte> Comptes=req.getResultList();
		return req.getResultList();
	}

	@Override
	public void Depot(Depot dpt, Long idCpte) {
		double interet;
		Compte cp=getCompte(idCpte);
		//Calcul d'interet
		interet =cp.getTauxInteret()*dpt.getMontant();
		//Mise a jour du solde
		cp.setSolde((cp.getSolde()+dpt.getMontant())+interet);
		dpt.setDateTransaction(new Date());
		//Recuperer la valeur du champs idCptes dans compte et l'assigner au champs idCpte de la classe transaction.
		dpt.setIdCpte(cp);
		em.persist(dpt);
		em.merge(cp);
	}

	@Override
	public void Retrait(Retrait rt, Long idCpte) {
		Compte cp=getCompte(idCpte);
		em.persist(rt);
		if(cp.getSolde()<=rt.getMontant())
			throw new RuntimeException("Solde Insuffisant!");
		cp.setSolde(cp.getSolde()-rt.getMontant());
		rt.setDateTransaction(new Date());
		rt.setIdCpte(cp);
		em.merge(cp);
	}

}