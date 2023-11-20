# DummyCopilot

## 1.Introdução

Este é um trabalho em progresso para facilitar/automatizar a digitação ou uso em geral de computadores desktop. Atualmente, ele permite o usuário criar atalhos customizáveis e globais (no nível do sistema operacional), mas outras funcionalidades virão em breve, se Deus quiser.

### 1.1.Motivação

A ideia nasceu de uma motivação muito simples: Sendo um brasileiro e usando teclados ABNT2, quando tive meu primeiro notebook com teclado padrão US, a adaptação foi muito ruim. Sendo ainda um leigo, eu não sabia da existência do layout US internacional (que permitia essas acentuações do alfabeto português). Então, por volta de 2016 eu escrevi a primeira versão desse programa, simplesmente para digitar caracteres como "ç", "ê".

Infelizmente, na época eu não tinha por hábito faser o push dos meus códigos no github, então não tenho o histórico do código dessa época versionado.

Daí, ainda que essa fosse uma motivação um tanto quanto inútil, pois mais tarde eu acabei descobrindo o layout US internacional que me permitia essas acentuações, outras motivações muito interessantes surgiram e me motivaram a dar sequência no projeto. Em geral essas motivações foram muito na linha de evitar a digitação de textos longos e que eu não me lembrava de cabeça, ou q eu simplesmente tinha preguiça de digitá-los. Por exemplo:

- Sempre que eu precisava fazer o push de uma branch, o git me pedia para digitar um `git push --set-upstream origin BRANCH_NAME`. Isso me incomodava muito, eu não queria ter que digitar isso, e tbm achava chato copiar o comando do terminal para colá-lo logo abaixo. Na minha cabeça, se o git já sabia que era uma branch nova, eu deveria simplesmente poder digitar um simples `git push` e ele seguir o fluxo do comando q ele mesmo me sugeriu. Enfim, eu até mesmo criei um atalho no VSCode para isso, mas ainda me incomodava o fato de que se eu estivesse num terminal fora do VSCode esse atalho não funcionaria.
- Diversos outros exemplos nessa linha. Por exemplo, ao me candidatar pra vagas, os formulários grandes pedindo informações bem comuns (e tendo o input HTML sem sugestões), me fazendo digitar diversas vezes informações como 'email', 'linkedin' e por aí vai.

Diante disso, eu ficava muito encucado pensando: Eu realmente preciso de um gerenciador de atalhos que funcione no OS, seja num terminal, numa página web ou o que for. Quando eu for preencher um formulário de vagas, eu quero digitar por exemplo algo como `. e m l ctrl` e ter o email todo digitado. Ao ter que fazer um git push de uma branch nova, eu quero digitar um `. g p ctrl` e ter o comando digitado bem na minha frente. Assim, essa foi a maior motivação e que me fez seguir com o projeto.

Outras motivações surgiram, como um analytics de como é o meu uso do teclado, velocidade média de digitação e coisas do tipo, mas são motivações menos importantes e que eu ainda não implementei.

Por exemplo, hoje existem teclados como o `ZFA Planck EZ`, caros, porém com nível de personalização absurda; é possível até mesmo controlar o mouse pelas teclas do teclado; e, percebi que grande parte dessas funcionalidades poderiam ser reproduzidas via software por esse programa, sem a necessidade de adquirir um hardware desse calibre e gastando nenhum dinheiro.

### 1.2.Origem do Nome

O nome `DummyCopilot` é no sentido dele ser um copiloto 'burro'/'manequim' (tipo um manequim articulado, ele só vai fazer exatamente o que você o configurar para fazer), mas ainda assim útil pois ele irá funcionar independente do editor de texto, OS ou o que for.

Para usar o software também ficou muito simples e com boa compatibilidade nos diversos OS atuais. Seguem as explicações no tópico de instalação.


## 2.Instalação

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

## 3.Como usar

TODO: ...

## 4.Estrutura do Projeto

O projeto tem uma estrutura baseada no Clean-Architecture; onde tentei isolar o domínio em entidades e usecases, como sendo as duas camadas de mais alto nível, e abstraindo os demais detalhes como acessos ao OS, arquivos e etc para camadas externas de baixo nível.

![image](./assets/CleanArchitecture.jpg)

A seguir, como ficou minha hierarquia de camadas:

```
    .....................................................
    . Main.java                                         .
    .   .............................................   .
    .   . infra                                     .   .
    .   .   .....................................   .   .
    .   .   . adapters                          .   .   .
    .   .   .   .............................   .   .   .
    .   .   .   . usecases                  .   .   .   .
    .   .   .   .   .....................   .   .   .   .
    .   .   .   .   . entities          .   .   .   .   .
    .   .   .   .   .....................   .   .   .   .
    .   .   .   .............................   .   .   .
    .   .   .....................................   .   .
    .   .............................................   .
    .....................................................
```

```
  src
    ├── Main.java
    ├── infra
       ├── composers
       ├── keylistenner
       ├── robot
       ├── shortcutsfile
       └── views
    ├── adapters
       ├── keyIdAdapter
       ├── keyloggerController
       └── shortcutsController
    ├── usecases
       ├── actionsExecutor
       └── shortcutsEvaluator
    └── entities
       ├── action
       ├── clickType
       ├── keyEvent
       ├── keyId
       └── shortcut
```

### 4.1.Entities (Business Rules)

Estes são os `objetos de negócio` da aplicação. Todas as regras de negócios se baseiam nestas entidades.

Esta é a camada mais abstrata; portanto, não deve ter nenhuma referência à camadas mais externas, portanto nenhuma referência à nenhuma camada além de si própria.

- Action;
- ClickType;
- KeyId;
- KeyEvent;
- Shortcut;

A `Action` representa uma ação que o programa deve executar. Essas ações fazem parte da entidade `Shortcut`; cada atalho funciona baseado num trigger e numa respectiva ação.

O `ClickType` é o tipo de clique que pode ser feito pelo user. Basicamente, o user pode pressionar ou soltar uma tecla.

O `KeyId` é a entidade que relaciona cada tecla a um código inteiro.

O `KeyEvent` é uma união do `KeyId` e `ClickType`. Assim, sempre que uma tecla for solta ou pressionada, um `KeyEvent` poderá ser usado para representar este evento.

Por fim, a entidade `Shortcut` é uma união das demais entidades. Esta entidade representa um atalho, com um trigger, que nada mais é do que uma lista de `KeyEvent`; e, este atalho possui uma lista de `Action` para executar quando for disparado.

### 4.2.Usecases (Application Business Rules)

Aqui temos as principais regras de negócio (por enquanto poucas). Esta camada tem a liberdade de importar e manipular as entidades, para assim criar as regras de negócio.

- ActionsExecutor;
- ShortcutsEvaluator;

O `ActionsExecutor` é responsável por executar todas as entidades `actions` q possam existir, baseado num identificador de cada respectiva action.

Idealmente, o bom seria se o `ActionsExecutor` não conhecesse nem mesmo a ideia de uma estrutura de uma camada superior, muito menos ter qualquer acoplamento com classes ou interfaces de camadas superiores. Contudo, esse é um exemplo em que fica difícil modelar essa regra de negócio sem pressupor algo na infra que execute essas ações no sistema. Assim, nós podemos fazer uso da **inversão de dependência**; O próprio `ActionsExecutor` irá especificar um `IRobot` genérico; e a camada de `infra` precisará declarar um `Robot` que implemente o `IRobot` especificado na camada `usecases`. Desta forma, a **Regra da Dependência** do clean-architecture não é violada.

O `ShortcutsEvaluator` é responsável por, baseado em uma lista de atalhos e numa entrada de teclas clicadas, nos retornar se um atalho foi disparado ou não.

### 4.3.Adapters (Interface Adapters)

Esta camada faz uma ponte entre a infra e os usecases. Aqui por hora temos adapters e controllers.

- KeyIdAdapter;
- KeyloggerController;
- ShortcutsController;

O `KeyIdAdapter` é responsável por mapear a entidade `KeyId` para texto e vice-versa; sendo útil em especial para representar visualmente os atalhos num json ou no terminal.

O `KeyloggerController` é responsável por 'casar' um `IKeylistenner` (que será declarado na camada de infra, novamente a inversão de dependência) com o adapter `KeyIdAdapter` e a entidade `KeyEvent`; e, expor uma function para ser executada sempre que uma tecla for clicada.

O `ShortcutsController` é responsável por 'casar' os usecases `ActionsExecutor` e `ShortcutsEvaluator` com um `IKeylistenner`. Para isso ele também precisara ter uma entrada de quais são os atalhos do user, que vêm pela interface `IShortcutsFileParser`; e também possui algumas outras dependências de camadas internas que se fizeram necessárias para gerenciar tudo isso. O objetivo desse controller é aproximar ao máximo o uso do sistema de atalhos da infra, mas sem implementar nada ainda da camada de infra.

### 4.4.Infra (Libs, OS, Drivers...)

Esta camada já é onde fica a implementação concreta das classes de mais baixo nível, que irão ler arquivos, interagir com o OS, criar interfaces gráficas e etc.

- KeyListenner;
- Robot;
- ShortcutsFile;
- composers;
- views (CLI);

O `KeyListenner` é responsável por 'ouvir' o teclado do user. Cada tecla clicada irá disparar um método de um objeto dessa classe. Para criar essa classe, eu precisei utilizar um lib externa, que ficou completamente isolada graças às boas práticas sugeridas no clean-arch. Como essa lib possui um mapeamento de teclas distinto do que eu estou usando, esta implementação do `KeyListenner` vai ter seu próprio adapter convertendo os códigos das teclas da lib para as representações das teclas no meu domínio (`KeyId`, `ClickType` e `KeyEvent`).

O `Robot` é responsável por fazer com que a aplicação consiga teclar algo, mover o mouse e etc no PC do usuário, sem que o usuário mesmo tenha feito isso pelo seu prórpio teclado ou mouse. Esta é uma dependência do próprio Java. Há quem diga que não seria então necessário isolá-la na camada `infra`. Contudo, quis fazer assim, pois entendi que isto deixaria as regras de negócios mais puras.

O `ShortcutsFile` é responsável por pegar um arquivo json e convertê-lo para uma lista de `Shortcut`. Muito em breve ele também ganhará responsabilidades de inserir atalhos no json, o caminho contrário.

As `view` são as aplicações de mais baixo nível, que o usuário terá contato. Por hora, tenho apenas uma CLI (command-line interface). Para a view funcionar, em tese ela precisa pegar outras estruturas da camada `infra`, instanciá-las, e em seguida instanciar algum controller passando essas dependências de `infra` para o controller. Para não ficar muito complexo o build de um controller dentro da view, dentro da camada `infra` eu criei também o package `composers`, que nada mais é do que builders dos controllers, que injetam todas as dependências de infra; como esses composers estão na camada de `infra`, eles não violam a **Regra da Dependência** por conhecerem a implementação concreta de tais elementos.

## 5.Planejamento de próximos passos:

- Criar mais testes automatizados;
- Conferir a visibilidade dos pacotes, métodos public desnecessários;
- Criar feature para inserir um atalho pelo terminal;
- Caso não exista um arquivo de configurações, gerar automaticamente;
- Criar feature para permitir mover o mouse pelo teclado; ex.:
    { "trigger": "ctrl+space m", "actions": [{ "type": "setMode", "mode": "mouse" }] }
    {
        "trigger": "left", "when": { "mode": "mouse" },
        "actions": [{"type": "mouseMove", "direction": "left", "px": 10 }]
    }
- Criar interface gráfica;
- Criar um analytics que me mostra dados como: média de velocidade de digitção ao longo dos dias; palavras mais digitadas; atalhos existentes que poderiam estar sendo usados e não foram; etc... 