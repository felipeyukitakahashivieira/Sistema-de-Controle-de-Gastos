package cadastrar;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

public class Cadastrar extends JFrame {

    private JPanel cadastrarPainel;
    private JTextField campoNome;
    private JTextField campoEmail;
    private JPasswordField campoSenha;
    private JButton cadastrarButton;
    private JButton voltarButton;

    private JLabel labelNome;
    private JLabel labelEmail;
    private JLabel labelSenha;

    private String nome, email, senha;

    public Cadastrar() {
        setTitle("Cadastrar");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cadastrarPainel = new JPanel();
        cadastrarPainel.setLayout(null);
        this.setContentPane(cadastrarPainel);
        this.setSize(450, 350);

        initComponents();
        addListeners();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        labelNome = new JLabel("Nome:");
        labelNome.setBounds(50, 50, 100, 25);
        cadastrarPainel.add(labelNome);

        campoNome = new JTextField();
        campoNome.setBounds(150, 50, 250, 25);
        cadastrarPainel.add(campoNome);

        labelEmail = new JLabel("E-mail:");
        labelEmail.setBounds(50, 90, 100, 25);
        cadastrarPainel.add(labelEmail);

        campoEmail = new JTextField();
        campoEmail.setBounds(150, 90, 250, 25);
        cadastrarPainel.add(campoEmail);

        labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(50, 130, 100, 25);
        cadastrarPainel.add(labelSenha);

        campoSenha = new JPasswordField();
        campoSenha.setBounds(150, 130, 250, 25);
        cadastrarPainel.add(campoSenha);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(50, 200, 170, 30);
        cadastrarPainel.add(cadastrarButton);

        voltarButton = new JButton("Voltar");
        voltarButton.setBounds(230, 200, 170, 30);
        cadastrarPainel.add(voltarButton);
    }

    private void addListeners() {
        cadastrarButton.addActionListener(e -> {
            nome = campoNome.getText();
            email = campoEmail.getText();
            senha = String.valueOf(campoSenha.getPassword());

            if (nome.trim().isEmpty() || email.trim().isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                try (FileWriter writer = new FileWriter("usuarios.txt", true)) {
                    writer.write(nome + ";" + email + ";" + senha + "\n");
                }
                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                campoNome.setText("");
                campoEmail.setText("");
                campoSenha.setText("");

            } catch(IOException ex){
                JOptionPane.showMessageDialog(null, "Erro ao salvar usuário: " + ex.getMessage(), "Erro de I/O", JOptionPane.ERROR_MESSAGE);
            }
        });

        voltarButton.addActionListener(e -> {
            dispose();
        });
    }
}