package funcionalidades;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EditarGastos extends JFrame {

    private JPanel painel;
    private JLabel lblData, lblDescricao, lblCategoria, lblValor;
    private JTextField txtData, txtDescricao, txtCategoria, txtValor;
    private JButton btnSalvar, btnCancelar;

    private int i;

    public EditarGastos(int i, String data, String descricao, String categoria, String valor) {
        this.i = i;

        setTitle("Editar Gasto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(420, 300);

        painel = new JPanel();
        painel.setLayout(null);
        setContentPane(painel);

        initComponents();
        
        txtData.setText(data);
        txtDescricao.setText(descricao);
        txtCategoria.setText(categoria);
        txtValor.setText(valor);

        addListeners();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        lblData = new JLabel("Data (dd/MM/yyyy):");
        lblData.setBounds(20, 20, 140, 25);
        painel.add(lblData);

        txtData = new JTextField();
        txtData.setBounds(170, 20, 220, 25);
        painel.add(txtData);

        lblDescricao = new JLabel("Descrição:");
        lblDescricao.setBounds(20, 60, 140, 25);
        painel.add(lblDescricao);

        txtDescricao = new JTextField();
        txtDescricao.setBounds(170, 60, 220, 25);
        painel.add(txtDescricao);

        lblCategoria = new JLabel("Categoria:");
        lblCategoria.setBounds(20, 100, 140, 25);
        painel.add(lblCategoria);

        txtCategoria = new JTextField();
        txtCategoria.setBounds(170, 100, 220, 25);
        painel.add(txtCategoria);

        lblValor = new JLabel("Valor:");
        lblValor.setBounds(20, 140, 140, 25);
        painel.add(lblValor);

        txtValor = new JTextField();
        txtValor.setBounds(170, 140, 220, 25);
        painel.add(txtValor);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(80, 200, 110, 30);
        painel.add(btnSalvar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(230, 200, 110, 30);
        painel.add(btnCancelar);
    }

    private void addListeners() {
        btnSalvar.addActionListener(e -> {
            String data = txtData.getText().trim();
            String descricao = txtDescricao.getText().trim();
            String categoria = txtCategoria.getText().trim();
            String valor = txtValor.getText().trim();

            if (data.isEmpty() || descricao.isEmpty() || categoria.isEmpty() || valor.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Preencha todos os campos.",
                        "Atenção",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            String linhaNova = data + ";" + descricao + ";" + categoria + ";" + valor;

            File arquivo = new File("gastos.txt");
            List<String> linhas = new ArrayList<>();

            try {
                if (arquivo.exists()) {
                    try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                        String linha;
                        while ((linha = br.readLine()) != null) {
                            linhas.add(linha);
                        }
                    }
                }

                if (i< 0 || i >= linhas.size()) {
                    JOptionPane.showMessageDialog(this,
                            "Índice inválido para edição. Operação abortada.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                linhas.set(i, linhaNova);

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
                    for (String l : linhas) {
                        bw.write(l);
                        bw.newLine();
                    }
                }

                JOptionPane.showMessageDialog(this,
                        "Gasto atualizado com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);

                dispose();
                new ListarGastos();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Erro ao salvar alterações: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dispose());
    }
}
