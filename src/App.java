import java.util.Scanner; //Importação do 'Scanner' para usar na classe MAIN.
import java.util.Random; //Importação do 'Random' para usar na classe MAIN.

//OBS: ESTA É A PRIMEIRA VERSÃO, UMA ESPECIE DE ALPHA. MUITA COISA AINDA ESTÁ EM FASE DE TESTE, ENTÃO TENHAM COMPREEÇÃO!

public class App {
    public static void main(String[] args) throws Exception {

        // As instancias de objetos das outras classes/bibliotecas, de onde virá grande parte do codigo.
        Scanner entrada = new Scanner(System.in);   //Leitor para escolhas do jogador.
        Random gerador = new Random();  //Instância de um gerador númerico para dar aleatoriedade ao jogo.
        Banco_De_Dados vetor_dados = new Banco_De_Dados();  //Instância do sistema de Combate, para ter acesso a sua classe com seus métodos e atributos.
        Jogador player = new Jogador(); //Instância do jogador, para ter acesso a classe com seus métodos e atributos.
        Inimigo enemy = new Inimigo();  //Instância do inimigo, para ter acesso a classe com seus métodos e atributos.
        int indice; // Indice para os vetores de inimigo.
        int sala = 0; //Variavel referente a sala atual que o jogador está.

        //Introdução do jogo.
        System.out.println("Bem vindo á 'JAVA DUNGEON'! \n"
        + "Você, estará no controle de aventureiro iniciante que foi enviado para 'A MASMORRA DAS 100 SALAS' com a missão de finaliza-la. \n"
        + "Agora cabe a você, pequeno aventureiro, desbravar esta masmorra e conquistar o seu tesouro. \n");
        System.out.print("Insira o nome do seu personagem para iniciarmos a sua aventura: ");
        player.nome = entrada.nextLine(); //Entrada do nome do personagem do jogador.

        /*Interface inicial do jogador, junto de um sistema de repetição 'SWITCH' junto do 'DO/WHILE', com o intuito do jogador escolher suas ações, sejam elas:
        * Avançar - Ir para a proxima sala da masmorra;
        * Ver status - Verificar os atributos do personagem (Ataque, Defesa, Vida e nivel);
        * Sair - Fechar o programa.
        */
        System.out.print(player.nome + " Boa sorte em sua jornada! Que ação você desja realizar? \nVer Status(VER) || Avançar(AVA) || Sair(SAIR): ");
        player.escolha = entrada.nextLine();

        do {

        /*Atribuição dos valores dos status do jogador, sendo elas em ordem:
        * Nivel;
        * Pontos de ataque;
        * Pontos de defesa;
        * Pontos de vida.
        */
        player.Calcular_Nivel(1, player.pontos_experiencia);
        player.Calcular_Ataque(10,  player.nivel);
        player.Calcular_Defesa(2, player.nivel);
        player.Calcular_Vida(10, player.nivel);

        //Inicio da estrutura de repetição situacional (Cada opção é um caso diferente):
        switch(player.escolha) {
            
            //Opção 1, caso o jogador escolha ver os status, o codigo irá rodar essa  "função":
            case "VER": 
            System.out.format("\nNome: %s || Nível: %d || Classe: Cavaleiro \n ---Status--- \n ATK: %.1f || DEF: %.1f || PV: %.1f || EXP: %d\n",
            player.nome, player.nivel, player.pontos_ataque, player.pontos_defesa, player.pontos_vida, player.pontos_experiencia);
            System.out.print("\nInsira outra ação para continuar: ");
            player.escolha = entrada.nextLine();
            break;

            //Opção 2, caso o jogador escolha avançar mais a fundo na masmorra: 
            case "AVA":
            sala +=1;
            System.out.format("\nVocê decidiu avançar na masmorra. A sala atual que você está é %d\n", sala);
            
            //Declaração dos stautus (Valores dos atributos do inimigo):
            enemy.Calcular_Nivel_Inimigo(1+sala); //Nivel inimigo;
            enemy.Definir_Nome_Inimigo(vetor_dados.nomes_ini[indice = gerador.nextInt(3)]); //Nome inimigo;
            enemy.Calcular_Dano_Inimigo(vetor_dados.pontos_atk_ini[indice = gerador.nextInt(3)] + (2 + (enemy.nivel_inimigo/2))); //Pontos de ataque do inimigo;
            enemy.Calcular__Defesa_inimigo(vetor_dados.pontos_def_ini[indice = gerador.nextInt(3)] + (2 + (enemy.nivel_inimigo/2))); //Pontos de defesa do inimigo;
            enemy.Calcular_PV_Inimigo(vetor_dados.pvs_ini[indice = gerador.nextInt(3)], enemy.nivel_inimigo); //Pontos de vida do inimigo.

            //Alerta de que o jogador encontrou um inimigo:
            System.out.format("Um %s selvagem de nível %d apareceu na sua frente! O que você escolhe fazer? \n(ATK) || (CHECK) || (CURAR) || (FUGIR): ",
            enemy.nome_inimigo, enemy.nivel_inimigo);
            player.escolha = entrada.nextLine();

            /*Sistema de combate, com as opções de:
            * Curar - Restaurar a vida do jogador;
            * Atacar - Causar dano ao inimigo (Ele também causará dano de volta);
            * Checkar - Ver os status inimigo;
            * Fugir - Abandonar a batlha.
            */
            do{
            switch(player.escolha) {

                //Opção para caso o jogador escolha atacar o inimigo (Há a chance de ser morto):
                case "ATK":
                player.Causa_dano(player.pontos_ataque, enemy.pontos_vida_inimigo, enemy.pontos_defesa_inimigo);
                enemy.Calcular_PV_Inimigo(player.Causa_dano(player.pontos_ataque, enemy.pontos_vida_inimigo, enemy.pontos_defesa_inimigo));

                //Estrutura de repetição para saber se o inimigo foi derrotado ou não.
                if  (enemy.pontos_vida_inimigo <= 0){ //Caso ele tenha sido derrotado:
                    player.pontos_experiencia += 5;
                    System.out.print("\nO inimigo foi derrotado! Você recebu 5 pontos de Experiência. \n"+
                        "Escolha uma das opções para continuar Avançar(AVA) || Ver Status(VER) || Sair(SAIR): ");
                }
                else { //Caso não:
                    System.out.format("Você atacou o %s! O seu ataque causou %.1f de dano. A vida atual dele é de %.1f\n",
                    enemy.nome_inimigo, player.pontos_ataque, enemy.pontos_vida_inimigo);
                    
                    enemy.Atacar_Jogador(player.pontos_vida, enemy.dano_inimigo, player.pontos_defesa);
                    player.Calcular_Vida(enemy.Atacar_Jogador(player.pontos_vida, enemy.dano_inimigo, player.pontos_defesa));
                    
                    //Estrutura de repetição (IF/ELSE) para saber se o jogador foi morto ou não.
                    if (player.pontos_vida <= 0){ //Caso o jogador tenha sido derrotado:
                        System.out.println("\nVocê morreu, digite 'SAIR' na proxima tela para sair.");
                    }
                    else { //Caso não:
                        System.out.format("O %s atacou você! Ele causou %.1f de dano ao seu personagem. Sua vida atual é de %.1f\n",
                        enemy.nome_inimigo, enemy.dano_inimigo, player.pontos_vida);
                        System.out.print("\nInsira uma ação novamente para continuar: (ATK) || (CHECK) || (CURAR) || (FUGIR):  ");
                        player.escolha = entrada.nextLine();
                    }
                }
                break;

                //Opção para caso o jogador escolha verificar os STATUS do inimigo atual:
                case "CHECK":
                System.out.format("\nNome: %s || Nível: %d || \n---Status--- \n ATK: %.1f || DEF: %.1f || PV: %.1f \n",
                enemy.nome_inimigo, enemy.nivel_inimigo, enemy.dano_inimigo, enemy.pontos_defesa_inimigo, enemy.pontos_vida_inimigo);
                System.out.print("\nInsira uma ação novamente para continuar: (ATK) || (CHECK) || (CURAR) || (FUGIR):  ");
                player.escolha = entrada.nextLine();
                break;

                //Opção para caso o jogador esolha curar o seu personagem:
                case "CURAR":

                player.Curar(player.pontos_vida, 5); //Essa função irá retornar a vida do jogador após a cura.
                System.out.format("Você se curou em 5 pontos! Agora seus pontos de vida atuais são: %.1f \n", player.pontos_vida);
                System.out.print("\nInsira uma ação novamente para continuar: (ATK) || (CHECK) || (CURAR) || (FUGIR):  ");
                player.escolha = entrada.nextLine();
                break;

                //Opção para caso o jogador escolha fugir (Irá perder pontos de experiência):
                case "FUGIR":
                player.pontos_experiencia -= 2;
                System.out.println("Você decidiu fugir! Por conta da sua covardice, você perdeu 2 de experiência! "+
                    "\nEscolha uma das opções para continuar Avançar(A) || Ver Status(V) || Sair(S):");   
                break;

                //Opção DEFAULT para caso o jogador insira uma alternativa não existente:
                default:
                System.out.println("\nInsira uma ação valída, tente novamente.");
                player.escolha = entrada.nextLine();
                break;

            }}while((enemy.pontos_vida_inimigo > 0) && (!player.escolha.equals("FUGIR")) && (player.pontos_vida > 0));
            player.escolha = entrada.nextLine();
            break;

            //Opção 3, encerrar o programa:
            case "SAIR":
            System.out.println("Programa encerrado. Até logo!");
            break;

            //Opção DEFAULT para caso o jogador não coloque uma alternativa possivel:
            default:
            System.out.println("Insira uma ação valída, tente novamente.");
            player.escolha = entrada.nextLine();
            break;
        }
        }while(!player.escolha.equals("SAIR") && sala < 100);

        entrada.close();
    }
}
