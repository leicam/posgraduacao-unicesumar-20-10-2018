package controle;

import converter.ConverterGenerico;
import entidades.Cidade;
import entidades.PessoaFisica;
import entidades.Estado;
import facade.CidadeFacade;
import facade.PessoaFisicaFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@SessionScoped
@ManagedBean
public class PessoaFisicaControle implements Serializable{

    private PessoaFisica pessoaFisica;
    @EJB
    private PessoaFisicaFacade pessoaFisicaFacade;
    @EJB
    private CidadeFacade cidadeFacade;
    private ConverterGenerico cidadeConverter;
    
    public void novo(){
        pessoaFisica = new PessoaFisica();
    }

    public ConverterGenerico getCidadeConverter() {
        if(cidadeConverter == null){
            cidadeConverter = new ConverterGenerico(cidadeFacade);
        }
        return cidadeConverter;
    }

    public void setCidadeConverter(ConverterGenerico cidadeConverter) {
        this.cidadeConverter = cidadeConverter;
    }
    
    public List<Cidade> listaCidadeFiltrando(String filtro){
        return cidadeFacade.listaFiltrando(filtro, "nome");
    }
    
    public void salvar(){
        pessoaFisicaFacade.salvar(pessoaFisica);
        pessoaFisica = new PessoaFisica();
    }
    
    public void excluir(PessoaFisica cid){
        pessoaFisicaFacade.remover(cid);
    }
    
    public void editar(PessoaFisica cid){
        this.pessoaFisica = cid;
    }

    public List<PessoaFisica> getListaPessoaFisicas() {
        return pessoaFisicaFacade.listaTodos();
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

}
