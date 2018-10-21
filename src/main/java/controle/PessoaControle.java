package controle;

import converter.ConverterGenerico;
import entidades.Pessoa;
import entidades.PessoaFisica;
import entidades.PessoaJuridica;

import facade.PessoaFisicaFacade;
import facade.PessoaJuridicaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class PessoaControle implements Serializable{

    @EJB
    private PessoaFisicaFacade pessoaFisicaFacade;
    @EJB
    private PessoaJuridicaFacade pessoaJuridicaFacade;
    private ConverterGenerico estadoConverter;
    
    @ManagedProperty(value = "#{pessoaFisicaControle}")
    private PessoaFisicaControle pessoaFisicaControle;
    @ManagedProperty(value = "#{pessoaJuridicaControle}")
    private PessoaJuridicaControle pessoaJuridicaControle;

    public PessoaFisicaControle getPessoaFisicaControle() {
        return pessoaFisicaControle;
    }

    public void setPessoaFisicaControle(PessoaFisicaControle pessoaFisicaControle) {
        this.pessoaFisicaControle = pessoaFisicaControle;
    }

    public PessoaJuridicaControle getPessoaJuridicaControle() {
        return pessoaJuridicaControle;
    }

    public void setPessoaJuridicaControle(PessoaJuridicaControle pessoaJuridicaControle) {
        this.pessoaJuridicaControle = pessoaJuridicaControle;
    }
    
    public void excluir(Pessoa pe) {
        if (pe instanceof PessoaFisica) {
            pessoaFisicaFacade.remover((PessoaFisica) pe);
        } else {
            pessoaJuridicaFacade.remover((PessoaJuridica) pe);
        }
    }

    public String editar(Pessoa pe) {
        if (pe instanceof PessoaFisica) {
            pessoaFisicaControle.setPessoaFisica((PessoaFisica) pe);
            return "pessoafisicaedita";
        } else {
            pessoaJuridicaControle.setPessoaJuridica((PessoaJuridica) pe);
            return "pessoajuridicaedita";
        }
    }

    public List<Pessoa> getListaPessoas() {
        List<Pessoa> retorno = new ArrayList<>();
        retorno.addAll(pessoaFisicaFacade.listaTodos());
        retorno.addAll(pessoaJuridicaFacade.listaTodos());
        return retorno;
    }

}
