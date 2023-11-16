# DummyCopilot

Este é um trabalho em progresso para facilitar/automatizar o uso de computadores desktop. Atualmente, ele permite o usuário criar atalhos customizaveis e globais (no nível do sistema operacional), mas outras funcionalidades virão em breve, se Deus quiser.

A ideia nasceu de uma motivação muito simples: Sendo um brasileiro e usando teclados ABNT2, quando tive meu primeiro notebook com teclado padrão US, a adaptação foi muito ruim. Me desagradava muito ter que usar o layout ABNT2 em um teclado US e as teclas todas diferentes do símbolo físico no teclado, e também era bastante chato usar o layout US e não ter teclas para caracteres como "ç", "ê" e etc.

Assim, inicialmente a motivação era permitir a criação de atalhos globais para digitar esses caracteres do ABNT2, mas mantendo o layout US do teclado. Você perceberá que o código vêm com alguns atalhos padrões, por exemplo `. c ctrl` irá digitar um `ç`, a menos que você mude ou remova esse atalho.

Com o tempo eu percebi que existem muitas outras automações interessantes, e estou dando uma nova atenção para o projeto. Por exemplo, hoje existem teclados como o `ZFA Planck EZ`, caros, porém com nível de personalização absurda; é possível até mesmo controlar o mouse pelas teclas do teclado; e, eu percebi que grande parte dessas funcionalidades poderiam ser reproduzidas via software por esse programa, sem a necessidade de adquirir um hardware desse calibre e gastando nenhum dinheiro.

O nome `DummyCopilot` é no sentido dele ser um copiloto 'burro'/'manequim' (tipo um manequim articulado, ele só vai fazer exatamente o que você o configurar para fazer), mas ainda assim útil pois ele irá funcionar independente do editor de texto, OS ou o que for.

Para usar o software também ficou muito simples e com boa compatibilidade nos diversos OS atuais. Seguem as explicações no tópico de instalação.


## Instalação

Se você não deseja saber os detalhes da implementação, mas apenas pegar o arquivo `.jar` e sair em usando, em breve eu devo criar uma página web para disponiblizar esse arquivo. Até lá, você precisará gerar o `.jar` pelo código-fonte, ou rodar o código fonte em um ambiente de desenvolvimento integrado.

Para rodar o projeto em um IntelliJ, VScode ou similar, não tem muito segredo. Basta clonar o projeto; o seu editor irá pedir para instalar as dependências do arquivo `pom.xml`, e em seguida pasta executar a classe Main em `src/main/java/Main.class`.

Se você quiser criar o `.jar` para executar o projeto fora do editor, é bem simples de fazer isso pelo seu ambiente de desenvolvimento também. Como eu estou usando o VScode com a extensão `Extension Pack for Java`, vou mostrar como fazer isso neste editor.

Digite:

```
ctrl+shift+p
```

Será aberto um input de busca, digite nele `Java: Export Jar`. O VScode pedir para você selecionar as classes necessárias para criar o `.jar`, marque todas menos as classes de testes automatizados. Com isso será criado um `.jar`, que você pode movar para outras pastas e executar fora do escopo do projeto; contudo, juntamente com o `.jar`, você precisará mover o arquivo `shortcuts.config.json` para a mesma pasta do `.jar`.

Na pasta do `.jar`, execute pelo terminal:

```
java -jar FILE_NAME.jar
```

O projeto ainda não tem uma interface gráfica, então você não conseguirá executar o programa apenas clicando no `.jar`. Contudo, assim que for criado uma interface, para executar o `.jar` pelo clique do mouse, no OS Linux você precisará usar um `chmod` no arquivo para dar as devidas permissões, por exemplo:

```
chmod +x FILE_NAME.jar
```

## Planejamento de próximos passos:

- Criar testes automatizados;
- Melhorar nomes de classes e métodos em geral;
- Melhorar tratamento de exeções no código;
- Melhorar a visibilidade dos pacotes, acabou ficando quase tudo public;
- Inserir o injector-factory no código;
- Criar feature para inserir um atalho pelo terminal;
- Caso não exista um arquivo de configurações, gerar automaticamente;
- Criar feature para permitir mover o mouse pelo teclado;
- Criar interface gráfica;
- Criar um analytics que me mostra dados como: média de velocidade de digitção ao longo dos dias; palavras mais digitadas; atalhos existentes que poderiam estar sendo usados e não foram; etc... 
