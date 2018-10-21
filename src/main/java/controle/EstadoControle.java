package controle;

import entidades.Estado;
import facade.EstadoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

//@RequestScoped
//@ViewScoped
@SessionScoped
//@ApplicationScoped
@ManagedBean
public class EstadoControle {

    private Estado estado;
    @EJB
    private EstadoFacade estadoFacade;
    
    public void novo(){
        estado = new Estado();
    }
    
    public void salvar(){
        estadoFacade.salvar(estado);
        estado = new Estado();
    }
    
    public void excluir(Estado est){
        try {
            estadoFacade.remover(est);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "O estado não pode ser removido, pois possui dependencias!",
                            null));
        }
        
    }
    
    public void editar(Estado est){
        this.estado = est;
    }

    public List<Estado> getListaEstados() {
        return estadoFacade.listaTodos();
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
