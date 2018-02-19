package metier;

import java.util.List;
import javax.ejb.Remote;
import metier.entities.Compte;
import metier.entities.Transaction;
import metier.entities.Depot;
import metier.entities.Retrait;

@Remote
public interface BanqueglRemote {
	public Compte addCompte(Compte cp, double taux);
	public Compte getCompte(Long idCpte);
	public List<Compte> getComptes();
	public List<Compte> getComptesByClient(Long persId);
	public void Depot(Depot dpt, Long idCpte);
	public void Retrait(Retrait rt, Long idCpte);
	public List<Transaction> getTransactionsByComptes(Long idCpte);
	
}
