import java.util.ArrayList;
import java.util.List;

public class Labirinto {
    Sala primeiraSala;
    Sala ultimSala;
    int totalSalas;
    ArrayList<Sala> salas = new ArrayList<>();

    public void adicionarSala(int id, String descricao, String tipo) {
        Sala nova = new Sala(id, descricao, tipo);
        salas.add(nova);

        if(primeiraSala == null){
            primeiraSala = nova;
            ultimSala = nova;
        
        }else{
            ultimSala.proxima = nova;
            nova.anterior = ultimSala;
            ultimSala = nova;

        }
        totalSalas++;
    }

    
    public void listarSalas() {
        Sala atual = primeiraSala;
        while (atual != null) {
            
            System.out.printf("ID: %d | Tipo: %s | Descrição: %s\n", atual.id, atual.tipo, atual.descricao);
            atual = atual.proxima;

        }
    }

    public void removerSala(int id) {

        Sala atual = primeiraSala;
        while (atual != null) {

            if (atual.id == id) {

                if (atual.anterior != null) {
                    atual.anterior.proxima = atual.proxima;
                }else{
                    primeiraSala = atual.proxima;
                }

                if (atual.proxima != null) {
                    atual.proxima.anterior = atual.anterior;
                }else{
                    ultimSala = atual.anterior;
                }

                totalSalas--;
                System.out.println("Sala Removida!");
                return;
            }

            atual = atual.proxima;
        }
        System.out.println("Sala não encontrada");

    }

    public boolean todasSalasVisitadas(Jogador jogador) {

        //
        return jogador.salasVisitadas.size() == totalSalas - 1;
    }

}
