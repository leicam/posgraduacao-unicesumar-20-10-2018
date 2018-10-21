package controle;

import entidades.GrupoProduto;
import facade.GrupoProdutoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

//@RequestScoped
//@ViewScoped
@SessionScoped
//@ApplicationScoped
@ManagedBean
public class GrupoProdutoControle {

    private GrupoProduto grupoProduto;
    @EJB
    private GrupoProdutoFacade grupoProdutoFacade;
    
    public void novo(){
        grupoProduto = new GrupoProduto();
    }
    
    public void salvar(){
        grupoProdutoFacade.salvar(grupoProduto);
        grupoProduto = new GrupoProduto();
    }
    
    public void excluir(GrupoProduto est){
        grupoProdutoFacade.remover(est);
    }
    
    public void editar(GrupoProduto est){
        this.grupoProduto = est;
    }

    public List<GrupoProduto> getListaGrupoProdutos() {
        return grupoProdutoFacade.listaTodos();
    }

    public GrupoProduto getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto(GrupoProduto grupoProduto) {
        this.grupoProduto = grupoProduto;
    }

}
