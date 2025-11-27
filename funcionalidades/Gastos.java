package funcionalidades;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

public class Gastos extends JFrame {

    private JPanel painel;
    private JTextField campoData;
    private JTextField campoDescricao;
    private JTextField campoCategoria;
    private JTextField campoValor;
    private JButton salvarButton;
    private JButton cancelarButton;

    private JLabel labelData;
    private JLabel labelDescricao;
    private JLabel labelCategoria;
    private JLabel labelValor;

    public Gastos() {
        setTitle("Cadastrar Gasto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        painel = new JPanel();
        painel.setLayout(null);
        this.setContentPane(painel);
        this.setSize(500, 350);

        initComponents();

        addListeners();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        int x = 50;
        int y = 30;
        int wLabel = 100;
        int wField = 300;
        int h = 25;
        int yEspaco = 40;

        labelData = new JLabel("Data:");
        labelData.setBounds(x, y, wLabel, h);
        painel.add(labelData);
        campoData = new JTextField();
        campoData.setBounds(x + wLabel, y, wField, h);
        painel.add(campoData);

        y += yEspaco;
        labelDescricao = new JLabel("Descrição:");
        labelDescricao.setBounds(x, y, wLabel, h);
        painel.add(labelDescricao);
        campoDescricao = new JTextField();
        campoDescricao.setBounds(x + wLabel, y, wField, h);
        painel.add(campoDescricao);

        y += yEspaco;
        labelCategoria = new JLabel("Categoria:");
        labelCategoria.setBounds(x, y, wLabel, h);
        painel.add(labelCategoria);
        campoCategoria = new JTextField();
        campoCategoria.setBounds(x + wLabel, y, wField, h);
        painel.add(campoCategoria);

        y += yEspaco;
        labelValor = new JLabel("Valor:");
        labelValor.setBounds(x, y, wLabel, h);
        painel.add(labelValor);
        campoValor = new JTextField();
        campoValor.setBounds(x + wLabel, y, wField, h);
        painel.add(campoValor);

        y += yEspaco * 2;
        salvarButton = new JButton("Salvar");
        salvarButton.setBounds(x, y, 200, 30);
        painel.add(salvarButton);

        cancelarButton = new JButton("Cancelar");
        cancelarButton.setBounds(x + 250, y, 200, 30);
        painel.add(cancelarButton);
    }

    private void addListeners() {
        salvarButton.addActionListener(e -> {
            String data = campoData.getText();
            String descricao = campoDescricao.getText();
            String categoria = campoCategoria.getText();
            String valor = campoValor.getText();

            if (data.trim().isEmpty() || valor.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha a Data e o Valor.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (FileWriter fw = new FileWriter("gastos.txt", true)) {
                fw.write(data + ";" + descricao + ";" + categoria + ";" + valor + "\n");
                JOptionPane.showMessageDialog(null, "Gasto salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar o gasto!", "Erro de I/O", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarButton.addActionListener(e -> dispose());
    }
}