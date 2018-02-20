package metier.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Depot")
public class Depot extends Transactions {
	private static final long serialVersionUID = 1L;

	public Depot() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Depot(Long idTransaction, Double montant, Date dateTransaction) {
		super(idTransaction, montant, dateTransaction);
		// TODO Auto-generated constructor stub
	}
	public Depot(Double montant) {
		super(montant);
	}

}
