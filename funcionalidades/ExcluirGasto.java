package funcionalidades;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcluirGasto {

    private int i;

    public ExcluirGasto(int index) {
        this.i = i;
        excluir();
    }

    private void excluir() {

        try {
            File arquivo = new File("gastos.txt");

            List<String> novasLinhas = new ArrayList<>();
            int linhaAtual = 0;

            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                String linha;

                while ((linha = br.readLine()) != null) {
                    if (linhaAtual != i) {
                        novasLinhas.add(linha);
                    }
                    linhaAtual++;
                }
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
                for (String l : novasLinhas) {
                    bw.write(l);
                    bw.newLine();
                }
            }

            JOptionPane.showMessageDialog(null,
                    "Gasto excluído com sucesso!",
                    "Confirmação",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao excluir gasto: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
