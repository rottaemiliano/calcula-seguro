# CHALLENGE - PL. SOFTWARE ENGINEER 🚀

## Índice
1. [**Desafio**](#introducao)
2. [**Premissas**](#esperado)
3. [**Pontos que não iremos avaliar**](#pontos_que_nao_iremos_avaliar)
4. [**Como esperamos receber a solução**](#como_esperamos_receber)
5. [**Dúvidas**](#duvidas)


## <a name="introducao">1. Introdução</a>

A proposta da aplicação que vamos desenvolver em conjunto é disponibilizar a uma pessoa as modalidades de seguro as quais melhor se encaixam com seu perfil de acordo com algumas variáveis.

Abaixo seguem as regras de negócio relacionadas ao seguro de acordo com o valor do veículo:

| Valor do Veículo              | % do cálculo |
|-------------------------------|--------------|
| Veículo <= 70.000             | 4%           |
| Veículo <= 70.000 e cidade SP | 5%           |
| Veículo > 70.000 e < 100.000  | 5.5%         |
| Veículo => 100.000            | 6%           |

### Utilização da aplicação:

A aplicação deve receber como entrada essas informações:

##### input

```json
{
  "customer": {
    "name": "João",
    "document": "123.456.789-10",
    "birthday": "1990-07-10",
    "location": "BH",
    "vehicle_value": 70000
  }
}
```

_Para fins de simplicidade, considere que vamos sempre receber os dados corretos (tipos e formatos)_

E deve responder essas informações:

##### output

```json
{
  "customer": {
    "name": "João",
    "location": "BH",
    "value": 2800
  }
}
```

## <a name="esperado">2. O que é esperado</a>
Abaixo deixamos os pontos que daremos maior atenção:
- Padrões REST
- Tratamento de Erros
- Testes de unidade
- Cobertura de testes (Code Coverage)
- Arquitetura utilizada
- Abstração, acoplamento, extensibilidade e coesão
- Utilização de Design Patterns (quando necessário)
- Legibilidade de Código
- Documentação da Solução no README.md
- Observabilidade (logs)
- Geração do relatório de cobertura de testes

## <a name="pontos_que_nao_iremos_avaliar">3. Pontos que não iremos avaliar</a>
Abaixo deixamos alguns pontos que não iremos avaliar
- Scripts CI/CD
- Collections do Postman, Insomnia ou qualquer outra ferramenta para execução
- IDE utilizada

## <a name="como_esperamos_receber">4. Como esperamos receber a solução</a>
Esta etapa é eliminatória, e por isso esperamos que o código reflita essa importância.

Se tiver algum imprevisto, dúvida ou problema, por favor entre em contato com a gente, estamos aqui para ajudar.

Atualmente trabalhamos com a stack Java/Spring, porém você pode utilizar a tecnologia de sua preferência.

Para candidatos externos nos envie o link de um repositório público com a sua solução e link do Docker hub com a imagem de seu aplicativo publicada (pode deixar este link no README.md).

Para candidatos internos nos envie o projeto em formato .zip

## <a name="duvidas">5. Dúvidas</a>
Em caso de dúvidas, contacto-nos o quanto antes através do e-mail `EspecialistasComunidadeSeguros@correio.itau.com.br`.

Lembrando que não existe "bala de prata" e solução perfeita!

VEM COM A GENTE! 🍊🚀