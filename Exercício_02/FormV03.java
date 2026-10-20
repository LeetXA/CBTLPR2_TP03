/* ADS 471 - Fernanda Cristina Oliveira Pinheiro e Letícia Amaral Xavier

2. Versão 03 - Refaça o exercício anterior, porém agora usando um componente do tipo “JRadioButton”
*/

import javax.swing.*;
import java.awt.*;

public class FormV03 extends JFrame {
    private JTextField txtNumero;
    private JTextField txtNome;
    private JRadioButton rbMasculino;
    private JRadioButton rbFeminino;
    private ButtonGroup bgSexo;
    private JTextField txtIdade;

    private JButton btnOK;
    private JButton btnLimpar;
    private JButton btnMostrar;
    private JButton btnSair;

    // Objeto da classe Pessoa
    private Pessoa umaPessoa;

    public FormV03() {
        setTitle("Exercicio 02 - Versão 03");
        setSize(380, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel para os campos de entrada (Grid de 4 linhas x 2 colunas)
        JPanel panelCampos = new JPanel(new GridLayout(4, 2, 8, 8));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Campo 1: Número (Bloqueado para digitação)
        panelCampos.add(new JLabel("Número:"));
        txtNumero = new JTextField();
        txtNumero.setEditable(false); // Não acessível à digitação
        panelCampos.add(txtNumero);

        // Campo 2: Nome
        panelCampos.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panelCampos.add(txtNome);

        // Campo 3: Sexo (Versão 03 - JRadioButton)
        panelCampos.add(new JLabel("Sexo:"));
        JPanel panelSexo = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        rbMasculino = new JRadioButton("M");
        rbFeminino = new JRadioButton("F");
        bgSexo = new ButtonGroup(); // Garante que apenas um fique selecionado
        bgSexo.add(rbMasculino);
        bgSexo.add(rbFeminino);
        panelSexo.add(rbMasculino);
        panelSexo.add(rbFeminino);
        panelCampos.add(panelSexo);

        // Campo 4: Idade
        panelCampos.add(new JLabel("Idade:"));
        txtIdade = new JTextField();
        panelCampos.add(txtIdade);

        add(panelCampos, BorderLayout.CENTER);

        // Painel para os botões na parte inferior
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 10));
        btnOK = new JButton("OK");
        btnLimpar = new JButton("Limpar");
        btnMostrar = new JButton("Mostrar");
        btnSair = new JButton("Sair");

        panelBotoes.add(btnOK);
        panelBotoes.add(btnLimpar);
        panelBotoes.add(btnMostrar);
        panelBotoes.add(btnSair);

        add(panelBotoes, BorderLayout.SOUTH);

        // Ações dos Botões
        btnOK.addActionListener(e -> salvarDados());
        btnLimpar.addActionListener(e -> limparCampos());
        btnMostrar.addActionListener(e -> mostrarDados());
        btnSair.addActionListener(e -> System.exit(0));
    }

    private void salvarDados() {
        String nome = txtNome.getText().trim();
        String idadeStr = txtIdade.getText().trim();

        // Validação dos campos obrigatórios
        if (nome.isEmpty() || idadeStr.isEmpty() || (!rbMasculino.isSelected() && !rbFeminino.isSelected())) {
            JOptionPane.showMessageDialog(this,
                    "Os campos 'Nome', 'Sexo' e 'Idade' são de preenchimento obrigatório!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idade = Integer.parseInt(idadeStr);
            char sexo = rbMasculino.isSelected() ? 'M' : 'F';

            // Instancia o objeto UmaPessoa utilizando o seu construtor parametrizado
            umaPessoa = new Pessoa(nome, sexo, idade);

            // Atualiza o campo Número no formulário com o valor do objeto
            txtNumero.setText(String.valueOf(umaPessoa.getNumero()));

            JOptionPane.showMessageDialog(this,
                    "Dados cadastrados com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Digite um número inteiro válido para a idade.",
                    "Erro de Validação",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtIdade.setText("");
        txtNumero.setText("");
        bgSexo.clearSelection();
    }

    private void mostrarDados() {
        if (umaPessoa == null) {
            JOptionPane.showMessageDialog(this,
                    "Nenhum cadastro foi criado ainda!\nPreencha os dados e clique em OK.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String mensagem = String.format(
            "Número: %d\n" +
            "Nome: %s\n" +
            "Sexo: %c\n" +
            "Idade: %d\n" +
            "Total de pessoas: %d",
            umaPessoa.getNumero(),
            umaPessoa.getNome(),
            umaPessoa.getSexo(),
            umaPessoa.getIdade(),
            umaPessoa.getKp()
        );

        JOptionPane.showMessageDialog(this, mensagem, "Dados Descarregados", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormV03().setVisible(true));
    }
}