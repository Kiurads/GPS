package GPS.Modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class Frota implements Constantes, Serializable {

    List<Veiculo> veiculos;

    public Frota() throws IOException, FileNotFoundException, ClassNotFoundException {
        this.veiculos = getFrotaBD(BD_FROTA_BIN);
    }

    /////////////////////////////////////////////////REGISTAR VEICULO
    /////////////////////////////////////////////////ENIMINAR VEICULO
    /////////////////////////////////////////////////lISTAR VEICULOS
    @Override
    public String toString() {
        String s = "";
        for (Veiculo v : veiculos) {
            s += v.toString() + "\n\n";
        }
        return s;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////FUNCOES AUXILIARES
    private Veiculo pesquisaVeiculo(String matricula) {
        for (Veiculo v : veiculos) {
            if (v.matricula.equals(matricula)) {
                return v;
            }
        }
        return null;
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
