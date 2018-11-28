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

    public Frota() {
        try {
            this.veiculos = getFrotaBD(BD_FROTA_BIN);
        } catch (IOException ex) {
            try {
                guardarFrotaBD(BD_FROTA_BIN);
            } catch (IOException ex1) {
                System.exit(1);
            }
        } catch (ClassNotFoundException ex) {
            System.exit(1);
        }
    }

    /////////////////////////////////////////////////REGISTAR VEICULO
    public boolean RegistaVeiculo(String nome, String matricula, int KmReais, int KmMensais, String seguradora, LocalDate dataRegistoSeguro, String tipoSeguro, TipoVeiculo tipo) {
        switch (tipo) {
            case LIGEIRO:
                veiculos.add(new Ligeiro(nome, matricula, KmReais, KmMensais, seguradora, dataRegistoSeguro, tipoSeguro));
                return true;
            case MOTOCICLO:
                veiculos.add(new Ligeiro(nome, matricula, KmReais, KmMensais, seguradora, dataRegistoSeguro, tipoSeguro));
                return true;
            case PESADO:
                veiculos.add(new Pesado(nome, matricula, KmReais, KmMensais, seguradora, dataRegistoSeguro, tipoSeguro));
                return true;
            default:
                break;

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
        if (veiculos.size() == 0) s = "Sem veículos";
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
        
        for (Veiculo v : veiculos) {
            for (Evento e : v.eventos) {
                allEventos.add(e);
            }
        }
        return allEventos;
    }
    
    private void guardarFrotaBD(String nomeFicheiro) throws IOException {
        ObjectOutputStream oout = null;

        try {
            oout = new ObjectOutputStream(new FileOutputStream(nomeFicheiro));
            oout.writeObject(this);

        } finally {
            if (oout != null) {
                oout.close();
            }
        }
    }

    private List<Veiculo> getFrotaBD(String nomeFicheiro) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream oin = null;

        try {
            oin = new ObjectInputStream(new FileInputStream(nomeFicheiro));
            Frota f = (Frota) oin.readObject();
            return f.veiculos;
        } finally {
            if (oin != null) {
                oin.close();
            }
        }
    }

}
