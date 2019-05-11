![Build](https://travis-ci.org/emmanuelneri/programacao2.svg?branch=master)

# Exemplos da matéria Programação II

#### Pré requisitos

- JDK 8
- Maven 3+

Obs: Caso queira conhecer um pouco mais sobre o Maven, acesse esse [tutorial](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html) e para quem utiliza a IDE Intellij assista o [vídeo](https://www.youtube.com/watch?v=pt3uB0sd5kY).

#### Baixando o projeto

Há duas alternativas para baixar o projeto:

- 1a alternativa: Via git

    Executar o seguinte comando no terminal: `git clone https://github.com/emmanuelneri/programacao2.git`

    Obs: é pré requisito ter o [git](https://git-scm.com/downloads) instalado.
    
- 2a alternativa: Via donwload

   [Baixe aqui](https://github.com/emmanuelneri/programacao2/archive/master.zip) o projeto zipado e descompacte na sequência.
   
   
#### Importe o projeto na sua IDE

Como é utilizado o Maven no projeto programacao2, o projeto deve ser importador como projeto Maven na sua IDE.

No IntelliJ:
  `File > Open > Procure o pom.xml > OK > Open as Project`
  
#### Faça o build do projeto

Há duas alternativas para fazer o build do projeto:

- 1a alternativa: Via comando Maven

    Executar o seguinte comando no terminal: `mvn clean install`

    Obs: é pré requisito ter o comando `mvn` do Maven configurado.
    
- 2a alternativa: Via IDE

   No IntelliJ:
  `Maven Project > Lifecycle > Selecione > Clean + Install`
  
O Resultado esperado é de Build Success como demonstrado abaixo:

```
[INFO] --- maven-install-plugin:2.4:install (default-install) @ programacao2 ---
[INFO] Installing /Users/facec/Downloads/programacao2/target/programacao2-1.0-SNAPSHOT.jar to /Users/facec/.m2/repository/br/com/facec/programacao2/1.0-SNAPSHOT/programacao2-1.0-SNAPSHOT.jar
[INFO] Installing /Users/facec/Downloads/programacao2/pom.xml to /Users/facec/.m2/repository/br/com/facec/programacao2/1.0-SNAPSHOT/programacao2-1.0-SNAPSHOT.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 2.579 s
```

Agora o projeto está pronto para ser utilizado!
   
