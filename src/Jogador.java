
public class Jogador {
    //Atributos:
    String nome; //Nome dado pelo jogador.
    String escolha; //Escolha que será fornecida pelo jogador.
    int nivel = 1; //Valor referente a quão experiente o jogador é.
    double pontos_vida; //Valor referente a quantidade de vida (Saúde) do jogador.
    int pontos_experiencia; //Valor que molda o nivel do jogador.
    double pontos_ataque; //Valor referente a forca do jogador.
    double pontos_defesa; //Valor referente a resistência do jogador.
    

    /*
        Metódos/Funções (Ações ou Funcionalidades)
        Funções referentes aos atributos do jogador:
    */
    int Calcular_Nivel(int lv, int exp) {
        lv = lv + (exp/4);
        return nivel = lv;
    }
    
    double Calcular_Vida(double pv_jogador, int lv) {
        pv_jogador = pv_jogador + 3*(lv/4);
        return pontos_vida = pv_jogador;
    }

    double Calcular_Ataque(double p_atk, int lv) {
        p_atk = p_atk + (lv/2);
        return pontos_ataque = p_atk;
    }

    double Calcular_Defesa (double p_def, int lv) {
        p_def = p_def + (lv/2);
        return pontos_defesa = p_def;
    }

    //Funções referentes as ações do jogador:
    double Causa_dano(double atk, double pv_inimigo, double def_ini) {
        //Função de ataque, ela recebe os valores do atque do jogador e vida do inimigo na classe main, e retorna a vida do inimigo após causar o dano.
        if (pv_inimigo < (pv_inimigo - (atk - (def_ini/3))))
            return pv_inimigo;
        else 
            return (pv_inimigo - (atk - (def_ini/3)));
    }
    double Curar(double pv_jogador, double cura) {
        //Função de cura, ela recebe a vida do jogador e o valor de cura na classe main, e retorna a vida do jogador após a cura.
        if (pv_jogador >= (pv_jogador + cura))
            return pv_jogador;
        else 
            return pontos_vida = (pv_jogador + cura);
    }


}
