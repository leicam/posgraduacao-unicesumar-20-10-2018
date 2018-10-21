package facade;

import entidades.ContasReceber;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ContasReceberFacade extends AbstractFacade<ContasReceber>{

    @PersistenceContext(unitName = "posjava2018jsfPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContasReceberFacade() {
        super(ContasReceber.class);
    }
    
}
