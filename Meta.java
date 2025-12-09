
import java.util.ArrayList;
import java.util.List;

public class Meta {
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private StatusMeta status;
    private List<Tarefa> tarefas;
    
    public Meta(String titulo, String descricao, Prioridade prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.status = StatusMeta.PENDENTE;
        this.tarefas = new ArrayList<>();
    }
    
    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }
    
    public void atualizarStatus(StatusMeta novoStatus) {
        this.status = novoStatus;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public StatusMeta getStatus() {
        return status;
    }
    
    public Prioridade getPrioridade() {
        return prioridade;
    }
    
    public List<Tarefa> getTarefas() {
        return tarefas;
    }
    
    public String calcularProgresso() {
        if (tarefas.isEmpty()) return "Sem tarefas";
        long concluidas = tarefas.stream().filter(Tarefa::isConcluida).count();
        if (concluidas == tarefas.size()) {
            return "Concluída";
        } else if (concluidas > 0) {
            return "Em andamento";
        } else {
            return "Pendente";
        }
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s - %s (%s)", 
            prioridade, titulo, status, calcularProgresso());
    }
}