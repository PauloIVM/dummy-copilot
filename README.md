# DummyCopilot 🤖💻

## 1.Introdução

Este é um trabalho em progresso para facilitar/automatizar a digitação e o uso geral de computadores desktop. Atualmente, ele permite que o usuário crie atalhos personalizados e globais (no nível do sistema operacional). Outras funcionalidades serão adicionadas em breve, se Deus quiser.

### 1.1.História/Motivação

A ideia surgiu de uma situação bastante simples: como brasileiro acostumado a usar teclados ABNT2, a adaptação ao meu primeiro notebook com teclado padrão US foi bastante complicada. Sendo leigo na época, desconhecia a existência do layout US internacional, que permite a utilização dos caracteres acentuados do alfabeto português. Assim, por volta de 2016, desenvolvi a primeira versão desse programa com o objetivo simplesmente de digitar caracteres como `ç` e `ê`.

Infelizmente, naquela época, não tinha o hábito de fazer push dos meus códigos no GitHub, então não tenho o histórico do código daquela versão.

Embora essa tenha sido uma motivação inicial um tanto inútil, pois mais tarde descobri o layout US internacional que possibilitava tais acentuações, outros casos interessantes me incentivaram a continuar com o projeto. Geralmente, essas motivações envolviam evitar a digitação de textos longos que não conseguia lembrar de cabeça ou dos quais simplesmente sentia preguiça de digitar. Por exemplo:

- Sempre que precisava fazer push de uma branch, o git solicitava a digitação de `git push --set-upstream origin NOME_DA_BRANCH`. Isso me incomodava muito, pois não queria digitar isso repetidamente e também achava tedioso copiar o comando do terminal para colá-lo logo abaixo. Na minha concepção, se o git já identificava uma nova branch, eu deveria digitar um simples `git push` e ele seguir o fluxo do comando que ele próprio sugeriu. Cheguei a criar um atalho no VSCode para isso, porém ainda me incomodava o fato de que esse atalho não funcionaria se estivesse em um terminal fora do VSCode.
- Diversos outros exemplos nessa linha. Por exemplo, ao me candidatar pra vagas, os formulários grandes pedindo informações bem comuns (e tendo o input HTML sem sugestões), me fazendo digitar diversas vezes informações como 'email', 'linkedin' e por aí vai.

Diante disso, questionava-me: "Realmente preciso de um gerenciador de atalhos que funcione no sistema operacional, seja no terminal, em uma página da web ou em qualquer lugar. Ao preencher um formulário de vagas, gostaria de digitar algo como `. e m l ctrl` e ter meu email inserido automaticamente. Quando precisar fazer push de uma nova branch no git, gostaria de digitar `. g p ctrl` e ter o comando pronto para uso". Esta foi a principal motivação que me levou adiante com o projeto.

Outras motivações surgiram, como análise do uso do teclado, velocidade média de digitação e outras estatísticas similares. Além disso, desejava replicar funcionalidades de teclados como o `ZFA Planck EZ`, que são caros, mas oferecem um nível de personalização absurdo, permitindo até controlar o mouse pelas teclas do teclado. Percebi que muitas dessas funcionalidades poderiam ser reproduzidas pelo **DummyCopilot**, sem a necessidade de adquirir um hardware tão avançado.

Em outubro de 2022, reescrevi esse programa utilizando a stack NodeJS/Typescript. A primeira versão de 2016 havia sido feita em Java. No entanto, essa versão de 2022 ficou muito problemática. Para configurar a aplicação, era necessário instalar várias bibliotecas e fazer ajustes no código de acordo com o hardware utilizado. Além disso, essa versão oferecia suporte apenas para Linux. Ela pode ser encontrada neste [repositório](https://github.com/PauloIVM/-DEPRECATED--dummy-copilot-node), mas aviso que tentar executar esse código em Typescript resultará em muitas dificuldades. Eu mesmo desisti quando precisei trocar de máquina e não consegui configurar o ambiente em um PC com sistema mais atualizado.

Em 2023, retornei às origens, reescrevendo o programa em Java, que pode ser encontrado aqui. Apesar de trabalhar com Typescript no dia a dia, devo confessar que para aplicações Desktop o Java ainda é muito superior. A configuração da aplicação tornou-se muito mais simples, com compatibilidade para a maioria dos sistemas operacionais. 

### 1.2.Origem do Nome

O nome `DummyCopilot` faz alusão a um copiloto "inerte" ou "manequim" (como um manequim articulado que fará exatamente o que você o configurar para fazer), mas ainda assim útil, pois funcionará independentemente do editor de texto, sistema operacional ou outras variáveis.


## 2.Instalação

TODO: Adicionar vídeo tutorial...

Caso não queira saber os detalhes da implementação, mas apenas pegar o arquivo `.jar` e começar a usar, em breve pretendo disponibilizar esse arquivo por meio de uma página web. Até lá, será necessário gerar o `.jar` a partir do código-fonte ou executar o código-fonte em um ambiente de desenvolvimento integrado.

Para rodar o projeto em um IntelliJ, VScode ou similar, não há muitos segredos. Basta clonar o projeto; o seu editor solicitará a instalação das dependências do arquivo `pom.xml` e, em seguida, executar a classe Main em `src/main/java/Main.class`.

Se desejar criar o `.jar` para executar o projeto fora do editor, é bem simples fazer isso também pelo ambiente de desenvolvimento. Como utilizo o VScode com a extensão `Extension Pack for Java`, mostrarei como fazer isso neste editor.

Digite:

```
ctrl+shift+p
```

Isso abrirá uma caixa de busca; digite `Java: Export Jar` nela. O VScode solicitará a seleção das classes necessárias para criar o `.jar`, marque todas, exceto as classes de testes automatizados. Isso criará um `.jar`, que poderá ser movido para outras pastas e executado fora do contexto do projeto; no entanto, junto com o `.jar`, será necessário mover o arquivo `shortcuts.config.json` para a mesma pasta do `.jar`.

Na pasta do `.jar`, execute no terminal:

```
java -jar FILE_NAME.jar
```

O projeto ainda não possui uma interface gráfica, protanto, não será possível executar o programa apenas clicando no `.jar`. Contudo, quando for criada uma interface, para executar o `.jar` pelo clique do mouse, no OS Linux bastará usar um `chmod` no arquivo para dar as devidas permissões, por exemplo:

```
chmod +x FILE_NAME.jar
```

## 3.Como usar

TODO: Adicionar vídeo tutorial...

Tendo feito a configuração e executado o projeto com sucesso, você terá percebido a importância do arquivo `shortcuts.config.json`. Neste arquivo, é onde estão armazenados todos os seus atalhos personalizados.

Cada atalho possui um `trigger` e uma lista de `actions`. Para formar a sequência de um `trigger` de um atalho, basta considerar que a sequência é composta pela representação textual de cada tecla separada por espaços. No caso de teclas pressionadas simultaneamente, é necessário utilizar o sinal `+` entre as duas teclas em vez do espaço.

Por exemplo, suponhamos um `trigger` em que o usuário pressiona a tecla `control`; mantendo o `control` pressionado, pressiona a tecla `espaço`. Em seguida, libera tanto a tecla `espaço` quanto a tecla `control`; e por fim, pressiona e solta a tecla `m`. A representação do `trigger` no formato de texto seria:

```
"trigger": "ctrl+space m"
```

Observe que para criar um `trigger`, é necessário conhecer a representação em texto de cada tecla. Para descobrir isso, você pode iniciar o CLI, selecionar a opção `Start keylogger` e, em seguida, as teclas que você pressionar terão o código textual exibido no terminal. Um detalhe importante é que a representação das teclas se baseia no layout do teclado dos EUA (US International Keyboard Layout), então os nomes das teclas podem ser um pouco diferentes dos símbolos no seu teclado; no entanto, basta copiar e colar a string indicada pelo CLI. Em breve, planejo ter uma funcionalidade para inserir um atalho pelo CLI, facilitando caso não queira inserir diretamente pelo JSON.

Depois de ter o `trigger`, agora você precisa fornecer as ações do seu atalho. Por enquanto, temos apenas dois tipos de ações: `paste` e `sequence`. Na lista de ações que você fornecerá no atalho, é possível combinar ações dos dois tipos. Normalmente, você vai querer apagar algum caractere que foi utilizado para acionar a ação e, em seguida, inserir algo ou executar uma sequência de teclas. No exemplo abaixo, eu finalizo o atalho inicialmente com uma ação que pressiona a tecla `backspace` para apagar a tecla `m` que foi digitada durante o trigger; em seguida, realizo um `paste` de um conteúdo qualquer:

```
// Filename: shortcuts.config.json
[
    {
        "trigger": "ctrl+space m",
        "actions": [
            { "type": "sequence", "keys": "backspace" },
            { "type": "paste", "content": "FOO" }
        ]
    }
]
```

Repare que o `shortcuts.config.json` é um array, e você pode adicionar quantos atalhos quiser.

## 4.Estrutura do Projeto

O projeto possui uma estrutura baseada no Clean Architecture, onde busquei isolar o domínio em entidades e casos de uso, como as camadas de nível mais alto, e abstrair os detalhes como acessos ao sistema operacional, arquivos, entre outros, para camadas externas de nível mais baixo.

![image](./assets/CleanArchitecture.jpg)

A seguir, a estrutura de camadas do projeto:

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

Esses são os `objetos de negócio` da aplicação. Todas as regras de negócio se baseiam nessas entidades.

Esta é a camada mais abstrata; não deve conter referências a camadas além dela mesma.

- Action;
- ClickType;
- KeyId;
- KeyEvent;
- Shortcut;

A `Action` representa uma ação que o programa deve executar. Essas ações fazem parte da entidade `Shortcut`; cada atalho opera com base em um trigger e em uma ação correspondente.

O `ClickType` é o tipo de clique que pode ser feito pelo usuário. Basicamente, o usuário pode pressionar ou soltar uma tecla.

O `KeyId` é a entidade que relaciona cada tecla a um código inteiro.

O `KeyEvent` é uma combinação do `KeyId` e `ClickType`. Assim, sempre que uma tecla for solta ou pressionada, um `KeyEvent` poderá ser usado para representar esse evento.

Por fim, a entidade `Shortcut` é uma composição das outras entidades. Ela representa um atalho, com um trigger (que nada mais é do que uma lista de `KeyEvent`) e possui uma lista de `Action` para serem executadas quando o atalho for ativado.

### 4.2.Usecases (Application Business Rules)

Aqui estão as principais regras de negócio (por enquanto poucas). Esta camada tem a liberdade de importar e manipular as entidades para criar as regras de negócio.

- ActionsExecutor;
- ShortcutsEvaluator;

O `ActionsExecutor` é responsável por executar todas as actions baseado em um identificador para cada ação.

Idealmente, seria preferível que o `ActionsExecutor` não tivesse conhecimento sobre a estrutura de camadas superior, evitando qualquer acoplamento com classes ou interfaces de camadas superiores. No entanto, em certos casos, torna-se difícil modelar essa regra de negócio sem assumir algo na infraestrutura que execute essas ações no sistema. Assim, podemos utilizar a **Inversão de Dependência**; o `ActionsExecutor` especificará um `IRobot` genérico, e a camada de infra precisará declarar um `Robot` que implemente o `IRobot` especificado na camada de usecases. Dessa forma, a **Regra da Dependência** da Clean Architecture não será violada.

O `ShortcutsEvaluator` é responsável por, baseado em uma lista de atalhos e numa entrada de teclas clicadas, determinar se um atalho foi acionado ou não.

### 4.3.Adapters (Interface Adapters)

Essa camada atua como uma ponte entre a infraestrutura e os casos de uso. Aqui, atualmente, estão presentes adaptadores e controladores.

- KeyIdAdapter;
- KeyloggerController;
- ShortcutsController;

O `KeyIdAdapter` é responsável por mapear a entidade KeyId para texto e vice-versa, útil especialmente para representar visualmente os atalhos em um JSON ou no terminal.

O `KeyloggerController` é responsável por associar um `IKeylistener` (que será declarado na camada de infraestrutura, novamente utilizando a inversão de dependência) com o adaptador `KeyIdAdapter` e a entidade `KeyEvent`, expondo uma função a ser executada sempre que uma tecla for pressionada.

O `ShortcutsController` é responsável por associar os casos de uso `ActionsExecutor` e `ShortcutsEvaluator` com um `IKeylistener`. Para isso, também precisará ter uma entrada de quais são os atalhos do usuário, que são fornecidos pela interface `IShortcutsFileParser`, além de outras dependências de camadas internas que se mostraram necessárias para gerenciar todo esse processo. O objetivo desse controlador é aproximar o uso do sistema de atalhos o máximo possível da infraestrutura, sem, no entanto, implementar nada da camada de infraestrutura.

### 4.4.Infra (Libs, OS, Drivers...)

Nesta camada, encontramos a implementação concreta das classes de mais baixo nível, responsáveis por ler arquivos, interagir com o sistema operacional, criar interfaces gráficas, entre outras funções.

- KeyListenner;
- Robot;
- ShortcutsFile;
- composers;
- views (CLI);

O `KeyListener` é responsável por "ouvir" o teclado do usuário. Cada tecla pressionada aciona um método de um objeto dessa classe. Para criar essa classe, precisei utilizar uma biblioteca externa, que foi completamente isolada graças às boas práticas sugeridas no Clean Architecture. Como essa biblioteca possui um mapeamento de teclas diferente do que estou usando, esta implementação do `KeyListener` terá seu próprio adaptador para converter os códigos das teclas da biblioteca para as representações das teclas no meu domínio (`KeyId`, `ClickType` e `KeyEvent`).

O `Robot` é responsável por realizar ações como digitar algo ou mover o mouse no computador do usuário, sem que o próprio usuário tenha realizado essas ações com seu teclado ou mouse. Esta é uma dependência nativa do Java. Alguns argumentam que não seria necessário isolá-la na camada infra. No entanto, optei por fazê-lo, pois entendi que isso manteria as regras de negócios mais puras.

O `ShortcutsFile` é responsável por pegar um arquivo JSON e convertê-lo para uma lista de `Shortcut`. Em breve, ele também terá a responsabilidade de inserir atalhos no JSON, realizando o processo inverso.

As `views` são as aplicações de mais baixo nível, cujo o usuário terá contato direto. Até o momento, tenho apenas uma CLI (interface de linha de comando). Para que a view funcione, ela precisa acessar outras estruturas da camada `infra`, instanciá-las e, em seguida, instanciar algum controlador passando essas dependências da camada `infra`. Para simplificar a construção de um controlador dentro da view, criei também o pacote `composers` na camada infra. Este pacote nada mais é do que `builders` dos controladores, os quais injetam todas as dependências da infraestrutura. Como esses `composers` estão na camada de `infra`, eles não violam a **Regra da Dependência**, pois conhecem apenas a implementação concreta desses elementos.

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
