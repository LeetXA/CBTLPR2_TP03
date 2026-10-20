/* ADS 471 - Fernanda Cristina Oliveira Pinheiro e Letícia Amaral Xavier

1. Construir o Form e possibilitar o cálculo das operações de divisão, multiplicação, subtração e adição.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame implements ActionListener {
    private JTextField display;
    private double valor1 = 0;
    private double valor2 = 0;
    private String operacao = "";
    private boolean novaEntrada = true;

    public Calculadora() {
        setTitle("Calcul...");
        setSize(260, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        // Campo de texto de resultado
        display = new JTextField("0");
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 20));
        add(display, BorderLayout.NORTH);

        // Painel de botões numéricos e operadores
        JPanel panelBotoes = new JPanel(new GridLayout(4, 4, 4, 4));
        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        for (String texto : botoes) {
            JButton btn = new JButton(texto);
            btn.setFont(new Font("Arial", Font.PLAIN, 16));
            btn.addActionListener(this);
            panelBotoes.add(btn);
        }

        add(panelBotoes, BorderLayout.CENTER);

        // Botão Clear (C)
        JButton btnClear = new JButton("C");
        btnClear.setFont(new Font("Arial", Font.BOLD, 16));
        btnClear.addActionListener(this);
        add(btnClear, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        try {
            if ("0123456789.".contains(comando)) {
                if (novaEntrada || display.getText().equals("0")) {
                    display.setText(comando.equals(".") ? "0." : comando);
                    novaEntrada = false;
                } else {
                    if (comando.equals(".") && display.getText().contains(".")) {
                        return;
                    }
                    display.setText(display.getText() + comando);
                }
            } else if ("+-*/".contains(comando)) {
                valor1 = Double.parseDouble(display.getText());
                operacao = comando;
                novaEntrada = true;
            } else if (comando.equals("=")) {
                if (!operacao.isEmpty()) {
                    valor2 = Double.parseDouble(display.getText());
                    double resultado = 0;

                    switch (operacao) {
                        case "+": resultado = valor1 + valor2; break;
                        case "-": resultado = valor1 - valor2; break;
                        case "*": resultado = valor1 * valor2; break;
                        case "/":
                            if (valor2 == 0) {
                                throw new ArithmeticException("Erro: Divisão por 0");
                            }
                            resultado = valor1 / valor2;
                            break;
                    }
                    display.setText(String.valueOf(resultado));
                    operacao = "";
                    novaEntrada = true;
                }
            } else if (comando.equals("C")) {
                limparMemoria();
            }
        } catch (NumberFormatException ex) {
            display.setText("Erro de Formato");
            novaEntrada = true;
        } catch (ArithmeticException ex) {
            display.setText(ex.getMessage());
            novaEntrada = true;
        } catch (Exception ex) {
            display.setText("Erro Inesperado");
            novaEntrada = true;
        } finally {
            // Bloco de libertação de recursos / finalização do processamento
        }
    }

    private void limparMemoria() {
        valor1 = 0;
        valor2 = 0;
        operacao = "";
        display.setText("0");
        novaEntrada = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculadora().setVisible(true));
    }
}
