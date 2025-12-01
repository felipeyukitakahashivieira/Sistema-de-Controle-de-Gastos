package funcionalidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListarGastos extends JFrame {

    private JPanel painel;
    private JTable tabela;
    private JScrollPane scrollPane;
    private JButton editarButton;
    private JButton excluirButton;
    private JButton fecharButton;
    private DefaultTableModel modeloTabela;

    public ListarGastos() {
        setTitle("Lista de Gastos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 400);

        painel = new JPanel();
        painel.setLayout(null);
        setContentPane(painel);

        initComponents();
        carregarGastos();
        addListeners();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        String[] colunas = {"Data", "Descrição", "Categoria", "Valor"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modeloTabela);

        scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(20, 20, 650, 280);
        painel.add(scrollPane);

        editarButton = new JButton("Editar");
        editarButton.setBounds(140, 320, 120, 30);
        painel.add(editarButton);

        excluirButton = new JButton("Excluir");
        excluirButton.setBounds(280, 320, 120, 30);
        painel.add(excluirButton);

        fecharButton = new JButton("Fechar");
        fecharButton.setBounds(420, 320, 120, 30);
        painel.add(fecharButton);
    }

    private void carregarGastos() {
        modeloTabela.setRowCount(0);

        try (BufferedReader br = new BufferedReader(new FileReader("gastos.txt"))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");

                if (partes.length >= 4) {
                    modeloTabela.addRow(new Object[]{
                            partes[0], partes[1], partes[2], partes[3]
                    });
                }
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao carregar gastos: " + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addListeners() {
        fecharButton.addActionListener(e -> dispose());

        editarButton.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();

            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(null,
                        "Selecione um gasto para editar!",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            String data = (String) modeloTabela.getValueAt(linhaSelecionada, 0);
            String descricao = (String) modeloTabela.getValueAt(linhaSelecionada, 1);
            String categoria = (String) modeloTabela.getValueAt(linhaSelecionada, 2);
            String valor = (String) modeloTabela.getValueAt(linhaSelecionada, 3);

            new EditarGastos(linhaSelecionada, data, descricao, categoria, valor);

            dispose();
        });

        excluirButton.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();

            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(null,
                        "Selecione um gasto para excluir!",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Deseja realmente excluir este gasto?",
                    "Confirmar exclusão",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                new ExcluirGasto(linhaSelecionada);
                dispose();
                new ListarGastos();
            }
        });
    }
}
