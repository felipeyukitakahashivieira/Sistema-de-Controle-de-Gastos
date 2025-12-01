package inicio;

import javax.swing.*;
import funcionalidades.Gastos;
import funcionalidades.ListarGastos;
import java.awt.Font;

public class Inicio extends JFrame {

    private JLabel bemVindoUser;
    private JPanel inicioPainel;

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuArquivo = new JMenu("Arquivo");
    private JMenuItem novoGasto = new JMenuItem("Novo Gasto");
    private JMenuItem listarGastos = new JMenuItem("Listar Gastos");
    private JMenuItem salvarArquivo = new JMenuItem("Salvar");
    private JMenu menuSair = new JMenu("Sair");

    public Inicio(String nome) {

        setTitle("Início");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);

        inicioPainel = new JPanel();
        inicioPainel.setLayout(null);
        this.setContentPane(inicioPainel);

        menuArquivo.add(novoGasto);
        menuArquivo.add(listarGastos);
        menuArquivo.addSeparator();
        menuArquivo.add(salvarArquivo);

        menuBar.add(menuArquivo);
        menuBar.add(menuSair);

        setJMenuBar(menuBar);

        initComponents(nome);
        addListeners();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents(String nome) {
        bemVindoUser = new JLabel("Bem vindo " + nome);
        bemVindoUser.setFont(new Font("Arial", Font.BOLD, 18));
        bemVindoUser.setBounds(20, 20, 400, 30);
        inicioPainel.add(bemVindoUser);
    }

    private void addListeners() {

        novoGasto.addActionListener(e -> {
            new Gastos();
        });

        listarGastos.addActionListener(e -> {
            new ListarGastos();
        });

        menuSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.exit(0);
            }
        });
    }
}