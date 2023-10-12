public class Inimigo extends Personagem {
    //Atributos: 
    private String inimigos [] = {"Esqueleto", "Slime", "Gárgula", "Planta Carnívora", "Fantasma", "Goblin"};
    private String chefoes [] = {"Serpente Solitária", "Ogro Raivoso", "Mercador Lunático", "Incubus Melancólico", "???"};
    private boolean boss;

    /*Funções e Métodos:
    * Getters: Retorna o valor da varíavel
    * Setters: Atribui um valor a varíavel.
    */
    //Variável Boss:
    public boolean getBoss() {
        return boss;
    }
    public void setBoss(boolean status_boss) {
        this.boss = status_boss;
    }

    //Variável Inimigos:
    public void setInimigos(int i) {
        this.inimigos[i] = inimigos[i];
    }
    public String getInimigos(int i) {
        return inimigos[i];
    }

    //Variável Chefões
    public String getChefoes(int i) {
        return chefoes[i];
    }
    //FUNÇÕES POLIMORFADAS COM SOBREPOSIÇÃO DA CLASSE PERSONAGEM: 

    //Função que retorna o dano causado ao jogador pelo inimigo:
    @Override
    public double Atacar(double atk_inimigo, double pv_jogador, double def_jogador){
        if ((pv_jogador < (pv_jogador - (atk_inimigo - (def_jogador/1.5)))))
            return pv_jogador;
        else
            return (pv_jogador - (atk_inimigo - (def_jogador/1.5)));
    }

    //Função que atribui os valores aos atributos do Inimigo:
    public void setInimigo(int sala, int gerador_int) {
        /*
        * Essa estrutura de repetição (IF/ELSE) é utilizada para definir se o inimigo atual é um CHEFÃO ou não,
        * Caso ele seja, seus valores serão diferentes dos inimigos normais.
        */
        if (sala == 20) {
            setBoss(true);
            setNome(getChefoes(0));
            setNivel(sala, sala, 2);
            setPontos_De_Vida(15, getNivel(), 2);
            setPontos_de_Ataque(7.5, getNivel(), 4);
            setPontos_de_Defesa(5, getNivel(), 4);
        }
        else if (sala == 40){
            setBoss(true);
            setNome(getChefoes(1));
            setNivel(sala, sala, 2);
            setPontos_De_Vida(20, getNivel(), 2);
            setPontos_de_Ataque(10, getNivel(), 4);
            setPontos_de_Defesa(8.5, getNivel(), 4);
        }
        else if (sala == 60) {
            setBoss(true);
            setNome(getChefoes(2));
            setNivel(sala, sala, 2);
            setPontos_De_Vida(25, getNivel(), 2);
            setPontos_de_Ataque(14, getNivel(), 4);
            setPontos_de_Defesa(12, getNivel(), 4);
        }
        else if (sala == 80) {
            setBoss(true);
            setNome(getChefoes(3));
            setNivel(sala, sala, 2);
            setPontos_De_Vida(30, getNivel(), 2);
            setPontos_de_Ataque(17.5, getNivel(), 4);
            setPontos_de_Defesa(15.5, getNivel(), 4);
        }
        else if (sala == 100) {
            setBoss(true);
            setNome(getChefoes(4));
            setNivel(sala, sala, 2);
            setPontos_De_Vida(35, getNivel(), 2);
            setPontos_de_Ataque(20, getNivel(), 4);
            setPontos_de_Defesa(18, getNivel(), 4);
        }
        else {
            setBoss(false);
            setNome(getInimigos(gerador_int));
            setNivel(1, sala, 2);
            setPontos_De_Vida(15, getNivel(), 2);
            setPontos_de_Ataque(6.5, getNivel(), 4);
            setPontos_de_Defesa(4, getNivel(), 4);
        }
    }
}
