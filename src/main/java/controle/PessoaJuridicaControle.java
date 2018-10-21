package controle;

import converter.ConverterGenerico;
import entidades.PessoaJuridica;
import entidades.Cidade;
import facade.PessoaJuridicaFacade;
import facade.CidadeFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class PessoaJuridicaControle implements Serializable {

    private PessoaJuridica pessoaJuridica;
    @EJB
    private PessoaJuridicaFacade pessoaJuridicaFacade;
    @EJB
    private CidadeFacade cidadeFacade;
    private ConverterGenerico cidadeConverter;
    
    public void novo(){
        pessoaJuridica = new PessoaJuridica();
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
    
    public List<Cidade> listaCidadesFiltrando(String filtro){
        return cidadeFacade.listaFiltrando(filtro, "nome");
    }
    
    public void salvar(){
        pessoaJuridicaFacade.salvar(pessoaJuridica);
        pessoaJuridica = new PessoaJuridica();
    }
    
    public void excluir(PessoaJuridica cid){
        pessoaJuridicaFacade.remover(cid);
    }
    
    public void editar(PessoaJuridica cid){
        this.pessoaJuridica = cid;
    }

    public List<PessoaJuridica> getListaPessoaJuridicas() {
        return pessoaJuridicaFacade.listaTodos();
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

}
