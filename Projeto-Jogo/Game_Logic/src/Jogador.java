
public class Jogador extends Personagem{
    //Atributos proprios da classe Jogador:
    private String escolha; //Escolha que será fornecida pelo jogador.
    private int pontos_experiencia; //Valor que molda o nivel do jogador.
    private int sala;//Variável responsável por identificar a sala em que o jogador está.
    
    /*Funções e Métodos:
    * Getters: Retorna o valor da variável
    * Setters: Atribui um valor a variável.
    */
    //Variável Escolha:
    public String getEscolha() {
        return escolha;
    }
    public void setEscolha(String escolha) {
        this.escolha = escolha;
    }

    //Variável Pontos de experiência:
    public void setPontos_Experiencia(boolean result, int lv_inimigo) {
        if (result == true) 
            this.pontos_experiencia += (lv_inimigo*2);
        else if (result == false && this.pontos_experiencia > 0)
            this.pontos_experiencia -= (lv_inimigo*2);
        else 
            this.pontos_experiencia += 0;
    }
    public int getPontos_Experiencia() {
        return pontos_experiencia;
    }

    //Variável sala:
    public int getSala() {
        return sala;
    }
    public void setSala(boolean decisao) {
        if (decisao == true) {
            this.sala += 1;
        }
        else if (decisao == false) {
            this.sala -= 1;
        }
    }

    //FUNÇÕES POLIMORFADAS COM SOBREPOSIÇÃO DA CLASSE PERSONAGEM: 

    //Função que retorna o dano causado ao inimigo pelo jogador:
    @Override
    public double Atacar(double atk_jogador, double pv_inimigo, double def_inimigo){
        if (pv_inimigo < (pv_inimigo - (atk_jogador - (def_inimigo/4) )))
            return pv_inimigo;
        else
            return (pv_inimigo - (atk_jogador - (def_inimigo/4)));
    }


    //Função que retorna o valor curado pelo jogador:
    public double Curar(double pv_jogador, double cura, int lv) {
        /*
         * Estrutura de repetição que identifica o quanto de vida o jogador tem e retorna cura realizada (Cura total):
         */
        if (pv_jogador <= (15 + (3*(lv/4)))){
            for (double pv = pv_jogador; pv <= (15 + (3*(lv/4))); pv += cura) {
                pv_jogador += cura; 
            }
            return pv_jogador;
        }

        else {
            return pv_jogador;
        }
    }

    //Função que atribui os valores dos atributos do jogador:
    public void setJogador() {
        setNivel(1, getPontos_Experiencia(), 4);
        setPontos_De_Vida(15, 3*(getNivel()), 4);
        setPontos_de_Ataque(9, getNivel(), 3);
        setPontos_de_Defesa(5, getNivel(), 2);
    }

}
