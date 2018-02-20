package metier;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.Comptes;
import metier.entities.Transactions;
import metier.entities.Depot;
import metier.entities.Retrait;

@Stateless(name="BKGL")
public class BanqueGLImpl implements BanqueglLocal,BanqueglRemote {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Comptes addCompte(Comptes cp, double taux) {
		cp.setTauxInteret(taux);
		cp.setDateCreation(new Date());
		em.persist(cp);
		return cp;
	}

	@Override
	public Comptes getCompte(Long idCpte) {
		Comptes cp=em.find(Comptes.class, idCpte);
		if(cp==null) throw new RuntimeException("Compte introuvable!");
		return cp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Comptes> getComptesByClient(Long persId) {
		Query req=em.createQuery("SELECT c FROM Comptes c WHERE c.client.persId=:x");
		req.setParameter("x", persId);
		List<Comptes> listComptes=req.getResultList();
		return listComptes;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Transactions> getTransactionsByComptes(Long idCpte) {
		Query req=em.createQuery("SELECT t FROM Transaction t WHERE t.compte.idCpte=:x");
		req.setParameter("x", idCpte);
		List<Transactions> listTransact=req.getResultList();
		return listTransact;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comptes> getComptes() {
		Query req=em.createQuery("select c from Compte c");
		List<Comptes> listCompte=req.getResultList();
		return listCompte;
	}

	@Override
	public void Depot(Depot dpt, Long idCpte) {
		double interet;
		Comptes cp=getCompte(idCpte);
		//Calcul d'intérêt
		interet =cp.getTauxInteret()*dpt.getMontant();
		//Mise a jour du solde
		cp.setSolde((cp.getSolde()+dpt.getMontant())+interet);
		dpt.setDateTransaction(new Date());
		//Récuperer la valeur du champs idCptes dans compte et l'assigner au champs idCpte de la classe transaction
		dpt.setIdCpte(cp);
		em.persist(dpt);
		em.merge(cp);
	}

	@Override
	public void Retrait(Retrait rt, Long idCpte) {
		Comptes cp=getCompte(idCpte);
		em.persist(rt);
		if(cp.getSolde()<=rt.getMontant())
			throw new RuntimeException("Solde Insuffisant!");
		cp.setSolde(cp.getSolde()-rt.getMontant());
		rt.setDateTransaction(new Date());
		rt.setIdCpte(cp);
		em.merge(cp);
	}

}