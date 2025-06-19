public class Sala {
    
    int id;
    String descricao;
    String tipo;
    Sala anterior;
    Sala proxima;
    boolean visitada;

    public Sala(int id, String descricao, String tipo){
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo.toLowerCase();
        this.visitada = false;
    }

}
