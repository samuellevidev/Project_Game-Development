
//Classe abstrata (Abstrata: não pode herdar  nem ser instânciada) progenitora (Progenitora: que será herdada por outras classes):
public abstract class Personagem {

    //Atributos Principais:
    private String nome; 
    private int nivel;
    private double pontos_de_vida;
    private double pontos_de_ataque;
    private double pontos_de_defesa;

    /*Funções e Métodos abstratos:
    * Ou seja, eles sofreram o polimorfismo de 'SOBREPOSIÇÃO';
    * Os parametros 'x', 'y' e 'z', são a assinatura desses métodos para facilitar a Sobreposição;
    * Os 'Setters' recebem um valor e atribuem a sua variável;
    * Os 'Getters' retornam a variável.
    */
    //Variável Nome:
    public void setNome(String name) {
        this.nome = name;
    }
    public String getNome() {
        return nome;
    }

    //Variável Nivel:
    public void setNivel(int lv, int sala_exp, int numerador){
        lv = lv + (sala_exp/numerador);
        this.nivel = lv;
    }
    public int getNivel() {
        return nivel;
    }

    //Variável Pontos_de_vida:
    public void setPontos_De_Vida(double pv, int sala_exp, int numerador) {
        pv = pv + (sala_exp/numerador);
        this.pontos_de_vida = pv;
    }
    public void setPontos_De_Vida(double pv_atual) {
        this.pontos_de_vida = pv_atual;
    }
    public double getPontos_De_Vida() {
        return pontos_de_vida;
    }

    //Variável Pontos_de_Ataque
    public void setPontos_de_Ataque(double atk, int sala_exp, int numerador) {
        atk = atk + (sala_exp/numerador);
        this.pontos_de_ataque = atk;
    }
    public double getPontos_de_Ataque() {
        return pontos_de_ataque;
    }

    //Variável Pontos_de_Defesa:
    public void setPontos_de_Defesa(double def, int sala_exp, int numerador) {
        def =  def + (sala_exp/numerador);
        this.pontos_de_defesa = def;
    }
    public double getPontos_de_Defesa() {
        return pontos_de_defesa ;
    }

    //Método responsável pela ação de ataque:
    public abstract double Atacar(double x, double y, double z);
    
}
