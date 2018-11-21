/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPS.Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Márcio Guia
 */
abstract public class Veiculo implements Biblioteca{

    /**
     * @param args the command line arguments
     */
    String matricula;
    Date dataRegistoMatricula;
    int KmReais;
    int KmMensais;
    Seguro seguro;
    List<Evento> eventos;

    int intervaloKmsOleo;

    public Veiculo(String matricula, Date dataRegistoMatricula, int KmReais, int KmMensais, String seguradora, Date dataRegistoSeguro) {
        this.matricula = matricula;
        this.dataRegistoMatricula = dataRegistoMatricula;
        this.KmReais = KmReais;
        this.KmMensais = KmMensais;
        this.seguro = new Seguro(seguradora, dataRegistoSeguro);
        this.eventos = new ArrayList<>();
    }

    // gets
    public Date getDataRegistoMatricula() {
        return dataRegistoMatricula;
    }
    public int getKmReais() {
        return KmReais;
    }
    public int getKmMensais() {
        return KmMensais;
    }
    public String getMatricula() {
        return matricula;
    }
    public Seguro getSeguro() {
        return seguro;
    }

    //sets
    public void setDataRegistoMatricula(Date dataRegistoMatricula) {
        this.dataRegistoMatricula = dataRegistoMatricula;
    }
    public void setKmReais(int KmReais) {
        this.KmReais = KmReais;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    
    // Calcular
    public Date CalculaProximaPagementoImpostoCirculaçao() {
        return null;
    }
    public Date CalcularProximaDataDePagamentoSeguro() {
        return null;
    }
    public Date CalcularProximaMudancaOleo() {
        return null;
    }
    public Date CalcularProximaMudancaDeCorreia() {
        return null;
    }
    abstract public Date CalculaProximaInspecao();

    
    // Realizar
    public void RealizaMudancaOleo() {
        Evento aux;
        if((aux=PesquisaEvento(MUDANCA_OLEO))!=null){
            if(!aux.isCheack()){
                aux.setCheack(true);
                Evento novo=new Evento(dataRegistoMatricula, MUDANCA_OLEO, matricula, TipoEvento.Reparacao);
                CriaEvento(novo);
            }
        }
    }
    
    public void RealizaPagamentoSeguro() {
       Evento aux;
        if((aux=PesquisaEvento(PAGAMENTO_SEGURO))!=null){
            if(!aux.isCheack()){
                aux.setCheack(true);
                 Evento novo=new Evento(dataRegistoMatricula, PAGAMENTO_SEGURO, matricula, TipoEvento.Reparacao);
                CriaEvento(novo);
            }
        }
    }
    public void RealizaMudancaDeCorreia() {
         Evento aux;
        if((aux=PesquisaEvento(MUDANCA_CORREIA))!=null){
            if(!aux.isCheack()){
                aux.setCheack(true);
                 Evento novo=new Evento(dataRegistoMatricula, MUDANCA_CORREIA, matricula, TipoEvento.Reparacao);
                CriaEvento(novo);
            }
        }
    }
    public void RealizaPagamentoImpostoCirculacao() {
         Evento aux;
        if((aux=PesquisaEvento(PAGEMENTO_IMPOSTO_CIRCULA))!=null){
            if(!aux.isCheack()){
                aux.setCheack(true);
                 Evento novo=new Evento(dataRegistoMatricula, INSPECAO, matricula, TipoEvento.Reparacao);
                CriaEvento(novo);
            }
        }
    }
    public void RealizaInspecao() {
        Evento aux;
        if((aux=PesquisaEvento(INSPECAO))!=null){
            if(!aux.isCheack()){
                aux.setCheack(true);
                 Evento novo=new Evento(dataRegistoMatricula, INSPECAO, matricula, TipoEvento.Reparacao);
                CriaEvento(novo);
            }
        }
    }

    
    public Evento PesquisaEvento(String Nome){
        for (Evento evento : eventos) {
            if(evento.getNome().compareTo(Nome)==0)
                return evento;
        }
        return null;
    }
    
    public List<Evento> ListaEventos() {
        return eventos;
    }

    public void CriaEvento(Evento evento) {
        if (evento != null) {
            eventos.add(evento);
        }
    }
    
    
}
