package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidades.ContasReceber;
import entidades.ItensVenda;
import entidades.Venda;
import entidades.Pessoa;
import entidades.Produto;
import facade.ContasReceberFacade;
import facade.VendaFacade;
import facade.PessoaFacade;
import facade.ProdutoFacade;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@SessionScoped
@ManagedBean
public class VendaControle {

    private Venda venda;
    private ItensVenda itensVenda;
    @EJB
    private VendaFacade vendaFacade;
    @EJB
    private PessoaFacade pessoaFacade;
    private ConverterGenerico pessoaConverter;
    @EJB
    private ProdutoFacade produtoFacade;
    private ConverterGenerico produtoConverter;
    private MoneyConverter moneyConverter;

    private Integer nParcela;

    @EJB
    private ContasReceberFacade contasReceberFacade;

    public Integer getnParcela() {
        return nParcela;
    }

    public void setnParcela(Integer nParcela) {
        this.nParcela = nParcela;
    }

    public List<ContasReceber> getContasReceber() {
        return contasReceberFacade.listaTodos();
    }

    public void addParcela() {
        Date dataTemp = venda.getDataVenda();

        for (int i = 1; i <= nParcela; i++) {
            ContasReceber contasReceber = new ContasReceber();
            contasReceber.setDataLancamento(venda.getDataVenda());
            contasReceber.setNumeroParcela(i);
            contasReceber.setPessoa(venda.getPessoa());
            contasReceber.setVenda(venda);
            contasReceber.setDataVencimento(dataTemp);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataTemp);
            cal.add(Calendar.MONTH, 1);
            dataTemp = cal.getTime();
            contasReceber.setValor(venda.getValorTotal() / nParcela);
            
            venda.getContasRecebers().add(contasReceber);
        }
    }

    public MoneyConverter getMoneyConverter() {
        if (moneyConverter == null) {
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }

//    Cocacola - estoque = 20 == Adicioando 10 + 1
    public void addItem() {
        Double estoque = itensVenda.getProduto().getEstoque();
        ItensVenda itemTemp = null;
        for (ItensVenda it : venda.getItensVendas()) {
            if (it.getProduto().equals(itensVenda.getProduto())) {
                itemTemp = it;
                estoque = estoque - it.getQuantidade();
            }
        }
        if ((estoque - itensVenda.getQuantidade()) < 0) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Estoque insuficiente!",
                            "Restam apenas " + estoque + " unidades."));
        } else {
            if (itemTemp == null) {
                itensVenda.setVenda(venda);
                venda.getItensVendas().add(itensVenda);
            } else {
                itemTemp.setQuantidade(itemTemp.getQuantidade() + itensVenda.getQuantidade());
                itemTemp.setPreco(itensVenda.getPreco());
            }
            itensVenda = new ItensVenda();
        }
    }

    public void setaPrecoItem() {
        itensVenda.setPreco(itensVenda.getProduto().getPreco());
    }

    public List<Produto> listaProdutosFiltrando(String filtro) {
        return produtoFacade.listaFiltrando(filtro, "nome");
    }

    public ConverterGenerico getProdutoConverter() {
        if (produtoConverter == null) {
            produtoConverter = new ConverterGenerico(produtoFacade);
        }
        return produtoConverter;
    }

    public void setProdutoConverter(ConverterGenerico produtoConverter) {
        this.produtoConverter = produtoConverter;
    }

    public ItensVenda getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(ItensVenda itensVenda) {
        this.itensVenda = itensVenda;
    }

    public void novo() {
        venda = new Venda();
        itensVenda = new ItensVenda();
    }

    public ConverterGenerico getPessoaConverter() {
        if (pessoaConverter == null) {
            pessoaConverter = new ConverterGenerico(pessoaFacade);
        }
        return pessoaConverter;
    }

    public void setPessoaConverter(ConverterGenerico pessoaConverter) {
        this.pessoaConverter = pessoaConverter;
    }

    public List<Pessoa> listaPessoasFiltrando(String filtro) {
        return pessoaFacade.listaFiltrando(filtro, "nome");
    }

    public void salvar() {
        vendaFacade.salvar(venda);
        venda = new Venda();
    }

    public void excluir(Venda cid) {
        vendaFacade.remover(cid);
    }

    public void editar(Venda cid) {
        this.venda = cid;
    }

    public List<Venda> getListaVendas() {
        return vendaFacade.listaTodos();
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

}
