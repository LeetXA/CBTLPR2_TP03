/* ADS 471 - Fernanda Cristina Oliveira Pinheiro e Letícia Amaral Xavier

   2. Crie a estrutura de classes indicada
*/

import javax.swing.*;
import java.awt.*;

public class Form extends JFrame {

    private Pessoa UmaPessoa;

    private JTextField txtNumero;
    private JTextField txtNome;
    private JTextField txtSexo;
    private JTextField txtIdade;

    private JButton btnOK;
    private JButton btnMostrar;

    public Form() {

        setTitle("Cadastro de Pessoa - Versão 1");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Cria o primeiro objeto sem incrementar o contador
        UmaPessoa = new Pessoa();

        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));
        painel.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        // Número
        painel.add(new JLabel("Número:"));

        txtNumero = new JTextField();
        txtNumero.setEditable(false);
        txtNumero.setText("");
        painel.add(txtNumero);

        // Nome
        painel.add(new JLabel("Nome:"));

        txtNome = new JTextField();
        painel.add(txtNome);

        // Sexo
        painel.add(new JLabel("Sexo:"));

        txtSexo = new JTextField();
        painel.add(txtSexo);

        // Idade
        painel.add(new JLabel("Idade:"));

        txtIdade = new JTextField();
        painel.add(txtIdade);

        // Botões
        btnOK = new JButton("OK");
        btnMostrar = new JButton("Mostrar");

        painel.add(btnOK);
        painel.add(btnMostrar);

        add(painel);

        // Evento do botão OK
        btnOK.addActionListener(e -> cadastrarPessoa());

        // Evento do botão Mostrar
        btnMostrar.addActionListener(e -> mostrarPessoa());
    }

    private void cadastrarPessoa() {

        try {

            String nome = txtNome.getText().trim();
            String sexoTexto = txtSexo.getText().trim().toUpperCase();
            String idadeTexto = txtIdade.getText().trim();

            // Validar campos obrigatórios
            if (nome.isEmpty() || sexoTexto.isEmpty() || idadeTexto.isEmpty()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os campos obrigatórios."
                );

                return;
            }

            // Validar sexo
            if (!sexoTexto.equals("M") && !sexoTexto.equals("F")) {

                JOptionPane.showMessageDialog(
                    this,
                    "O sexo deve ser M ou F."
                );

                return;
            }

            // Validar idade
            int idade = Integer.parseInt(idadeTexto);

            if (idade < 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "A idade não pode ser negativa."
                );

                return;
            }

            // Criar um novo objeto para cada cadastro válido
            UmaPessoa = new Pessoa(nome, sexoTexto.charAt(0), idade);

            // Atualizar o número da pessoa
            txtNumero.setText(String.valueOf(UmaPessoa.getNumero()));

            JOptionPane.showMessageDialog(
                this,
                "Dados cadastrados com sucesso!"
            );

        } catch (NumberFormatException erro) {

            JOptionPane.showMessageDialog(
                this,
                "Digite uma idade válida."
            );

        } finally {
            // Finalização do processamento.
        }
    }

    private void mostrarPessoa() {

        // Verificar se existe uma pessoa cadastrada
        if (UmaPessoa == null || UmaPessoa.getNumero() == 0) {

            JOptionPane.showMessageDialog(
                this,
                "Cadastre uma pessoa primeiro."
            );

            return;
        }

        JOptionPane.showMessageDialog(
            this,
            "Número: " + UmaPessoa.getNumero()
            + "\nNome: " + UmaPessoa.getNome()
            + "\nSexo: " + UmaPessoa.getSexo()
            + "\nIdade: " + UmaPessoa.getIdade()
            + "\nTotal de pessoas: " + UmaPessoa.getKp()
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Form formulario = new Form();
            formulario.setVisible(true);
        });
    }
}