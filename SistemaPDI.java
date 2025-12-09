// SistemaPDI.java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class SistemaPDI {
    private List<Meta> metas;
    private List<Feedbacks> feedbacks;
    private Administrador admin;
    
    public SistemaPDI() {
        this.metas = new ArrayList<>();
        this.feedbacks = new ArrayList<>();
        this.admin = new Administrador("Admin");
    }
    
    public void menu(TipoUsuario tipo, Scanner scanner) {
        boolean rodando = true;
        
        while (rodando) {
            System.out.println("\n=== MENU " + tipo + " ===");
            System.out.println("1 - Criar Meta");
            System.out.println("2 - Listar Metas");
            System.out.println("3 - Atualizar Status Meta");
            System.out.println("4 - Adicionar Tarefa");
            System.out.println("5 - Concluir Tarefa");
            if (tipo == TipoUsuario.ADMINISTRADOR) {
                System.out.println("6 - Adicionar Feedback");
                System.out.println("7 - Ver Feedbacks");
            }
            System.out.println("8 - Gerar Relatorio");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcao) {
                    case 1: criarMeta(scanner); break;
                    case 2: listarMetas(); break;
                    case 3: atualizarStatusMeta(scanner); break;
                    case 4: adicionarTarefa(scanner); break;
                    case 5: concluirTarefa(scanner); break;
                    case 6: 
                        if (tipo == TipoUsuario.ADMINISTRADOR) adicionarFeedback(scanner);
                        else System.out.println("ERRO: Opcao disponivel apenas para administradores!");
                        break;
                    case 7: 
                        if (tipo == TipoUsuario.ADMINISTRADOR) verFeedbacks();
                        else System.out.println("ERRO: Opcao disponivel apenas para administradores!");
                        break;
                    case 8: Relatorio.gerarRelatorio(metas, feedbacks); break;
                    case 0: 
                        rodando = false;
                        System.out.println("Saindo...");
                        break;
                    default: 
                        System.out.println("ERRO: Opcao invalida! Escolha um numero entre 0 e 8.");
                }
            } catch (InputMismatchException e) {
                System.out.println("ERRO: Entrada invalida! Digite um numero inteiro.");
                scanner.nextLine(); // Limpar buffer
            } catch (IllegalArgumentException e) {
                System.out.println("ERRO de validacao: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("ERRO: Indice fora dos limites! " + e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("ERRO: Referencia nula! " + e.getMessage());
            } catch (Exception e) {
                System.out.println("ERRO inesperado: " + e.getMessage());
                if (e.getMessage() == null || e.getMessage().isEmpty()) {
                    System.out.println("Tipo de erro: " + e.getClass().getSimpleName());
                }
                scanner.nextLine(); // Limpar buffer em caso de erro
            }
        }
    }
    
    private void criarMeta(Scanner scanner) {
        try {
            System.out.print("Titulo da meta: ");
            String titulo = scanner.nextLine();
            if (titulo == null || titulo.trim().isEmpty()) {
                throw new IllegalArgumentException("Titulo da meta nao pode estar vazio!");
            }
            
            System.out.print("Descricao: ");
            String descricao = scanner.nextLine();
            if (descricao == null || descricao.trim().isEmpty()) {
                throw new IllegalArgumentException("Descricao nao pode estar vazia!");
            }
            
            System.out.println("Prioridade (1-BAIXA, 2-MEDIA, 3-ALTA): ");
            int p = scanner.nextInt();
            scanner.nextLine();
            
            if (p < 1 || p > 3) {
                throw new IllegalArgumentException("Prioridade deve ser 1, 2 ou 3!");
            }
            
            Prioridade prioridade = (p == 1) ? Prioridade.BAIXA : 
                                    (p == 2) ? Prioridade.MEDIA : Prioridade.ALTA;
            
            Meta meta = new Meta(titulo, descricao, prioridade);
            metas.add(meta);
            System.out.println("Meta criada com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("ERRO: Entrada invalida! Digite um numero inteiro para a prioridade.");
            scanner.nextLine(); // Limpar buffer
        } catch (IllegalArgumentException e) {
            System.out.println("ERRO de validacao: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERRO ao criar meta: " + e.getMessage());
            if (e.getMessage() == null || e.getMessage().isEmpty()) {
                System.out.println("Tipo de erro: " + e.getClass().getSimpleName());
            }
        }
    }
    
    private void listarMetas() {
        if (metas.isEmpty()) {
            System.out.println("Nenhuma meta cadastrada.");
            return;
        }
        
        System.out.println("\nSUAS METAS");
        for (int i = 0; i < metas.size(); i++) {
            System.out.println((i + 1) + ". " + metas.get(i));
        }
    }
    
    private void atualizarStatusMeta(Scanner scanner) {
        try {
            listarMetas();
            if (metas.isEmpty()) {
                System.out.println("AVISO: Nenhuma meta cadastrada para atualizar.");
                return;
            }
            
            System.out.print("Numero da meta: ");
            int num = scanner.nextInt() - 1;
            scanner.nextLine();
            
            if (num < 0 || num >= metas.size()) {
                throw new IndexOutOfBoundsException("Meta numero " + (num + 1) + " nao existe! Escolha entre 1 e " + metas.size());
            }
            
            System.out.println("Novo status (1-PENDENTE, 2-EM_PROGRESSO, 3-CONCLUIDA): ");
            int s = scanner.nextInt();
            scanner.nextLine();
            
            if (s < 1 || s > 3) {
                throw new IllegalArgumentException("Status deve ser 1, 2 ou 3!");
            }
            
            StatusMeta status = (s == 1) ? StatusMeta.PENDENTE : 
                               (s == 2) ? StatusMeta.EM_PROGRESSO : StatusMeta.CONCLUIDA;
            
            metas.get(num).atualizarStatus(status);
            System.out.println("Status atualizado com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("ERRO: Entrada invalida! Digite um numero inteiro.");
            scanner.nextLine(); // Limpar buffer
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERRO: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("ERRO de validacao: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERRO ao atualizar status: " + e.getMessage());
            if (e.getMessage() == null || e.getMessage().isEmpty()) {
                System.out.println("Tipo de erro: " + e.getClass().getSimpleName());
            }
        }
    }
    
    private void adicionarTarefa(Scanner scanner) {
        try {
            listarMetas();
            if (metas.isEmpty()) {
                System.out.println("AVISO: Nenhuma meta cadastrada. Crie uma meta primeiro.");
                return;
            }
            
            System.out.print("Numero da meta: ");
            int num = scanner.nextInt() - 1;
            scanner.nextLine();
            
            if (num < 0 || num >= metas.size()) {
                throw new IndexOutOfBoundsException("Meta numero " + (num + 1) + " nao existe! Escolha entre 1 e " + metas.size());
            }
            
            System.out.print("Descricao da tarefa: ");
            String desc = scanner.nextLine();
            
            if (desc == null || desc.trim().isEmpty()) {
                throw new IllegalArgumentException("Descricao da tarefa nao pode estar vazia!");
            }
            
            Tarefa tarefa = new Tarefa(desc);
            metas.get(num).adicionarTarefa(tarefa);
            System.out.println("Tarefa adicionada com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("ERRO: Entrada invalida! Digite um numero inteiro para a meta.");
            scanner.nextLine(); // Limpar buffer
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERRO: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("ERRO de validacao: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("ERRO: Meta nao encontrada! " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERRO ao adicionar tarefa: " + e.getMessage());
            if (e.getMessage() == null || e.getMessage().isEmpty()) {
                System.out.println("Tipo de erro: " + e.getClass().getSimpleName());
            }
        }
    }
    
    private void concluirTarefa(Scanner scanner) {
        try {
            listarMetas();
            if (metas.isEmpty()) {
                System.out.println("AVISO: Nenhuma meta cadastrada.");
                return;
            }
            
            System.out.print("Numero da meta: ");
            int num = scanner.nextInt() - 1;
            scanner.nextLine();
            
            if (num < 0 || num >= metas.size()) {
                throw new IndexOutOfBoundsException("Meta numero " + (num + 1) + " nao existe! Escolha entre 1 e " + metas.size());
            }
            
            Meta meta = metas.get(num);
            if (meta == null) {
                throw new NullPointerException("Meta nao encontrada!");
            }
            
            List<Tarefa> tarefas = meta.getTarefas();
            
            if (tarefas.isEmpty()) {
                System.out.println("AVISO: Nenhuma tarefa nesta meta.");
                return;
            }
            
            System.out.println("\nTarefas:");
            for (int i = 0; i < tarefas.size(); i++) {
                System.out.println((i + 1) + ". " + tarefas.get(i));
            }
            
            System.out.print("Numero da tarefa: ");
            int t = scanner.nextInt() - 1;
            scanner.nextLine();
            
            if (t < 0 || t >= tarefas.size()) {
                throw new IndexOutOfBoundsException("Tarefa numero " + (t + 1) + " nao existe! Escolha entre 1 e " + tarefas.size());
            }
            
            tarefas.get(t).concluir();
            System.out.println("Tarefa concluida com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("ERRO: Entrada invalida! Digite um numero inteiro.");
            scanner.nextLine(); // Limpar buffer
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERRO: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("ERRO: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERRO ao concluir tarefa: " + e.getMessage());
            if (e.getMessage() == null || e.getMessage().isEmpty()) {
                System.out.println("Tipo de erro: " + e.getClass().getSimpleName());
            }
        }
    }
    
    private void adicionarFeedback(Scanner scanner) {
        try {
            System.out.print("Autor do feedback: ");
            String autor = scanner.nextLine();
            if (autor == null || autor.trim().isEmpty()) {
                throw new IllegalArgumentException("Autor do feedback nao pode estar vazio!");
            }
            
            System.out.print("Comentario: ");
            String comentario = scanner.nextLine();
            if (comentario == null || comentario.trim().isEmpty()) {
                throw new IllegalArgumentException("Comentario nao pode estar vazio!");
            }
            
            System.out.print("Data (dd/mm/aaaa): ");
            String data = scanner.nextLine();
            if (data == null || data.trim().isEmpty()) {
                throw new IllegalArgumentException("Data nao pode estar vazia!");
            }
            
            // Validação básica de formato de data
            if (!data.matches("\\d{2}/\\d{2}/\\d{4}")) {
                throw new IllegalArgumentException("Formato de data invalido! Use dd/mm/aaaa (exemplo: 25/12/2024)");
            }
            
            Feedbacks feedback = new Feedbacks(autor, comentario, data);
            admin.adicionarFeedback(feedbacks, feedback);
        } catch (IllegalArgumentException e) {
            System.out.println("ERRO de validacao: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("ERRO: Referencia nula! " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERRO ao adicionar feedback: " + e.getMessage());
            if (e.getMessage() == null || e.getMessage().isEmpty()) {
                System.out.println("Tipo de erro: " + e.getClass().getSimpleName());
            }
        }
    }
    
    private void verFeedbacks() {
        if (feedbacks.isEmpty()) {
            System.out.println("Nenhum feedback cadastrado.");
            return;
        }
        
        System.out.println("\n FEEDBACKS");
        for (Feedbacks f : feedbacks) {
            System.out.println(f);
        }
    }
}