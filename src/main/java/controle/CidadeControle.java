package controle;

import converter.ConverterGenerico;
import entidades.Cidade;
import entidades.Estado;
import facade.CidadeFacade;
import facade.EstadoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

//@RequestScoped
//@ViewScoped
@SessionScoped
//@ApplicationScoped
@ManagedBean
public class CidadeControle {

    private Cidade cidade;
    @EJB
    private CidadeFacade cidadeFacade;
    @EJB
    private EstadoFacade estadoFacade;
    private ConverterGenerico estadoConverter;
    
    public void novo(){
        cidade = new Cidade();
    }

    public ConverterGenerico getEstadoConverter() {
        if(estadoConverter == null){
            estadoConverter = new ConverterGenerico(estadoFacade);
        }
        return estadoConverter;
    }

    public void setEstadoConverter(ConverterGenerico estadoConverter) {
        this.estadoConverter = estadoConverter;
    }
    
    public List<Estado> listaEstadosFiltrando(String filtro){
        return estadoFacade.listaFiltrando(filtro, "nome", "sigla");
    }
    
    public void salvar(){
        cidadeFacade.salvar(cidade);
        cidade = new Cidade();
    }
    
    public void excluir(Cidade cid){
        cidadeFacade.remover(cid);
    }
    
    public void editar(Cidade cid){
        this.cidade = cid;
    }

    public List<Cidade> getListaCidades() {
        return cidadeFacade.listaTodos();
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

}
