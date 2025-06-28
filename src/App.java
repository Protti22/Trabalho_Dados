import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        Labirinto labirinto = new Labirinto();
        Jogador jogador = null;

        
        while (labirinto.totalSalas < 10) {
            
            System.out.println("Adicione pelo menos 10 salas antes de começar:");
            System.out.print("ID da sala: ");
            int id = sc.nextInt(); sc.nextLine();
            System.out.print("Descrição: ");
            String descricao = sc.nextLine();
            System.out.print("Tipo (normal, armadilha, premio, saida): ");
            String tipo = sc.nextLine();
            labirinto.adicionarSala(id, descricao, tipo);

        }

        System.out.print("Nome do jogador: ");
        String nome = sc.nextLine();
        jogador = new Jogador(nome);
        jogador.salaAtual = labirinto.primeiraSala;

        System.out.println("\nJogo iniciado!\n");
        

        boolean jogoAtivo = true;
        while (jogoAtivo) {
            
            Sala atual = jogador.salaAtual;
            jogador.visitarSala(atual.id);

            System.out.printf("\nVocê está na sala ID %d - %s\n", atual.id, atual.descricao);

            switch (atual.tipo) {
                case "normal":
                    break;
            
                case "armadilha":
                    System.out.println("Você encontrou uma armadilha!");
                    jogador.pontuacao -= 10;

                    if (atual.anterior != null) {
                        System.out.println("Você perdeu 10 pontos!");
                        continue;
                    }
                    break;

                case "premio":
                    jogador.pontuacao += 20;
                    jogador.inventario.add("Item raro" + atual.id);
                    System.out.println("Parabéns! Você encontrou um item raro");
                    break;

                case "saida":
                    if (labirinto.todasSalasVisitadas(jogador)) {
                        System.out.println("Você venceu o jogo!");
                        jogoAtivo = false;
                    }else{
                        System.out.println("Você chegou à saída, mas ainda não explorou tudo!");
                    }
                    break;

                default:
                    break;
            }

            System.out.println("\nEscolha uma ação: [1] Avançar | [2] Voltar | [3] Status | [0] Sair");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    if (atual.proxima != null) {
                        
                        System.out.println("Digite um número de 1 a 10 para escolher uma sala: ");
                        int escolhaSala = sc.nextInt();


                        for (Sala elemento : labirinto.salas){
                            if (elemento.id == escolhaSala) {
                                jogador.salaAtual = elemento;
                                System.out.println("Você está na sala " + elemento.id);
                                break;
                            }
                        }



                    }else{
                        System.out.println("Você está na última sala.");
                    }
                    break;

                case 2:
                    if (atual.anterior != null) {
                        jogador.salaAtual = atual.anterior;
                    }else{
                        System.out.println("Você está na primeira sala.");
                    }
                    break;

                case 3:
                    System.out.println("Pontuação: " + jogador.pontuacao);
                    System.out.println("Inventário: " + jogador.inventario);
                    System.out.println("Salas visitadas: " + jogador.salasVisitadas.size());
                    break;

                case 0:
                    jogoAtivo = false;
                    break;
            
                default:
                    break;
            }

        }

        System.out.println("\n--- Fim do Jogo ---");
        System.out.println("Jogador: " + jogador.nome);
        System.out.println("Pontuação final: " + jogador.pontuacao);
        System.out.println("Itens coletados: " + jogador.inventario);
        System.out.println("Salas visitadas: " + jogador.salasVisitadas.size());
    }

}
