import java.util.Scanner; //Importação do 'Scanner' para usar na classe MAIN.
import java.util.Random; //Importação do 'Random' para usar na classe MAIN.

//OBS: ESTA É A PRIMEIRA VERSÃO, UMA ESPECIE DE ALPHA. MUITA COISA AINDA ESTÁ EM FASE DE TESTE, ENTÃO TENHAM COMPREEÇÃO!

public class App {
    public static void main(String[] args) throws Exception {

        // As instancias de objetos das outras classes/bibliotecas, de onde virá grande parte do codigo.
        Scanner entrada = new Scanner(System.in);   //Leitor para escolhas do jogador.
        Random gerador = new Random();  //Instância de um gerador númerico para dar aleatoriedade ao jogo.
        Jogador player = new Jogador(); //Instância do jogador, para ter acesso a classe com seus métodos e atributos.
        Inimigo enemy = new Inimigo();  //Instância do inimigo, para ter acesso a classe com seus métodos e atributos.
         //Variavel referente a sala atual que o jogador está.

        //Introdução do jogo.
        System.out.println("Bem vindo á 'The Depths of Prekirs'! \n"
        + ">Você, um aventureiro novato, ouviu os boatos de uma masmorra esquecida pelo tempo, cujo o tesouro contido na ultima sala tem o poder de realizar qualquer desejo.\n"
        + ">Contudo, uma vez que alguem a adentra, é impossivel retroceder.\n"
        + "Existem rumores de que caso você tente sair da masmorra sem chegar ao ultimo andar, a pessoa sofrerá de uma horrível maldição, tal qual disfigura seu corpo e mente, tornando-a um dos montros presente dentro da masmorra.");
        System.out.print("Insira o nome do seu personagem para iniciarmos a sua aventura: ");
        player.setNome(entrada.nextLine()); //Entrada do nome do personagem do jogador.

        /*Interface inicial do jogador, junto de um sistema de repetição 'SWITCH' junto do 'DO/WHILE', com o intuito do jogador escolher suas ações, sejam elas:
        * Avançar - Ir para a proxima sala da masmorra;
        * Ver status - Verificar os atributos do personagem (Ataque, Defesa, Vida e nivel);
        * Sair - Fechar o programa.
        */
        System.out.print(player.getNome() + " Boa sorte em sua jornada! Que ação você desja realizar? \nVer Status(VER) || Avançar(AVA) || Retornar(RET) || Sair(SAIR): ");
        player.setEscolha(entrada.nextLine());

        do {
        /*Atribuição dos valores dos status do jogador, sendo elas em ordem:
        * Nivel;
        * Pontos de ataque;
        * Pontos de defesa;
        * Pontos de vida.
        */
        player.setJogador();
        

        //Inicio da estrutura de repetição situacional (Cada opção é um caso diferente):
        switch(player.getEscolha()) {
            
            //Opção 1, caso o jogador escolha ver os status, o codigo irá rodar essa  "função":
            case "VER": 
            System.out.format("\nNome: %s || Nível: %d || Classe: Cavaleiro \n ---Status--- \n ATK: %.1f || DEF: %.1f || PV: %.1f || EXP: %d\n",
            player.getNome(), player.getNivel(), player.getPontos_de_Ataque(), player.getPontos_de_Defesa(), player.getPontos_De_Vida(), player.getPontos_Experiencia());
            System.out.print("\nInsira outra ação para continuar: ");
            player.setEscolha(entrada.nextLine());
            break;

            case "RET":
                player.setSala(false);
                System.out.format("\nVocê decidiu retroceder na masmorra. A sala atual que você está é %d\n", player.getSala());

                //Declaração dos stautus (Valores dos atributos do inimigo):
                enemy.setInimigo(player.getSala(), gerador.nextInt(6));

                //Alerta de que o jogador encontrou um inimigo:
                System.out.format("Um %s selvagem de nível %d apareceu na sua frente! O que você escolhe fazer? \n(ATK) || (CHECK) || (CURAR) || (FUGIR): ",
                enemy.getNome(), enemy.getNivel());
                player.setEscolha(entrada.nextLine());

                /*Sistema de combate, com as opções de:
                * Curar - Restaurar a vida do jogador;
                * Atacar - Causar dano ao inimigo (Ele também causará dano de volta);
                * Checkar - Ver os status inimigo;
                * Fugir - Abandonar a batlha.
                */
                do{
                switch(player.getEscolha()) {

                    //Opção para caso o jogador escolha atacar o inimigo (Há a chance de ser morto):
                    case "ATK":
                        enemy.setPontos_De_Vida(player.Atacar(player.getPontos_de_Ataque(), enemy.getPontos_De_Vida(), enemy.getPontos_De_Vida()));
                        //Estrutura de repetição para saber se o inimigo foi derrotado ou não.
                        if  (enemy.getPontos_De_Vida() <= 0){ //Caso ele tenha sido derrotado:
                            player.setPontos_Experiencia(true, enemy.getNivel());
                            System.out.print("\nO inimigo foi derrotado! Você recebu " + (enemy.getNivel()*2) + " pontos de Experiência. \n"+
                                "Escolha uma das opções para continuar Avançar(AVA) || Retornar(RET) || Ver Status(VER) || Sair(SAIR): ");
                        }
                        else { //Caso não:
                            System.out.format("Você atacou o %s! O seu ataque causou %.1f de dano. A vida atual dele é de %.1f\n",
                            enemy.getNome(), (player.getPontos_de_Ataque() - (enemy.getPontos_de_Defesa()/4)), enemy.getPontos_De_Vida());

                            player.setPontos_De_Vida(enemy.Atacar(enemy.getPontos_de_Ataque(), player.getPontos_De_Vida(), player.getPontos_de_Defesa()));
                            //player.Calcular_Vida(enemy.Atacar_Jogador(player.pontos_vida, enemy.dano_inimigo, player.pontos_defesa), 0);

                            //Estrutura de repetição (IF/ELSE) para saber se o jogador foi morto ou não.
                            if (player.getPontos_De_Vida() <= 0){ //Caso o jogador tenha sido derrotado:
                                System.out.println("\nVocê morreu, digite 'SAIR' na proxima tela para sair.");
                            }
                            else { //Caso não:
                                System.out.format("O %s atacou você! Ele causou %.1f de dano ao seu personagem. Sua vida atual é de %.1f\n",
                                enemy.getNome(), (enemy.getPontos_de_Ataque() - (player.getPontos_de_Defesa()/3)), player.getPontos_De_Vida());
                                System.out.print("\nInsira uma ação novamente para continuar: (ATK) || (CHECK) || (CURAR) || (FUGIR):  ");
                                player.setEscolha(entrada.nextLine());
                            }
                        }
                    break;

                    //Opção para caso o jogador escolha verificar os STATUS do inimigo atual:
                    case "CHECK":
                        System.out.format("\nNome: %s || Nível: %d || \n---Status--- \n ATK: %.1f || DEF: %.1f || PV: %.1f \n",
                        enemy.getNome(), enemy.getNivel(), enemy.getPontos_de_Ataque(), enemy.getPontos_de_Defesa(), enemy.getPontos_De_Vida());
                        System.out.print("\nInsira uma ação novamente para continuar: (ATK) || (CHECK) || (CURAR) || (FUGIR):  ");
                        player.setEscolha(entrada.nextLine());
                    break;

                    //Opção para caso o jogador esolha curar o seu personagem:
                    case "CURAR": 
                        player.setPontos_De_Vida(player.Curar(player.getPontos_De_Vida(), 0.1, player.getNivel()));
                        System.out.format("Você se curou completamente! Agora seus pontos de vida atuais são: %.1f \n", 
                        player.getPontos_De_Vida());

                        player.setPontos_De_Vida(enemy.Atacar(enemy.getPontos_de_Ataque(), player.getPontos_De_Vida(), player.getPontos_de_Defesa()));
                        System.out.format("O %s atacou você! Ele causou %.1f de dano ao seu personagem. Sua vida atual é de %.1f\n",
                        enemy.getNome(), (enemy.getPontos_de_Ataque() - (player.getPontos_de_Defesa()/3)), player.getPontos_De_Vida());
                        System.out.print("\nInsira uma ação novamente para continuar: (ATK) || (CHECK) || (CURAR) || (FUGIR):  ");
                        player.setEscolha(entrada.nextLine());
                    break;

                    //Opção para caso o jogador escolha fugir (Irá perder pontos de experiência):
                    case "FUGIR":
                        player.setPontos_Experiencia(false, enemy.getNivel());
                        System.out.println("Você decidiu fugir! Por conta da sua covardice, você perdeu " + (enemy.getNivel()*2) + " pontos de experiência!"+
                            "\nEscolha uma das opções para continuar Avançar(AVA) || Retornar(RET) || Ver Status(VER) || Sair(SAIR):");   
                    break;

                    //Opção DEFAULT para caso o jogador insira uma alternativa não existente:
                    default:
                        System.out.println("\nInsira uma ação valída, tente novamente.");
                        player.setEscolha(entrada.nextLine());
                    break;

                }}while((enemy.getPontos_De_Vida() > 0) && (!player.getEscolha().equals("FUGIR")) && (player.getPontos_De_Vida() > 0));
                player.setEscolha(entrada.nextLine());
            //Opção 2, caso o jogador escolha avançar mais a fundo na masmorra: 
            case "AVA":
                player.setSala(true);
                System.out.format("\nVocê decidiu avançar na masmorra. A sala atual que você está é %d\n", player.getSala());

                //Declaração dos stautus (Valores dos atributos do inimigo):
                enemy.setInimigo(player.getSala(), gerador.nextInt(6));

                //Alerta de que o jogador encontrou um inimigo:
                System.out.format("Um %s selvagem de nível %d apareceu na sua frente! O que você escolhe fazer? \n(ATK) || (CHECK) || (CURAR) || (FUGIR): ",
                enemy.getNome(), enemy.getNivel());
                player.setEscolha(entrada.nextLine());

                /*Sistema de combate, com as opções de:
                * Curar - Restaurar a vida do jogador;
                * Atacar - Causar dano ao inimigo (Ele também causará dano de volta);
                * Checkar - Ver os status inimigo;
                * Fugir - Abandonar a batlha.
                */
                do{
                switch(player.getEscolha()) {

                    //Opção para caso o jogador escolha atacar o inimigo (Há a chance de ser morto):
                    case "ATK":
                        enemy.setPontos_De_Vida(player.Atacar(player.getPontos_de_Ataque(), enemy.getPontos_De_Vida(), enemy.getPontos_De_Vida()));
                        //Estrutura de repetição para saber se o inimigo foi derrotado ou não.
                        if  (enemy.getPontos_De_Vida() <= 0){ //Caso ele tenha sido derrotado:
                            player.setPontos_Experiencia(true, enemy.getNivel());
                            System.out.print("\nO inimigo foi derrotado! Você recebu " + (enemy.getNivel()*2) + " pontos de Experiência. \n"+
                                "Escolha uma das opções para continuar Avançar(AVA) || Retornar(RET) || Ver Status(VER) || Sair(SAIR): ");
                        }
                        else { //Caso não:
                            System.out.format("Você atacou o %s! O seu ataque causou %.1f de dano. A vida atual dele é de %.1f\n",
                            enemy.getNome(), (player.getPontos_de_Ataque() - (enemy.getPontos_de_Defesa()/4)), enemy.getPontos_De_Vida());

                            player.setPontos_De_Vida(enemy.Atacar(enemy.getPontos_de_Ataque(), player.getPontos_De_Vida(), player.getPontos_de_Defesa()));
                            //player.Calcular_Vida(enemy.Atacar_Jogador(player.pontos_vida, enemy.dano_inimigo, player.pontos_defesa), 0);

                            //Estrutura de repetição (IF/ELSE) para saber se o jogador foi morto ou não.
                            if (player.getPontos_De_Vida() <= 0){ //Caso o jogador tenha sido derrotado:
                                System.out.println("\nVocê morreu, digite 'SAIR' na proxima tela para sair.");
                            }
                            else { //Caso não:
                                System.out.format("O %s atacou você! Ele causou %.1f de dano ao seu personagem. Sua vida atual é de %.1f\n",
                                enemy.getNome(), (enemy.getPontos_de_Ataque() - (player.getPontos_de_Defesa()/3)), player.getPontos_De_Vida());
                                System.out.print("\nInsira uma ação novamente para continuar: (ATK) || (CHECK) || (CURAR) || (FUGIR):  ");
                                player.setEscolha(entrada.nextLine());
                            }
                        }
                    break;

                    //Opção para caso o jogador escolha verificar os STATUS do inimigo atual:
                    case "CHECK":
                        System.out.format("\nNome: %s || Nível: %d || \n---Status--- \n ATK: %.1f || DEF: %.1f || PV: %.1f \n",
                        enemy.getNome(), enemy.getNivel(), enemy.getPontos_de_Ataque(), enemy.getPontos_de_Defesa(), enemy.getPontos_De_Vida());
                        System.out.print("\nInsira uma ação novamente para continuar: (ATK) || (CHECK) || (CURAR) || (FUGIR):  ");
                        player.setEscolha(entrada.nextLine());
                    break;

                    //Opção para caso o jogador esolha curar o seu personagem:
                    case "CURAR": 
                        player.setPontos_De_Vida(player.Curar(player.getPontos_De_Vida(), 0.1, player.getNivel()));
                        System.out.format("Você se curou completamente! Agora seus pontos de vida atuais são: %.1f \n", 
                        player.getPontos_De_Vida());

                        player.setPontos_De_Vida(enemy.Atacar(enemy.getPontos_de_Ataque(), player.getPontos_De_Vida(), player.getPontos_de_Defesa()));
                        System.out.format("O %s atacou você! Ele causou %.1f de dano ao seu personagem. Sua vida atual é de %.1f\n",
                        enemy.getNome(), (enemy.getPontos_de_Ataque() - (player.getPontos_de_Defesa()/3)), player.getPontos_De_Vida());
                        System.out.print("\nInsira uma ação novamente para continuar: (ATK) || (CHECK) || (CURAR) || (FUGIR):  ");
                        player.setEscolha(entrada.nextLine());
                    break;

                    //Opção para caso o jogador escolha fugir (Irá perder pontos de experiência):
                    case "FUGIR":
                        player.setPontos_Experiencia(false, enemy.getNivel());
                        System.out.println("Você decidiu fugir! Por conta da sua covardice, você perdeu " + (enemy.getNivel()*2) + " pontos de experiência!"+
                            "\nEscolha uma das opções para continuar Avançar(AVA) || Retornar(RET) || Ver Status(VER) || Sair(SAIR):");   
                    break;

                    //Opção DEFAULT para caso o jogador insira uma alternativa não existente:
                    default:
                        System.out.println("\nInsira uma ação valída, tente novamente.");
                        player.setEscolha(entrada.nextLine());
                    break;

                }}while((enemy.getPontos_De_Vida() > 0) && (!player.getEscolha().equals("FUGIR")) && (player.getPontos_De_Vida() > 0));
                player.setEscolha(entrada.nextLine());
            break;

            //Opção 3, encerrar o programa:
            case "SAIR":
                System.out.println("Programa encerrado. Até logo!");
            break;

            //Opção DEFAULT para caso o jogador não coloque uma alternativa possivel:
            default:
                System.out.println("Insira uma ação valída, tente novamente.");
                player.setEscolha(entrada.nextLine());
            break;
        }
        }while(!player.getEscolha().equals("SAIR") && player.getSala() < 100);

        entrada.close();
    }
}
