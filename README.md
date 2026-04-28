# 🎯 SistemaPDI - Sistema de Planejamento e Desenvolvimento Individual

Bem-vindo ao **SistemaPDI**! Um sistema bacana pra gerenciar seus goals, metas e tarefas de forma organizada. Quer acompanhar seu desenvolvimento profissional? Esse projeto é pra você!

## 🔥 O que é?

É um sistema em Java que te ajuda a:
- ✅ **Criar metas** com prioridade (baixa, média, alta)
- ✅ **Rastrear tarefas** dentro de cada meta
- ✅ **Atualizar status** das metas (pendente, em progresso, concluída)
- ✅ **Gerar relatórios** do seu progresso
- ✅ **Adicionar feedbacks** (função exclusiva pra admins)

## 👥 Perfis de Usuário

### 👤 Usuário Comum
- Criar metas
- Listar metas
- Adicionar e concluir tarefas
- Visualizar relatórios

### 🛡️ Administrador
- Todas as funções do usuário comum
- Adicionar feedbacks
- Visualizar feedbacks

## 🚀 Como Usar

### Compilar o projeto
```bash
javac *.java
```

### Rodar o sistema
```bash
java Main
```

### Escolha seu perfil
```
SISTEMA DE PDI
1 - Login como Administrador
2 - Login como Usuario
Escolha: 
```

## 📋 Estrutura do Projeto

```
SistemaPDII/
├── Main.java              # Ponto de entrada do sistema
├── SistemaPDI.java        # Lógica principal do menu
├── Meta.java              # Classe de metas
├── Tarefa.java            # Classe de tarefas
├── Feedbacks.java         # Classe de feedbacks
├── Administrador.java     # Classe do admin
├── Relatorio.java         # Gerador de relatórios
├── Prioridade.java        # Enum de prioridades
├── StatusMeta.java        # Enum de status
├── TipoUsuario.java       # Enum de tipos de usuário
├── TratamentoExcecoes.java # Tratamento de exceções
└── GUIA_LANCAR_ERROS.md   # Documentação de erros
```

## 🎮 Menu Principal

**Usuário:**
1. Criar Meta
2. Listar Metas
3. Atualizar Status Meta
4. Adicionar Tarefa
5. Concluir Tarefa
6. Gerar Relatório
0. Sair

**Administrador:**
- Todas as opções do usuário +
- Adicionar Feedback
- Ver Feedbacks

## 💡 Exemplo de Uso

1. Escolha "Usuário"
2. Crie uma meta: "Aprender Java"
3. Escolha prioridade (1-BAIXA, 2-MEDIA, 3-ALTA)
4. Adicione tarefas como "Estudar POO" e "Fazer projetos"
5. Atualize o status conforme progride
6. Gere um relatório pra acompanhar tudo

## 🛡️ Tratamento de Erros

O sistema é bem robusto e trata vários tipos de erro:
- ❌ Entrada inválida (não é número?)
- ❌ Campos vazios
- ❌ Índices fora dos limites
- ❌ Referências nulas

Pra mais detalhes sobre como os erros funcionam, vê o arquivo `GUIA_LANCAR_ERROS.md`! 📚

## 🛠️ Tecnologias

- **Linguagem:** Java
- **Tipo:** Aplicação Console
- **Padrão:** OOP (Programação Orientada a Objetos)

## 📝 Notas

- Todas as metas e tarefas são armazenadas em memória (não persiste entre execuções)
- O sistema valida todas as entradas do usuário
- Feedbacks só podem ser adicionados por administradores

## 🤝 Quer Contribuir?

Feel free pra sugerir melhorias, reportar bugs ou fazer um PR! 

## 📄 Licença

Open source! Use como quiser! 🎉

---

**Desenvolvido com ❤️ em Java**

Qualquer dúvida, é só chamar! 😎