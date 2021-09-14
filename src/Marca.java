import java.util.List;

public class Marca {
        
    private String nome;
    private List<String> modelli;

    public Marca(String nome, List<String> modelli) {
        super();
        this.nome = nome;
        this.modelli = modelli;
    }

    public String getNome() {
        return nome;
    }

    public List<String> getModelli() {
        return modelli;
    }
}
