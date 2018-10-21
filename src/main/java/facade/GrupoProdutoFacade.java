package facade;

import entidades.GrupoProduto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GrupoProdutoFacade extends AbstractFacade<GrupoProduto>{

    @PersistenceContext(unitName = "posjava2018jsfPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoProdutoFacade() {
        super(GrupoProduto.class);
    }
    
    
}
