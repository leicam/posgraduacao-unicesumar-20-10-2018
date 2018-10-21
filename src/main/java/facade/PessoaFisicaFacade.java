package facade;

import entidades.Cidade;
import entidades.Estado;
import entidades.Pessoa;
import entidades.PessoaFisica;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PessoaFisicaFacade extends AbstractFacade<PessoaFisica>{

    @PersistenceContext(unitName = "posjava2018jsfPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PessoaFisicaFacade() {
        super(PessoaFisica.class);
    }
    
}
