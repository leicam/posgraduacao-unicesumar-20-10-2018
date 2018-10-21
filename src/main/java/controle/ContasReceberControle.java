package controle;

import entidades.ContasReceber;
import facade.ContasReceberFacade;
import java.util.Calendar;
import java.util.Date;
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
public class ContasReceberControle {

    private ContasReceber contasReceber;
    @EJB
    private ContasReceberFacade contasReceberFacade;
    
    public void novo(){
        contasReceber = new ContasReceber();
    }
    
    public void salvar(){
        if (contasReceber == null){
            contasReceber = new ContasReceber();
        }            
        contasReceberFacade.salvar(contasReceber);
        contasReceber = new ContasReceber();
    }
    
    public void excluir(ContasReceber est){
        try {
            contasReceberFacade.remover(est);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "O contasReceber não pode ser removido, pois possui dependencias!",
                            null));
        }
        
    }
    
    public void editar(ContasReceber est){
        this.contasReceber = est;
    }

    public List<ContasReceber> getListaContasRecebers() {
        return contasReceberFacade.listaTodos();
    }

    public ContasReceber getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(ContasReceber contasReceber) {
        this.contasReceber = contasReceber;
    }

}
