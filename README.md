Kaic Nunes Bastidas - 8516048

Leonardo Piccioni de Almeida - 8516094

Lucas Covre Delboni - 8516006

# EP de Distribuídos

## Relação de arquivos

* **Client.java**: Classe estática, que encapsula o terminal do cliente e seu comportamento.
* **Part.java**: Encapsulamento de uma peça.
* **PartQuantity.java**: Encapsulamento de uma tupla, contendo uma peça e uma quantidade.
* **PartRepository.java**: Interface, que declara um repositório de peças.
* **Server.java**: Servidor repositório de peças, implementa **PartRepository.java**.

## Lista de comandos

O cliente mantém três referências:
* Uma ao repositório atual, remoto;
* Uma à peça atual, remota;
* Uma à lista de subpeças, local.

Perceba que a manipulação de uma dessas referências nunca gera efeitos colaterais nas demais, exceto quando explicitamente notado o contrário, abaixo. Por exemplo, a lista de subpeças local nunca é limpa, exceto quando o comando **clearList** é chamado.

Os comandos abaixo devem ser inseridos no console do cliente, para exibição do respectivo contexto.

### Manipulação do repositório atual

* **addPart**: Adiciona uma peça ao repositório atual, incluindo a lista de subpeças local como lista de subpeças da nova peça.
* **countParts**: Conta o número de peças servidas pelo repositório atual.
* **getPart**: Substitui a peça atual pela peça requisitada pelo ID.
* **listParts**: Lista todas as peças servidas pelo repositório atual.
* **whoIsRepo**: Retorna o endereço do repositório atual.

### Manipulação da peça atual

* **countSubparts**: Exibe se a peça atual é primitiva ou derivada; no segundo caso, exibe uma contagem das subpeças.
* **showPart**: Exibe informações sobre a peça atual, tal como nome, descrição e, se ela possuir subpeças, lista-as.
* **whereIsPart**: Exibe o repositório que serve a peça atual.

### Manipulação da lista local de subpeças

* **addSubpart**: Adiciona uma determinada quantidade da peça atual à lista local de subpeças.
* **clearList**: Limpa a lista local de subpeças.
* **listSubparts**: Exibe as peças, e suas respectivas quantidades, contidas na lista local de subpeças.

### Outros comandos

* **reconnect**: Substitui o repositório atual pelo repositório requisitado pelo endereço.
* **quit**: Encerra o programa.
