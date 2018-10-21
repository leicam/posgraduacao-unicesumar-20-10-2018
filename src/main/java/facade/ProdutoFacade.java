package facade;

import entidades.Cidade;
import entidades.Estado;
import entidades.Produto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProdutoFacade extends AbstractFacade<Produto>{

    @PersistenceContext(unitName = "posjava2018jsfPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProdutoFacade() {
        super(Produto.class);
    }
    
}
