/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPS.Modelo;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Márcio Guia
 */
abstract public class Veiculo {

    /**
     * @param args the command line arguments
     */
    String matricula;
    int KmReais;
    int KmMensais;
    Seguro seguro;
    List<Evento> eventos;

    int intervaloKmsOleo;
    Calendar dataRegistoMatricula;

    public Veiculo(String matricula, int KmReais, int KmMensais, String seguradora, Date dataRegistoSeguro) {
        this.matricula = matricula;
        this.KmReais = KmReais;
        this.KmMensais = KmMensais;
        this.seguro = new Seguro(seguradora, dataRegistoSeguro);
        this.eventos = new ArrayList<>();

        //buscar dados da BD
        dataRegistoMatricula = Calendar.getInstance();
    }

    // gets

    public Calendar getDataRegistoMatricula() {
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

    public void setDataRegistoMatricula(Calendar dataRegistoMatricula) {
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
    public Date CalculaProximaPagementoImpostoCirculaçao(String descricao) {
        
        Calendar novaData = Calendar.getInstance();
        novaData.setTime(new Date(dataRegistoMatricula.get(Calendar.YEAR),dataRegistoMatricula.get(Calendar.MONTH),dataRegistoMatricula.get(Calendar.DAY_OF_MONTH)));
        
        eventos.add(new Evento(novaData, descricao, matricula, Biblioteca.TipoEvento.Reparacao));
        
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
        System.out.println("por definir");
    }

    public void RealizaPagamentoSeguro() {
        System.out.println("por definir");
    }

    public void RealizaMudancaDeCorreia() {
        System.out.println("por definir");
    }

    public void RealizaPagamentoImpostoCirculacao() {
        System.out.println("por definir");
    }

    public void RealizaInspecao() {
        System.out.println("por definir");
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
