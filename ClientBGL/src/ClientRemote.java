
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.naming.Context;
import javax.naming.InitialContext;

import metier.BanqueglRemote;
import metier.entities.Comptes;
import metier.entities.Depot;
import metier.entities.Retrait;
import metier.entities.Transactions;

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
			
			//Comptes c = proxy.getCompte(1L);
			//System.out.println(c.getTransactions());
			//Affichage menu
			
			System.out.print("\n=========================================================="
		    		+ "==============================================================\n"+ 
		    		" "+" "+ " "+ " "+ " " +" "+" "+ " "+ " "+" "+" "+" "+ " "+ " "+ " " +
					" "+" "+ " "+ " "+" "+" "+ " "+ " "+ " " +" "+" "+ " "+ " "+
					" "+" "+ " "+ " "+ " "+ " "+" BIENVENU SUR NOTRE SYSTEME DE GESTION BANCAIRE "+
		    		"\n================================================================================="
		    		+ "========================================== \n");
			Scanner sc=new Scanner(System.in);
		    String selection=""; 
		   do
		   {
		    System.out.println(" "+" "+" "+ " "+ " "+ " " +" "+" "+ " "+ " "+" "+" "+" "+ " "+ " "+ " " +
					" "+" "+ " "+ " "+" "+" "+ " "+ " "+ " " +" "+" "+ " "+ " "+
					" "+" "+ " "+ " "+ " "+ " "+"  | Pour créer un compte : tapez A"+ " "+" "+" "+" "+" "+"|");
			
			System.out.println("\n "+" "+" "+ " "+ " "+ " " +" "+" "+ " "+ " "+" "+" "+" "+ " "+ " "+ " " +
					" "+" "+ " "+ " "+" "+" "+ " "+ " "+ " " +" "+" "+ " "+ " "+
					" "+" "+ " "+ " "+ " "+ " "+"  | Pour effectuer un dépot : tapez B"+ " "+" "+"|");
			
			System.out.println("\n "+" "+" "+ " "+ " "+ " " +" "+" "+ " "+ " "+" "+" "+" "+ " "+ " "+ " " +
					" "+" "+ " "+ " "+" "+" "+ " "+ " "+ " " +" "+" "+ " "+ " "+
					" "+" "+ " "+ " "+ " "+ " "+"  | Pour effectuer un retrait : tapez C|");
			
			System.out.println("\n "+" "+" "+ " "+ " "+ " " +" "+" "+ " "+ " "+" "+" "+" "+ " "+ " "+ " " +
					" "+" "+ " "+ " "+" "+" "+ " "+ " "+ " " +" "+" "+ " "+ " "+
					" "+" "+ " "+ " "+ " "+ " "+"  | Pour lister les infos d'un compte : tapez D|");
			
			System.out.println("\n "+" "+" "+ " "+ " "+ " " +" "+" "+ " "+ " "+" "+" "+" "+ " "+ " "+ " " +
					" "+" "+ " "+ " "+" "+" "+ " "+ " "+ " " +" "+" "+ " "+ " "+
					" "+" "+ " "+ " "+ " "+ " "+"  | Pour lister tous les comptes d'un client : tapez E|");
			
			System.out.println("\n "+" "+" "+ " "+ " "+ " " +" "+" "+ " "+ " "+" "+" "+" "+ " "+ " "+ " " +
					" "+" "+ " "+ " "+" "+" "+ " "+ " "+ " " +" "+" "+ " "+ " "+
					" "+" "+ " "+ " "+ " "+ " "+"  | Pour lister les transaction d'un compte : tapez F|");
			
			System.out.println("\n "+" "+" "+ " "+ " "+ " " +" "+" "+ " "+ " "+" "+" "+" "+ " "+ " "+ " " +
					" "+" "+ " "+ " "+" "+" "+ " "+ " "+ " " +" "+" "+ " "+ " "+
					" "+" "+ " "+ " "+ " "+ " "+"  | Pour quitter le programme : tapez G|");
			System.out.print("\n Faites votre choix dans le menu: ");
		    selection=sc.nextLine();
			
		    //sca = new Scanner(System.in); 
		    String nomCli;
		    Long pid; 
		    Double taux;
		    
			switch(selection)
			{
			case "A":;
			//Long idC = null;
			System.out.println("Nom du titulaire :");
			nomCli=sc.nextLine();
			System.out.println("Numéro d'identification du titulaire(PID) : ");
			pid=sc.nextLong();
			System.out.println("Taux d'intérêt du compte :");
			taux=sc.nextDouble();
			proxy.addCompte(new Comptes(nomCli,pid), taux);
			/*Comptes cpt=proxy.getCompte(idC);
			//Resumé de l'enregistrement
			System.out.println("Vous venez d'enregistrer un compte bancaire avec les informations suivantes: \n"+

				"\n NUMERO D'IDENTIFICATION DU TITULAIRE:"+" "+cpt.getPersId()+
				"\n NOM DU TITULAIRE:"+ 
				" "+nomCli+ "\n MONTANT INITIAL:"+" " +cpt.getSolde()+ 
				"\n TAUX:"+" "+cpt.getTauxInteret()+
				"\n DATE:"+" " +cpt.getDateCreation()+
				"\n NUMERO DU COMPTE:"+ " "+cpt.getIdCpte());*/
				break;
				
			case "B":
				System.out.println("Effectuer le dépot");
				Long numCp;
				Double mt;
				
				System.out.println("SVP, saisir le numero du compte: ");
				numCp=sc.nextLong();
				System.out.println("Saisir le montant: ");
				mt=sc.nextDouble();
				proxy.Depot(new Depot(mt), numCp);
				break;
				
			case "C":
				System.out.println("Effectuer le retrait");
				Long numCp1;
				Double mt1;
				
				System.out.println("SVP, saisir le numero du compte: ");
				numCp1=sc.nextLong();
				System.out.println("Saisir le montant: ");
				mt1=sc.nextDouble();
				proxy.Retrait(new Retrait(mt1), numCp1);
				break;
				
			case "D":
				Long numCpte;
				System.out.println("SVP, saisir le numero du compte: ");
				numCpte=sc.nextLong();
				Comptes cpt1=proxy.getCompte(numCpte);
				System.out.println("Vous avez enregistré un compte bancaire avec les informations suivantes: \n"+

					"\n NUMERO D'IDENTIFICATION DU TITULAIRE:"+" "+cpt1.getPersId()+
					"\n NOM DU TITULAIRE:"+ 
					" "+cpt1.getNomTitulaire()+ "\n SOLDE:"+" " +cpt1.getSolde()+ 
					"\n TAUX:"+" "+cpt1.getTauxInteret()+
					"\n DATE:"+" " +cpt1.getDateCreation()+
					"\n NUMERO DU COMPTE:"+ " "+cpt1.getIdCpte());
				break;
			
			case "E":
				Long numCli1;
				System.out.println("SVP, saisir le numéro du client: ");
				numCli1=sc.nextLong();
				List<Comptes> lis=proxy.getComptesByClient(numCli1);
				for (Iterator<Comptes> iterator = lis.iterator(); iterator.hasNext();) {
					Comptes c = iterator.next();
					System.out.println("Vous avez enregistré un compte bancaire avec les informations suivantes: \n"+

						"\n NUMERO D'IDENTIFICATION DU TITULAIRE: "+" "+c.getPersId()+
						"\n NOM DU TITULAIRE: "+c.getNomTitulaire()+ 
						"\n Solde: "+" " +c.getSolde()+ 
						"\n TAUX:"+" "+c.getTauxInteret()+
						"\n DATE DE CREATION:"+" "+c.getDateCreation());
				}
				break;
				
			case "F":
				Long numCpte1;
				System.out.println("SVP, saisir le numéro du compte: ");
				numCpte1=sc.nextLong();
				List<Transactions> listTransaction=proxy.getTransactionsByComptes(numCpte1);
				for (Iterator<Transactions> iterator = listTransaction.iterator(); iterator.hasNext();) {
					Transactions tr = iterator.next();
					System.out.println("Vous avez enregistré un compte bancaire avec les informations suivantes: \n"+

						"\n NUMERO DE LA TRANSACTION:"+" "+tr.getIdTransaction()+
						"\n NUMERO DU COMPTE:"+" "+tr.getCompte()+ 
						"\n  MONTANT CONCERNE "+tr.getMontant()+ 
						"\n DATE DE TRANSACTION:"+" " +tr.getDateTransaction());
				}
				break;
				
			case "G":

				System.out.println("============== FIN DU PROGRAMME ==============");
				System.exit(0);
				break;
				
			default:
				System.out.println("Séléction invalide!");
				break;	
			}
			
		   }
			while(selection!="G");
		   sc.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
