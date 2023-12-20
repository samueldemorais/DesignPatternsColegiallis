# DesignPatternsColegiallis

Esse readme esclacerá como foi utilizado os padrões de projeto na etapa Interdisciplinar entre Programação para Web 2 e Padrões de Projeto.

# Padrão de Projeto State
O padrão de projeto State é um padrão comportamental que permite que um objeto altere seu comportamento quando seu estado interno é alterado. Ele permite a alteração do comportamento de um objeto sem alterar sua classe, tornando-o mais flexível e fácil de entender.

##Diagrama de Classes 
![colegiado-escolar-State](https://github.com/samueldemorais/DesignPatternsColegiallis/assets/93936945/c9d2dd54-58c9-46b6-a721-fd15dacb3c90)

## Aplicação do Padrão State no Código
No código, o padrão State é aplicado para gerenciar os diferentes estados de um processo por meio de uma interface EstadoProcesso e várias classes que implementam essa interface para representar estados específicos do processo (Criado, Distribuido, Em Pauta, em Julgamento e Julgado).


public interface EstadoProcesso {
    StatusProcesso getEstado();
    void avancarEstado(Processo processo);
}

public class Criado implements EstadoProcesso {
    // Implementação do estado Criado
}

// Outras classes de estado (Distribuido, EmPauta, EmJulgamento, Julgado) seguem uma estrutura semelhante à Criado. Apenas em Julgado é feito um corte pois não possue próximo Estado.
Cada classe que implementa a interface EstadoProcesso representa um estado específico do processo e define como o processo deve avançar para o próximo estado através do método avancarEstado(Processo processo).

No ProcessoService, a mudança de estado é realizada em diferentes métodos, como atribuirProcesso, votarProfessor, votarRelator e votarColegiado. Esses métodos interagem com o estado atual do processo e invocam a transição para o próximo estado. Por exemplo, a chamada processo.avancarParaProximoEstado() em métodos como votarProfessor, votarRelator e votarColegiado invoca a transição de estado, que é tratada internamente nas classes que implementam EstadoProcesso. Isso permite que o processo progrida de um estado para outro conforme necessário, seguindo as regras definidas para cada estado específico.

#Padrão de Projeto Proxy
O padrão Proxy é um padrão estrutural que permite a criação de um objeto intermediário que controla o acesso a outro objeto. Ele atua como um substituto ou representante de outro objeto para controlar o acesso a ele.

#Diagrama de Classes
![sistemacolegiado-Proxy](https://github.com/samueldemorais/DesignPatternsColegiallis/assets/93936945/d48304c4-b93e-4c93-adf0-94929473cc88)

##Aplicação do Padrão Proxy no Código
No código fornecido, o padrão Proxy é aplicado para controlar o acesso dos professores aos processos designados para eles. Duas classes e uma interface foi desenvolvida para esta implementação: ProcessosConsulta e ProxyConsultaProcessosProfessor.

public interface ConsultaProcessos {
    List<Processo> consultarProcessos();
}

public class ProcessosConsulta implements ConsultaProcessos {
    // Implementação da consulta de processos
}

public class ProxyConsultaProcessosProfessor implements ConsultaProcessos {
    // Implementação do proxy para consulta de processos dos professores
}

A classe ProxyConsultaProcessosProfessor atua como um intermediário para acessar os processos por meio da interface ConsultaProcessos. Ela controla o acesso, verificando se o professor tem permissão para visualizar os processos por meio do método consultarProcessos().

##Funcionamento da Implementação
A classe ProxyConsultaProcessosProfessor possui um método verificarAcesso() que verifica se o professor tem permissão para acessar os processos designados para ele. Se o acesso for permitido, o proxy permite a consulta aos processos por meio do método processoService.listarProcessos(filtro). Caso contrário, uma exceção de segurança é lançada, informando que o acesso foi negado.

No ProcessoService, o método listarProcessosProfessor utiliza o proxy ProxyConsultaProcessosProfessor para consultar os processos de um professor específico, garantindo que somente os professores autorizados possam acessar essas informações.

## Uso da Classe Concreta na Verificação de Acesso
Dentro do ProxyConsultaProcessosProfessor, o método verificarAcesso() delega a verificação de acesso à classe concreta ProcessosConsulta por meio do método verificarAcessoParaProfessor(idProfessor). Essa modificação permite que a lógica específica de verificação de acesso seja realizada na classe ProcessosConsulta.

Isso possibilita uma separação de responsabilidades, onde a classe concreta ProcessosConsulta é responsável por determinar se um professor tem acesso aos processos, enquanto o proxy (ProxyConsultaProcessosProfessor) controla o fluxo de acesso aos processos.

# Padrão de Projeto Mediator

O padrão de projeto Mediator é um padrão comportamental que facilita a comunicação entre diferentes objetos, atuando como um intermediário centralizado que coordena as interações entre esses objetos. Ele promove o desacoplamento entre os componentes do sistema, permitindo que eles interajam sem conhecer uns aos outros diretamente.

##Diagrama de Classes
![colegiado-escolar-Mediator](https://github.com/samueldemorais/DesignPatternsColegiallis/assets/93936945/6db97902-6323-4c47-ac6e-4e670194cc61)

##Diagrama de Estados
![Captura de tela 2023-12-20 022423](https://github.com/samueldemorais/DesignPatternsColegiallis/assets/93936945/dd90034b-3800-4ce5-b8f0-a9331980e38d)


##Aplicação do Padrão Mediator no Código
No código fornecido, o padrão Mediator é utilizado para gerenciar a distribuição de processos aos professores por meio da interface MediatorProcesso e da implementação concreta Coordenador.

public interface MediatorProcesso {
    void atribuirProcesso(Integer idProcesso, Integer idProfessor);
}

public class Coordenador implements MediatorProcesso {
    private ProcessoService processoService;
    private ProfessorService professorService;

    public Coordenador(ProcessoService processoService, ProfessorService professorService) {
        this.processoService = processoService;
        this.professorService = professorService;
    }

    @Override
    public void atribuirProcesso(Integer idProcesso, Integer idProfessor) {
        //Atribuição do processo a um professor designado (coordenador)
    }
}

##Funcionamento do Mediator no Serviço de Processo
No método atribuirProcesso() do serviço de processo (ProcessoService), um objeto do tipo Coordenador (que implementa MediatorProcesso) é utilizado para coordenar a atribuição de um processo a um professor específico.
Dentro deste método, a instância de Coordenador é responsável por realizar a atribuição do processo ao professor designado, garantindo que somente um coordenador (professor com a permissão adequada) possa realizar essa atribuição.

##Importância do Mediator na Distribuição de Processos
O padrão Mediator ajuda a gerenciar a distribuição de processos aos professores, agindo como um intermediário (nesse caso, o coordenador) que coordena e controla a atribuição adequada de processos aos professores designados. Isso promove uma organização eficiente e centralizada na distribuição de tarefas, garantindo que apenas os professores autorizados possam realizar essa ação.
