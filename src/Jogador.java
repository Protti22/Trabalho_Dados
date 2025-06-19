import java.util.ArrayList;
import java.util.List;

public class Jogador {
    String nome;
    int pontuacao;
    List<String> inventario;
    Sala salaAtual;
    List<Integer> salasVisitadas;

    public Jogador(String nome){
        this.nome = nome;
        this.pontuacao = 0;
        this.inventario = new ArrayList<>();
        this.salasVisitadas = new ArrayList<>();
    }

    public void visitarSala(int id){
        if (!salasVisitadas.contains(id)) {
            salasVisitadas.add(id);
        }
    }
}
