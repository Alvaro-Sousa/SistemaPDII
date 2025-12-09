import java.util.Scanner;
import java.util.List;
import java.util.InputMismatchException;

public class TratamentoExcecoes {
    
    // entrada de int
    public static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("ERRO: Entrada invalida! Digite um numero inteiro.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("ERRO: " + e.getMessage());
                if (e.getMessage() == null || e.getMessage().isEmpty()) {
                    System.out.println("Tipo de erro: " + e.getClass().getSimpleName());
                }
                scanner.nextLine();
            }
        }
    }
    

    public static String lerString(Scanner scanner, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String valor = scanner.nextLine();
                if (valor == null || valor.trim().isEmpty()) {
                    throw new IllegalArgumentException("Entrada vazia nao permitida! Digite um valor valido.");
                }
                return valor;
            } catch (IllegalArgumentException e) {
                System.out.println("ERRO: " + e.getMessage());
                // ele vai  tentar novamente
            } catch (Exception e) {
                System.out.println("ERRO ao ler texto: " + e.getMessage());
                if (e.getMessage() == null || e.getMessage().isEmpty()) {
                    System.out.println("Tipo de erro: " + e.getClass().getSimpleName());
                }
            }
        }
    }
    
    // numero fora da lista
    public static int selecionarItemLista(Scanner scanner, int tamanhoLista, String mensagem) {
        while (true) {
            try {
                int numero = lerInteiro(scanner, mensagem);
                int indice = numero - 1;
                
                if (tamanhoLista <= 0) {
                    throw new IllegalStateException("Lista esta vazia! Nao ha itens para selecionar.");
                }
                
                if (indice < 0 || indice >= tamanhoLista) {
                    throw new IndexOutOfBoundsException("Numero " + numero + " fora do intervalo valido. Escolha entre 1 e " + tamanhoLista);
                }
                return indice;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("ERRO: " + e.getMessage());
                // tenta continuar
            } catch (IllegalStateException e) {
                System.out.println("ERRO: " + e.getMessage());
                return -1;
            } catch (Exception e) {
                System.out.println("ERRO inesperado: " + e.getMessage());
                if (e.getMessage() == null || e.getMessage().isEmpty()) {
                    System.out.println("Tipo de erro: " + e.getClass().getSimpleName());
                }
                return -1;
            }
        }
    }
    
    // na hora de criar Meta
    public static Meta criarMetaSegura(Scanner scanner) {
        try {
            String titulo = lerString(scanner, "Titulo da meta: ");
            String descricao = lerString(scanner, "Descricao: ");
            
            System.out.println("Prioridade (1-BAIXA, 2-MEDIA, 3-ALTA): ");
            int p = lerInteiro(scanner, "Escolha: ");
            
            if (p < 1 || p > 3) {
                throw new IllegalArgumentException("Prioridade deve ser 1, 2 ou 3!");
            }
            
            Prioridade prioridade = (p == 1) ? Prioridade.BAIXA : 
                                    (p == 2) ? Prioridade.MEDIA : Prioridade.ALTA;
            
            Meta meta = new Meta(titulo, descricao, prioridade);
            System.out.println("Meta criada com sucesso!");
            return meta;
            
        } catch (IllegalArgumentException e) {
            System.out.println("ERRO de validacao: " + e.getMessage());
            return null;
        } catch (NullPointerException e) {
            System.out.println("ERRO: Referencia nula! " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("ERRO ao criar meta: " + e.getMessage());
            if (e.getMessage() == null || e.getMessage().isEmpty()) {
                System.out.println("Tipo de erro: " + e.getClass().getSimpleName());
            }
            return null;
        }
    }
    
    // pra atualizar status
    public static StatusMeta selecionarStatus(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Novo status (1-PENDENTE, 2-EM_PROGRESSO, 3-CONCLUIDA): ");
                int s = lerInteiro(scanner, "Escolha: ");
                
                if (s < 1 || s > 3) {
                    throw new IllegalArgumentException("Status deve ser 1, 2 ou 3!");
                }
                
                return (s == 1) ? StatusMeta.PENDENTE : 
                       (s == 2) ? StatusMeta.EM_PROGRESSO : StatusMeta.CONCLUIDA;
                       
            } catch (IllegalArgumentException e) {
                System.out.println("ERRO: " + e.getMessage());

            } catch (Exception e) {
                System.out.println("ERRO ao selecionar status: " + e.getMessage());
                if (e.getMessage() == null || e.getMessage().isEmpty()) {
                    System.out.println("Tipo de erro: " + e.getClass().getSimpleName());
                }
                return StatusMeta.PENDENTE;
            }
        }
    }
    
    // pra adicionar tarefa
    public static boolean adicionarTarefaSegura(Meta meta, Scanner scanner) {
        try {
            if (meta == null) {
                throw new NullPointerException("Meta nao pode ser nula!");
            }
            
            String descricao = lerString(scanner, "Descricao da tarefa: ");
            Tarefa tarefa = new Tarefa(descricao);
            meta.adicionarTarefa(tarefa);
            System.out.println("Tarefa adicionada com sucesso!");
            return true;
            
        } catch (NullPointerException e) {
            System.out.println("ERRO: " + e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("ERRO de validacao: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("ERRO ao adicionar tarefa: " + e.getMessage());
            if (e.getMessage() == null || e.getMessage().isEmpty()) {
                System.out.println("Tipo de erro: " + e.getClass().getSimpleName());
            }
            if (e.getCause() != null) {
                System.out.println("Causa: " + e.getCause().getMessage());
            }
            return false;
        }
    }
    
    // pra concluir tarefa
    public static boolean concluirTarefaSegura(Meta meta, Scanner scanner) {
        try {
            if (meta == null) {
                throw new NullPointerException("Meta nao encontrada!");
            }
            
            List<Tarefa> tarefas = meta.getTarefas();
            
            if (tarefas == null) {
                throw new NullPointerException("Lista de tarefas nao encontrada!");
            }
            
            if (tarefas.isEmpty()) {
                throw new IllegalStateException("Nenhuma tarefa nesta meta!");
            }
            
            System.out.println("\nTarefas:");
            for (int i = 0; i < tarefas.size(); i++) {
                System.out.println((i + 1) + ". " + tarefas.get(i));
            }
            
            int indice = selecionarItemLista(scanner, tarefas.size(), "Numero da tarefa: ");
            
            if (indice == -1) {
                System.out.println("ERRO: Nao foi possivel selecionar a tarefa.");
                return false;
            }
            
            if (indice < 0 || indice >= tarefas.size()) {
                throw new IndexOutOfBoundsException("Indice de tarefa invalido: " + indice);
            }
            
            Tarefa tarefa = tarefas.get(indice);
            if (tarefa == null) {
                throw new NullPointerException("Tarefa nao encontrada no indice " + indice);
            }
            
            tarefa.concluir();
            System.out.println("Tarefa concluida com sucesso!");
            return true;
            
        } catch (NullPointerException e) {
            System.out.println("ERRO: " + e.getMessage());
            return false;
        } catch (IllegalStateException e) {
            System.out.println("AVISO: " + e.getMessage());
            return false;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERRO: Tarefa invalida - " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("ERRO inesperado ao concluir tarefa: " + e.getMessage());
            if (e.getMessage() == null || e.getMessage().isEmpty()) {
                System.out.println("Tipo de erro: " + e.getClass().getSimpleName());
            }
            e.printStackTrace();
            return false;
        }
    }
    
    // pra adicionar feedback
    public static Feedbacks criarFeedbackSeguro(Scanner scanner) {
        while (true) {
            try {
                String autor = lerString(scanner, "Autor do feedback: ");
                String comentario = lerString(scanner, "Comentario: ");
                String data = lerString(scanner, "Data (dd/mm/aaaa): ");
                
                // ver se a data ta certa
                if (!data.matches("\\d{2}/\\d{2}/\\d{4}")) {
                    throw new IllegalArgumentException("Formato de data invalido! Use dd/mm/aaaa (exemplo: 25/12/2024)");
                }
                
                Feedbacks feedback = new Feedbacks(autor, comentario, data);
                System.out.println("Feedback criado com sucesso!");
                return feedback;
                
            } catch (IllegalArgumentException e) {
                System.out.println("ERRO de validacao: " + e.getMessage());

            } catch (NullPointerException e) {
                System.out.println("ERRO: Referencia nula! " + e.getMessage());
                return null;
            } catch (Exception e) {
                System.out.println("ERRO ao criar feedback: " + e.getMessage());
                if (e.getMessage() == null || e.getMessage().isEmpty()) {
                    System.out.println("Tipo de erro: " + e.getClass().getSimpleName());
                }
                e.printStackTrace();
                return null;
            }
        }
    }
    
}