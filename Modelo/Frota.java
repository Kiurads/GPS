package GPS.Modelo;

import java.io.*;
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
    public void RegistaVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
        try {
            guardarFrotaBD(BD_FROTA_BIN);
        } catch (IOException ignore) {}
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

    public List<String> getNomesVeiculos() {
        List<String> lista = new ArrayList<>();

        for(Veiculo veiculo : veiculos) {
            lista.add(veiculo.nome);
        }

        return lista;
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
    
    public void guardarFrotaBD(String nomeFicheiro) throws IOException {

        try (ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(nomeFicheiro))) {
            oout.writeObject(this);
        }
    }

    private List<Veiculo> getFrotaBD(String nomeFicheiro) throws FileNotFoundException, IOException, ClassNotFoundException {

        try (ObjectInputStream oin = new ObjectInputStream(new FileInputStream(nomeFicheiro))) {
            Frota f = (Frota) oin.readObject();
            return f.veiculos;
        }
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
}
