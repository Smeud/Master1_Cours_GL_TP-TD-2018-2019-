package metier.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Retrait")
public class Retrait extends Transactions{
	private static final long serialVersionUID = 1L;

	public Retrait() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Retrait(Long idTransaction, Double montant, Date dateTransaction) {
		super(idTransaction, montant, dateTransaction);
		// TODO Auto-generated constructor stub
	}
	public Retrait(Double montant) {
		super(montant);
	}
}

