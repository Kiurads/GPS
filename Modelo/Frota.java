package GPS.Modelo;

import javafx.scene.control.DatePicker;

import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Frota implements Constantes, Serializable {
    List<Veiculo> veiculos = new ArrayList<>();

    public Frota() throws IOException {
        try {
            this.veiculos = getFrotaBD();
        } catch (IOException ex) {
             System.exit(1);
        } catch (ClassNotFoundException ex) {
            System.exit(1);
        }
    }



    private void preencheDados(String matricula, TextField modelo, TextField tipo, DatePicker registo) {
        try (BufferedReader br = new BufferedReader(new FileReader(BD_MATRICULAS_TXT))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Scanner sc = new Scanner(linha);
                String matriculaLida = sc.next();
                if (matriculaLida.equals(matricula)) {
                    registo.setValue(LocalDate.of(sc.nextInt(), sc.nextInt(), sc.nextInt()));
                    modelo.setText(sc.next() + sc.next());
                    tipo.setText(sc.next());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean getExists(String matricula) {
        try (BufferedReader br = new BufferedReader(new FileReader(BD_MATRICULAS_TXT))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Scanner sc = new Scanner(linha);
                String matriculaLida = sc.next();
                if (matriculaLida.equals(matricula)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /////////////////////////////////////////////////REGISTAR VEICULO
    public void RegistaVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
        try {
            guardarFrotaBD();
        } catch (IOException ignore) {}
    }

    /////////////////////////////////////////////////ENIMINAR VEICULO
    public void eliminaVeiculo(String matricula) {
        Veiculo veiculo = pesquisaVeiculo(matricula);

        if (veiculo != null) {
            veiculos.remove(veiculo);
        }
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

    public List<String> getNomesVeiculos() {
        List<String> lista = new ArrayList<>();

        for(Veiculo veiculo : veiculos) {
            lista.add(veiculo.nome);
        }

        return lista;
    }
    
    public List<Evento> getEventosTotal() {
        List<Evento> allEventos = new ArrayList<>();

        for (Veiculo veiculo : veiculos) {
            allEventos.addAll(veiculo.eventos);
        }
        return allEventos;
    }
    
    public void guardarFrotaBD() throws IOException {

        try (ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(BD_FROTA_BIN))) {
            oout.writeObject(this);
        }
    }

    private List<Veiculo> getFrotaBD() throws IOException, ClassNotFoundException {
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
