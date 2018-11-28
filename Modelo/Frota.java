package GPS.Modelo;

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
//        guardarFrotaBD(BD_FROTA_BIN);
        try {
            this.veiculos = getFrotaBD();

        } catch (IOException ex) {
             System.exit(1);
        } catch (ClassNotFoundException ex) {
            System.exit(1);
        }

    }

    /////////////////////////////////////////////////REGISTAR VEICULO
    public boolean RegistaVeiculo(String matricula, int KmReais, int KmMensais, String seguradora, LocalDate dataRegistoSeguro, double custoAnualSeguro, TipoVeiculo tipo) {

        switch (tipo) {
            case LIGEIRO:
                veiculos.add(new Ligeiro(matricula, KmReais, KmMensais, seguradora, dataRegistoSeguro, custoAnualSeguro));
                return true;
            case MOTOCICLO:
                veiculos.add(new Motociclo(matricula, KmReais, KmMensais, seguradora, dataRegistoSeguro, custoAnualSeguro));
                return true;
            case PESADO:
                veiculos.add(new Pesado(matricula, KmReais, KmMensais, seguradora, dataRegistoSeguro, custoAnualSeguro));
                return true;

        }
        return false;
    }

    /////////////////////////////////////////////////ENIMINAR VEICULO
    public boolean EleminaVeiculo(String Matricula) {
        Veiculo v = pesquisaVeiculo(Matricula);

        if (v != null) {
            veiculos.remove(v);
            return true;
        }
        return false;
    }

    /////////////////////////////////////////////////lISTAR VEICULOS
    @Override
    public String toString() {
        String s = "";
        for (Veiculo v : veiculos) {
            s += v.toString() + "\n\n";
        }
        return s;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////REALIZAR
    public boolean RealizaMudancaOleo(String matricula, int custo) {
        if (custo <= 0 || matricula.isEmpty()) {
            return false;
        }

        Veiculo veiculo = pesquisaVeiculo(matricula);
        if (veiculo == null) {
            return false;
        }

        return veiculo.RealizaMudancaOleo(custo);
    }

    public boolean RealizaPagamentoSeguro(String matricula) {
        if (matricula.isEmpty()) {
            return false;
        }

        Veiculo veiculo = pesquisaVeiculo(matricula);
        if (veiculo == null) {
            return false;
        }

        return veiculo.RealizaPagamentoSeguro();

    }

    public boolean RealizaMudancaDeCorreia(String matricula, int custo) {
        if (custo <= 0 || matricula.isEmpty()) {
            return false;
        }

        Veiculo veiculo = pesquisaVeiculo(matricula);
        if (veiculo == null) {
            return false;
        }

        return veiculo.RealizaMudancaDeCorreia(custo);

    }

    public boolean RealizaPagamentoImpostoCirculacao(String matricula, int custo) {
        if (custo <= 0 || matricula.isEmpty()) {
            return false;
        }

        Veiculo veiculo = pesquisaVeiculo(matricula);
        if (veiculo == null) {
            return false;
        }

        return veiculo.RealizaPagamentoImpostoCirculacao(custo);

    }

    public boolean RealizaInspecao(String matricula, int custo) {
        if (custo <= 0 || matricula.isEmpty()) {
            return false;
        }

        Veiculo veiculo = pesquisaVeiculo(matricula);
        if (veiculo == null) {
            return false;
        }

        return veiculo.RealizaInspecao(custo);

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////FUNCOES AUXILIARES
    public Veiculo pesquisaVeiculo(String matricula) {
        for (Veiculo v : veiculos) {
            if (v.matricula.equals(matricula)) {
                return v;
            }
        }
        return null;
    }

    public List<Evento> getEventosTotal() {
        List<Evento> allEventos = new ArrayList<>();

        for (Veiculo v : veiculos) {
            for (Evento e : v.eventos) {
                allEventos.add(e);
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
            Frota f = (Frota) oin.readObject();
            return f.veiculos;
        } finally {
            if (oin != null) {
                oin.close();
            }
        }
    }
}
