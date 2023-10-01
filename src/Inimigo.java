public class Inimigo {
    //Atributos: 
    String nome_inimigo;
    int nivel_inimigo;
    double dano_inimigo;
    double pontos_vida_inimigo;
    double pontos_defesa_inimigo;
    

    //Funções refrentes aos atributos do inimigo:

    String Definir_Nome_Inimigo(String n_inimigo) {
        //Função que recebe um 'nome' do Banco de dados e retorna o nome do inimigo atual.
        return nome_inimigo = n_inimigo;
    }
    double Calcular_PV_Inimigo(double pv_inimigo, int lv_inimigo) {
        //Função que recebe um valor de 'vida' do Banco de dados e retorna o pontos de vida do inimigo atual
        pv_inimigo = pv_inimigo + (5 +(lv_inimigo/4));
        return pontos_vida_inimigo = pv_inimigo;
    }
    double Calcular__Defesa_inimigo(double def_inimigo, int lv_inimigo) {
        //Função que recebe um valor de 'defesa' do Banco de dados e retorna os pontos de defesa do inimigo atual.
        def_inimigo = def_inimigo + ()
        return pontos_defesa_inimigo = def_inimigo;
    }
    double Calcular_Dano_Inimigo(double ataque_inimigo){      
        //Função que recebe um valor de 'ataque' do Banco de dados e retorna os pontos de ataque do inimigo atual.
        return dano_inimigo = ataque_inimigo;
    }
    int Calcular_Nivel_Inimigo(int lv_inimigo) {
        //Função que recebe um valor de 'nivel' do Banco de dados e retorna o nivel do inimigo atual.
        return nivel_inimigo = lv_inimigo;
    }

    //Funções referentes ás ações do inimigo:
    double Atacar_Jogador (double pv_jogador, double atk_inimigo, double def_jogador) {
        //Função de ataque inimigo, ela recebe os pontos de vida do jogador e os pontos de atque inimigo, e retorna a vida do jogador após o ataque.
        if (pv_jogador < (pv_jogador - (atk_inimigo - (def_jogador/3))))
            return pv_jogador;
        else 
            return (pv_jogador -  (atk_inimigo - (def_jogador/3)));
    }


}
