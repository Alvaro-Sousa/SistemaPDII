
import java.util.List;

public class Administrador {
    private String nome;
    
    public Administrador(String nome) {
        this.nome = nome;
    }
    
    public void adicionarFeedback(List<Feedbacks> feedbacks, Feedbacks feedback) {
        feedbacks.add(feedback);
        System.out.println("Feedback adicionado com sucesso!");
    }
    
    public void visualizarMetas(List<Meta> metas) {
        if (metas.isEmpty()) {
            System.out.println("Nenhuma meta cadastrada.");
            return;
        }
        
        System.out.println("\n=== TODAS AS METAS ===");
        for (int i = 0; i < metas.size(); i++) {
            System.out.println((i + 1) + ". " + metas.get(i));
        }
    }
    
    public String getNome() {
        return nome;
    }
}