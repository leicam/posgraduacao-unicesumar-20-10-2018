package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidades.Produto;
import entidades.GrupoProduto;
import facade.ProdutoFacade;
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
public class ProdutoControle {

    private Produto produto;
    @EJB
    private ProdutoFacade produtoFacade;
    @EJB
    private GrupoProdutoFacade grupoProdutoFacade;
    private ConverterGenerico grupoProdutoConverter;
    
    private MoneyConverter moneyConverter;

    public MoneyConverter getMoneyConverter() {
        if(moneyConverter == null){
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }
    
    
    
    public void novo(){
        produto = new Produto();
    }

    public ConverterGenerico getGrupoProdutoConverter() {
        if(grupoProdutoConverter == null){
            grupoProdutoConverter = new ConverterGenerico(grupoProdutoFacade);
        }
        return grupoProdutoConverter;
    }

    public void setGrupoProdutoConverter(ConverterGenerico grupoProdutoConverter) {
        this.grupoProdutoConverter = grupoProdutoConverter;
    }
    
    public List<GrupoProduto> listaGrupoProdutosFiltrando(String filtro){
        return grupoProdutoFacade.listaFiltrando(filtro, "nome");
    }
    
    public void salvar(){
        produtoFacade.salvar(produto);
        produto = new Produto();
    }
    
    public void excluir(Produto cid){
        produtoFacade.remover(cid);
    }
    
    public void editar(Produto cid){
        this.produto = cid;
    }

    public List<Produto> getListaProdutos() {
        return produtoFacade.listaTodos();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
