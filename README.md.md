___

A aplicação desenvolvida consiste em um **Sistema de Controle de Gastos Pessoais**, cujo objetivo principal é permitir que o usuário registre, consulte, edite e exclua informações relacionadas às suas despesas diárias. O sistema foi criado para auxiliar o usuário no acompanhamento de seus gastos, oferecendo uma interface gráfica simples, intuitiva e amigável, construída utilizando a biblioteca **Java Swing**.

O sistema permite realizar operações completas de **CRUD (Create, Read, Update, Delete)** sobre uma entidade principal chamada **Gasto**, que representa um registro financeiro contendo informações como descrição, valor, categoria e data. Dessa forma, o usuário pode manter um histórico organizado de suas despesas e utilizá-lo para análise e controle pessoal.

Além das funcionalidades de manipulação dos dados, a aplicação implementa um **mecanismo simples de autenticação**, utilizando um arquivo local contendo usuários cadastrados. Esse procedimento garante que apenas pessoas autorizadas possam acessar os dados de gastos.

A persistência das informações é feita por meio de **arquivos locais**, que armazenam todos os registros inseridos. O sistema é capaz de carregar os dados automaticamente ao iniciar (caso o arquivo exista) e permite ao usuário salvar manualmente as alterações realizadas.

Durante o uso da aplicação, são aplicadas boas práticas de **tratamento de exceções**, garantindo que erros sejam comunicados ao usuário de forma clara, evitando falhas inesperadas e permitindo uma experiência mais segura. Todas as operações contam com validações e avisos visuais na interface, bem como confirmações nas ações mais críticas, como a exclusão de um registro.

Em resumo, o propósito principal da aplicação é prover um meio simples e eficiente para que o usuário:

- tenha controle sobre seus gastos pessoais;
    
- mantenha seus registros organizados e acessíveis;
    
- visualize suas despesas de forma clara por meio de listas e tabelas;
    
- possa cadastrar, alterar ou remover registros de forma prática;
    
- tenha seus dados preservados localmente, mesmo após encerrar o programa.
    

A aplicação atende aos requisitos mínimos propostos, fornecendo uma interface completa com menu de navegação, formulário de cadastro, listagem de gastos, edição de registros, autenticação, salvamento e carregamento dos dados, além do tratamento de exceções adequado e mensagens amigáveis ao usuário.
___

