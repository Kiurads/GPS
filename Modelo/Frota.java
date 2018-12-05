package GPS.Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Frota implements Constantes, Serializable {

    List<Veiculo> veiculos = new ArrayList<>();

    public Frota() throws IOException {

//        Veiculo v = new Ligeiro("21-45-RD", 100000, 100, "Liberty", LocalDate.now(), 30);
//        Veiculo v2 = new Motociclo("34-98-BG", 260000, 560, "Alianz", LocalDate.now(), 160);
//        veiculos.add(v);
//        veiculos.add(v2);
//
//        guardarFrotaBD();
        
        try {
            this.veiculos = getFrotaBD();

        } catch (IOException ex) {
             System.exit(1);
        } catch (ClassNotFoundException ex) {
            System.exit(1);
        }
    }

    /////////////////////////////////////////////////REGISTAR VEICULO
    public boolean registaVeiculo(String matricula, int kmReais, int kmMensais, String seguradora, LocalDate dataRegistoSeguro, double custoAnualSeguro, TipoVeiculo tipo) {

        switch (tipo) {
            case LIGEIRO:
                veiculos.add(new Ligeiro(matricula, kmReais, kmMensais, seguradora, dataRegistoSeguro, custoAnualSeguro));
                return true;
            case MOTOCICLO:
                veiculos.add(new Motociclo(matricula, kmReais, kmMensais, seguradora, dataRegistoSeguro, custoAnualSeguro));
                return true;
            case PESADO:
                veiculos.add(new Pesado(matricula, kmReais, kmMensais, seguradora, dataRegistoSeguro, custoAnualSeguro));
                return true;
            default:
                break;

        }
        return false;
    }

    /////////////////////////////////////////////////ENIMINAR VEICULO
    public boolean eliminaVeiculo(String matricula) {
        Veiculo veiculo = pesquisaVeiculo(matricula);

        if (veiculo != null) {
            veiculos.remove(veiculo);
            return true;
        }
        return false;
    }

    /////////////////////////////////////////////////lISTAR VEICULOS
    @Override
    public String toString() {
        String s = "";
        if (veiculos.size() == 0) s = "Sem veículos";
        for (Veiculo v : veiculos) {
            s += v.toString() + "\n\n";
        }
        return s;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////REALIZAR
    public boolean realizaMudancaOleo(String matricula, int custo) {
        if (custo <= 0 || matricula.isEmpty()) {
            return false;
        }

        Veiculo veiculo = pesquisaVeiculo(matricula);
        if (veiculo == null) {
            return false;
        }

        return veiculo.realizaMudancaOleo(custo);
    }

    public boolean realizaPagamentoSeguro(String matricula) {
        if (matricula.isEmpty()) {
            return false;
        }

        Veiculo veiculo = pesquisaVeiculo(matricula);
        if (veiculo == null) {
            return false;
        }

        return veiculo.realizaPagamentoSeguro();

    }

    public boolean realizaMudancaDeCorreia(String matricula, int custo) {
        if (custo <= 0 || matricula.isEmpty()) {
            return false;
        }

        Veiculo veiculo = pesquisaVeiculo(matricula);
        if (veiculo == null) {
            return false;
        }

        return veiculo.realizaMudancaDeCorreia(custo);

    }

    public boolean realizaPagamentoImpostoCirculacao(String matricula, int custo) {
        if (custo <= 0 || matricula.isEmpty()) {
            return false;
        }

        Veiculo veiculo = pesquisaVeiculo(matricula);
        if (veiculo == null) {
            return false;
        }

        return veiculo.realizaPagamentoImpostoCirculacao(custo);

    }

    public boolean RealizaInspecao(String matricula, int custo) {
        if (custo <= 0 || matricula.isEmpty()) {
            return false;
        }

        Veiculo veiculo = pesquisaVeiculo(matricula);
        if (veiculo == null) {
            return false;
        }

        return veiculo.realizaInspecao(custo);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////FUNCOES AUXILIARES
    public Veiculo pesquisaVeiculo(String valor) {
        for (Veiculo v : veiculos) {
            if (v.matricula.equals(valor) || v.nome.equals(valor)) {
                return v;
            }
        }
        return null;
    }

    public List<Evento> getEventosTotal() {
        List<Evento> allEventos = new ArrayList<>();

        for (Veiculo veiculo : veiculos) {
            for (Evento evento : veiculo.eventos) {
                allEventos.add(evento);
            }
        }
        return allEventos;
    }

    private void guardarFrotaBD() throws IOException {
        ObjectOutputStream oout = null;
        try {
            oout = new ObjectOutputStream(new FileOutputStream(BD_FROTA_BIN));
            oout.writeObject(this);

        } finally {
            if (oout != null) {
                oout.close();
            }
        }
    }

    private List<Veiculo> getFrotaBD() throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream oin = null;

        try {
            oin = new ObjectInputStream(new FileInputStream(BD_FROTA_BIN));
            Frota frota = (Frota) oin.readObject();
            return frota.veiculos;
        } finally {
            if (oin != null) {
                oin.close();
            }
        }
    }

    
}
