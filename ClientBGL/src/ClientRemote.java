import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import metier.BanqueglRemote;
import metier.entities.Compte;
import metier.entities.Depot;
import metier.entities.Retrait;
import metier.entities.Transaction;

public class ClientRemote {

	public static void main(String[] args) {
		try {
			Context ctx=new InitialContext();
			String appName="";
			String moduleName="BanqueGL";
			String beanName="BKGL";
			String remoteInterface=BanqueglRemote.class.getName();
			String name="ejb:"+appName+"/"+moduleName+"/"+beanName+"!"+remoteInterface;
			
			BanqueglRemote proxy=(BanqueglRemote) ctx.lookup(name);
			
			//Creation et depot sur comptes 
			/*proxy.addCompte(new Compte("Alice", 901L), 0.05); 
			proxy.addCompte(new Compte("Bob", 902L), 0.05);
			proxy.addCompte(new Compte("Alice", 901L), 0.1);
			proxy.addCompte(new Compte("Eve", 903L), 0.1);
			
			proxy.Depot(new Depot(100.00), 1L);
			proxy.Depot(new Depot(50.00), 2L);
			proxy.Depot(new Depot(200.00), 3L);
			proxy.Depot(new Depot(200.00), 4L);*/
			
			Compte cp=proxy.getCompte(1L);
			System.out.println("Numero Compte--------------"+cp.getIdCpte());
			System.out.println("Solde----------------------"+cp.getSolde()+"--EURO");
			System.out.println("Titulaire------------------"+cp.getNomTitulaire());
			System.out.println("Numero PID-----------------"+cp.getPersId());
			System.out.println("---------------------------------------------------");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
