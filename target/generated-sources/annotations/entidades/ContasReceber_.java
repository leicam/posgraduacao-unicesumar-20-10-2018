package entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContasReceber.class)
public abstract class ContasReceber_ {

	public static volatile SingularAttribute<ContasReceber, Venda> venda;
	public static volatile SingularAttribute<ContasReceber, Pessoa> pessoa;
	public static volatile SingularAttribute<ContasReceber, Date> dataVencimento;
	public static volatile SingularAttribute<ContasReceber, Double> valor;
	public static volatile SingularAttribute<ContasReceber, Long> id;
	public static volatile SingularAttribute<ContasReceber, Integer> numeroParcela;
	public static volatile SingularAttribute<ContasReceber, Date> dataLancamento;

}

