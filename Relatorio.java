
import java.util.List;

public class Relatorio {
    
    public static void gerarRelatorio(List<Meta> metas, List<Feedbacks> feedbacks) {
        System.out.println("\n RELATORIO PDI");
        System.out.println("Total de metas: " + metas.size());
        
        long concluidas = metas.stream()
            .filter(m -> m.getStatus() == StatusMeta.CONCLUIDA)
            .count();
        long emProgresso = metas.stream()
            .filter(m -> m.getStatus() == StatusMeta.EM_PROGRESSO)
            .count();
        long pendentes = metas.stream()
            .filter(m -> m.getStatus() == StatusMeta.PENDENTE)
            .count();
        
        System.out.println("Concluidas: " + concluidas);
        System.out.println("Em progresso: " + emProgresso);
        System.out.println("Pendentes: " + pendentes);
          
        System.out.println("\nTotal de feedbacks: " + feedbacks.size());
    }
}