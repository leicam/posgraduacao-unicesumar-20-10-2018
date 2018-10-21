package facade;

import entidades.ItensVenda;
import entidades.Produto;
import entidades.Venda;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class VendaFacade extends AbstractFacade<Venda>{

    @PersistenceContext(unitName = "posjava2018jsfPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VendaFacade() {
        super(Venda.class);
    }

    @Override
    public void salvar(Venda entity) {
        for(ItensVenda it : entity.getItensVendas()){
            Produto p = it.getProduto();
            p.setEstoque(p.getEstoque() - it.getQuantidade());
            em.merge(p);
        }
        super.salvar(entity);
    }

    @Override
    public List<Venda> listaTodos() {
        List<Venda> retorno = super.listaTodos();
        for(Venda v : retorno){
            v.getItensVendas().size();
            v.getContasRecebers().size();
        }
        return retorno;
    }
    
    
    
}
