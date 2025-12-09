# Guia: Como Lançar Erros e Exibir Mensagens

Este documento explica como lançar cada tipo de exceção para que as mensagens de erro sejam exibidas corretamente no sistema.

## Tipos de Exceções e Como Lançá-las

### 1. **IllegalArgumentException** - Validação de Dados

Use quando o valor fornecido é inválido ou não atende aos critérios esperados.

```java
// Exemplo: Validar prioridade
if (prioridade < 1 || prioridade > 3) {
    throw new IllegalArgumentException("Prioridade deve ser 1, 2 ou 3!");
}

// Exemplo: Validar string vazia
if (texto == null || texto.trim().isEmpty()) {
    throw new IllegalArgumentException("Campo nao pode estar vazio!");
}

// Exemplo: Validar formato de data
if (!data.matches("\\d{2}/\\d{2}/\\d{4}")) {
    throw new IllegalArgumentException("Formato de data invalido! Use dd/mm/aaaa");
}
```

**Como capturar:**
```java
catch (IllegalArgumentException e) {
    System.out.println("ERRO de validacao: " + e.getMessage());
}
```

---

### 2. **InputMismatchException** - Entrada Inválida do Scanner

Use quando o usuário digita um tipo de dado diferente do esperado (ex: texto quando espera número).

```java
// Não precisa lançar manualmente - o Scanner lança automaticamente
// Mas você deve capturar:
try {
    int numero = scanner.nextInt();
} catch (InputMismatchException e) {
    System.out.println("ERRO: Entrada invalida! Digite um numero inteiro.");
    scanner.nextLine(); // IMPORTANTE: Limpar o buffer
}
```

**Importante:** Sempre limpe o buffer com `scanner.nextLine()` após capturar esta exceção!

---

### 3. **IndexOutOfBoundsException** - Índice Fora dos Limites

Use quando tenta acessar um índice que não existe em uma lista/array.

```java
// Exemplo: Validar índice antes de usar
if (indice < 0 || indice >= lista.size()) {
    throw new IndexOutOfBoundsException(
        "Indice " + indice + " invalido! Escolha entre 0 e " + (lista.size() - 1)
    );
}

// Ou para números começando em 1:
if (numero < 1 || numero > lista.size()) {
    throw new IndexOutOfBoundsException(
        "Numero " + numero + " nao existe! Escolha entre 1 e " + lista.size()
    );
}
```

**Como capturar:**
```java
catch (IndexOutOfBoundsException e) {
    System.out.println("ERRO: " + e.getMessage());
}
```

---

### 4. **NullPointerException** - Referência Nula

Use quando um objeto obrigatório é nulo.

```java
// Exemplo: Validar objeto antes de usar
if (meta == null) {
    throw new NullPointerException("Meta nao pode ser nula!");
}

if (lista == null) {
    throw new NullPointerException("Lista nao foi inicializada!");
}
```

**Como capturar:**
```java
catch (NullPointerException e) {
    System.out.println("ERRO: " + e.getMessage());
}
```

---

### 5. **IllegalStateException** - Estado Inválido

Use quando o objeto está em um estado que não permite a operação.

```java
// Exemplo: Lista vazia quando precisa ter itens
if (tarefas.isEmpty()) {
    throw new IllegalStateException("Nenhuma tarefa cadastrada nesta meta!");
}

// Exemplo: Objeto já processado
if (processado) {
    throw new IllegalStateException("Este item ja foi processado!");
}
```

**Como capturar:**
```java
catch (IllegalStateException e) {
    System.out.println("AVISO: " + e.getMessage());
}
```

---

## Padrão Completo de Try-Catch

### Estrutura Recomendada:

```java
try {
    // 1. Validar entradas
    if (valor == null || valor.isEmpty()) {
        throw new IllegalArgumentException("Valor nao pode estar vazio!");
    }
    
    // 2. Validar índices
    if (indice < 0 || indice >= lista.size()) {
        throw new IndexOutOfBoundsException("Indice invalido!");
    }
    
    // 3. Validar estado
    if (lista.isEmpty()) {
        throw new IllegalStateException("Lista vazia!");
    }
    
    // 4. Executar operação
    lista.get(indice).fazerAlgo();
    
} catch (InputMismatchException e) {
    System.out.println("ERRO: Entrada invalida! Digite um numero inteiro.");
    scanner.nextLine(); // Limpar buffer
    
} catch (IllegalArgumentException e) {
    System.out.println("ERRO de validacao: " + e.getMessage());
    
} catch (IndexOutOfBoundsException e) {
    System.out.println("ERRO: " + e.getMessage());
    
} catch (NullPointerException e) {
    System.out.println("ERRO: " + e.getMessage());
    
} catch (IllegalStateException e) {
    System.out.println("AVISO: " + e.getMessage());
    
} catch (Exception e) {
    System.out.println("ERRO inesperado: " + e.getMessage());
    if (e.getMessage() == null || e.getMessage().isEmpty()) {
        System.out.println("Tipo de erro: " + e.getClass().getSimpleName());
    }
}
```

---

## Boas Práticas

### ✅ FAÇA:

1. **Sempre forneça mensagens descritivas:**
   ```java
   throw new IllegalArgumentException("Prioridade deve ser 1, 2 ou 3!");
   ```

2. **Limpe o buffer após InputMismatchException:**
   ```java
   catch (InputMismatchException e) {
       scanner.nextLine(); // Limpar buffer
   }
   ```

3. **Valide antes de usar:**
   ```java
   if (objeto == null) {
       throw new NullPointerException("Objeto nao pode ser nulo!");
   }
   ```

4. **Use exceções específicas primeiro, genérica por último:**
   ```java
   catch (IllegalArgumentException e) { ... }  // Específica
   catch (Exception e) { ... }                 // Genérica (última)
   ```

### ❌ NÃO FAÇA:

1. **Não deixe catch vazio:**
   ```java
   catch (Exception e) {
       // Vazio - erro nunca será visto!
   }
   ```

2. **Não use Exception genérica sem mensagem:**
   ```java
   catch (Exception e) {
       System.out.println("Erro"); // Muito vago!
   }
   ```

3. **Não esqueça de limpar o buffer:**
   ```java
   catch (InputMismatchException e) {
       // Falta scanner.nextLine()!
   }
   ```

---

## Exemplos Práticos do Sistema

### Exemplo 1: Criar Meta

```java
try {
    String titulo = scanner.nextLine();
    if (titulo == null || titulo.trim().isEmpty()) {
        throw new IllegalArgumentException("Titulo nao pode estar vazio!");
    }
    
    int prioridade = scanner.nextInt();
    if (prioridade < 1 || prioridade > 3) {
        throw new IllegalArgumentException("Prioridade deve ser 1, 2 ou 3!");
    }
    
    // Criar meta...
    
} catch (InputMismatchException e) {
    System.out.println("ERRO: Digite um numero inteiro para a prioridade.");
    scanner.nextLine();
} catch (IllegalArgumentException e) {
    System.out.println("ERRO: " + e.getMessage());
}
```

### Exemplo 2: Selecionar Item da Lista

```java
try {
    int numero = scanner.nextInt();
    int indice = numero - 1;
    
    if (indice < 0 || indice >= lista.size()) {
        throw new IndexOutOfBoundsException(
            "Numero " + numero + " invalido! Escolha entre 1 e " + lista.size()
        );
    }
    
    return lista.get(indice);
    
} catch (InputMismatchException e) {
    System.out.println("ERRO: Digite um numero inteiro.");
    scanner.nextLine();
} catch (IndexOutOfBoundsException e) {
    System.out.println("ERRO: " + e.getMessage());
}
```

### Exemplo 3: Validar Data

```java
try {
    String data = scanner.nextLine();
    
    if (data == null || data.trim().isEmpty()) {
        throw new IllegalArgumentException("Data nao pode estar vazia!");
    }
    
    if (!data.matches("\\d{2}/\\d{2}/\\d{4}")) {
        throw new IllegalArgumentException(
            "Formato invalido! Use dd/mm/aaaa (exemplo: 25/12/2024)"
        );
    }
    
    // Usar data...
    
} catch (IllegalArgumentException e) {
    System.out.println("ERRO: " + e.getMessage());
}
```

---

## Resumo das Correções Feitas

✅ **Main.java:**
- Adicionado tratamento específico para `InputMismatchException`
- Validação de opção (1 ou 2)
- Mensagens de erro mais claras

✅ **SistemaPDI.java:**
- Validações em todos os métodos
- Tratamento específico para cada tipo de exceção
- Mensagens descritivas para cada erro
- Limpeza de buffer quando necessário

✅ **TratamentoExcecoes.java:**
- Loops infinitos corrigidos (usando `while(true)`)
- Mensagens de erro padronizadas com "ERRO:" ou "AVISO:"
- Validações mais robustas
- Tratamento específico para cada tipo de exceção

---

## Testando os Erros

Para testar se as mensagens aparecem corretamente:

1. **InputMismatchException:** Digite texto quando pedir número
2. **IllegalArgumentException:** Digite valores fora do intervalo esperado
3. **IndexOutOfBoundsException:** Escolha um número maior que a lista
4. **NullPointerException:** Tente usar objeto nulo
5. **IllegalStateException:** Tente operar em lista vazia

Todas as mensagens agora devem aparecer corretamente! 🎉

