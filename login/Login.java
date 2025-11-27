package login;

import cadastrar.Cadastrar;
import inicio.Inicio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login extends JFrame {

    private JPanel painel;
    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JButton loginButton;
    private JButton cadastrarButton;
    private JLabel labelLogin;
    private JLabel labelSenha;
    private Cadastrar telaCadastrar;
    private Inicio telaInicio;

    public Login() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painel = new JPanel();
        painel.setLayout(null);
        this.setContentPane(painel);
        this.setSize(400, 300);

        initComponents();

        addListeners();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        labelLogin = new JLabel("E-mail:");
        labelLogin.setBounds(50, 50, 100, 25);
        painel.add(labelLogin);

        campoLogin = new JTextField();
        campoLogin.setBounds(150, 50, 200, 25);
        painel.add(campoLogin);

        labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(50, 90, 100, 25);
        painel.add(labelSenha);

        campoSenha = new JPasswordField();
        campoSenha.setBounds(150, 90, 200, 25);
        painel.add(campoSenha);

        loginButton = new JButton("Login");
        loginButton.setBounds(50, 150, 150, 30);
        painel.add(loginButton);

        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(200, 150, 150, 30);
        painel.add(cadastrarButton);
    }

    private void addListeners() {
        loginButton.addActionListener(e -> {
            String login = campoLogin.getText();
            String pass = String.valueOf(campoSenha.getPassword());

            boolean usuarioEncontrado = false;
            String nomeUsuario = "";

            try {
                try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
                    String linha;

                    while ((linha = br.readLine()) != null) {

                        String[] partes = linha.split(";");
                        String nomeArq = partes[0];
                        String emailArq = partes[1];
                        String senhaArq = partes[2];

                        if (login.equals(emailArq) && pass.equals(senhaArq)) {
                            usuarioEncontrado = true;
                            nomeUsuario = nomeArq;
                            break;
                        }
                    }
                }

                if (usuarioEncontrado) {
                    dispose();
                    telaInicio = new Inicio(nomeUsuario);
                } else {
                    JOptionPane.showMessageDialog(null, "Login ou senha incorretos!", "Erro de Login", JOptionPane.ERROR_MESSAGE);
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao ler arquivo de usuários. Certifique-se de que 'usuarios.txt' existe.", "Erro de Arquivo", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        cadastrarButton.addActionListener(e -> {
            telaCadastrar = new Cadastrar();
        });
    }
}