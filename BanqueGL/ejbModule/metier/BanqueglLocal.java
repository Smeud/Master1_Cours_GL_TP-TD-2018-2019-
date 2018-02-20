package metier;

import java.util.List;
import javax.ejb.Local;
import metier.entities.Comptes;
import metier.entities.Transactions;
import metier.entities.Depot;
import metier.entities.Retrait;

@Local
public interface BanqueglLocal {
	public Comptes addCompte(Comptes cp, double taux);
	public void Depot(Depot dpt, Long idCpte);
	public void Retrait(Retrait rt, Long idCpte);
	public Comptes getCompte(Long idCpte);
	public List<Comptes> getComptes();
	public List<Comptes> getComptesByClient(Long persId);
	public List<Transactions> getTransactionsByComptes(Long idCpte);
	
}