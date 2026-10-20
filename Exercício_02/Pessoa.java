/* ADS 471 - Fernanda Cristina Oliveira Pinheiro e Letícia Amaral Xavier

   Exercício 2 - Classe Pessoa
*/

public class Pessoa {

    private static int kp = 0;

    private int numero;
    private String nome;
    private char sexo;
    private int idade;

    public Pessoa() {
        numero = 0;
    }

    public Pessoa(String nome, char sexo, int idade) {
        setKp();
        setNome(nome);
        setSexo(sexo);
        setIdade(idade);
    }

    public void setKp() {
        kp++;
        numero = kp;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSexo(char sexo) {
        this.sexo = Character.toUpperCase(sexo);
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getKp() {
        return kp;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public char getSexo() {
        return sexo;
    }

    public int getIdade() {
        return idade;
    }
}