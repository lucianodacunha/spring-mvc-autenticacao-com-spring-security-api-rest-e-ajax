# Spring MVC: autenticação com Spring Security, API Rest e AJAX

## Aula 1. Autenticação com Spring Security

- @Configuration e @EnableWebSecurity na classe que define as regras de segurança
- As regras de acesso foram definidos pelo HttpSecurity
- Os dados do usuário, como login e senha, foram definidos pelo método userDetailsService()
- O login pode ser implementado de várias formas
  - Vimos a forma HttpBasic e form-login
  - A lógica de login e logout já está implementada pelo Spring Security, basta configurar

### Configuração do Spring Security

Toda a configuração do Spring Security ficou centralizada em uma única classe e alguns recursos foram usados para que esta configuração funcionasse. A primeira são as suas dependências: nada compilaria se não tivéssemos adicionado a dependência do módulo de segurança no pom.xml: `spring-boot-starter-security`.

Com isso, nos foram disponibilizadas todas as classes necessárias para configurar o módulo de segurança: utilizamos um adapter (`WebSecurityConfigurerAdapter`), que vem com várias configurações default. Apenas duas destas configurações nós precisamos reimplementar: configure(`HttpSecurity`) e `configure(AuthenticationManagerBuilder auth)`. Utilizamos uma anotação (`@EnableWebSecurity`) que disponibiliza todas as dependências do Spring Security que precisamos para configurá-lo conforme a necessidade do nosso projeto; além disso, ganhamos alguns builders, que nos permitiram criar usuários e configurar a autorização.

### Doc

- [Spring Security 5.3.2](https://docs.spring.io/spring-security/site/docs/5.3.2.RELEASE/reference/html5/)


## Aula 2. Provedor de autenticação

- Executar uma autenticação baseada em JDBC
- Criptografar a senha do usuário
- Criar o modelo JDBC para representar o usuário e as permissões com Spring Security
- Acessar o usuário autenticado com Spring MVC, usando o Principal
- Escrever uma query JPA com Spring Data
- Usar links relativos com Thymeleaf, por exemplo th:action="@{/pedido/novo}"
- Desabilitar o CRSF (cross-site request forgery) com Spring Security

## Aula 3. Paginação e Cache

- Separar a página pública (sem login) das páginas restritas
- Redirecionar após logout para a página home
- Trabalhar com paginação e ordenação, usando as classes Pageable e Sort
- Usar o cache na aplicação para melhorar o desempenho usando a anotação @Cacheable

## Aula 4. Construindo API Rest

- Criar um controlador específico para requisições REST
- Usar o conceito REST para construir uma API devolvendo JSON
- Usamos a anotação @RestController
- Vimos que o Spring gera automaticamente o JSON

## Aula 5. Ajax e Vue.js

- Construir uma página HTML com Vue.js
- Executar requisições AJAX com Axios
  - Vimos como consumir dados e enviar dados do form e JavaScript

## Aula 6. Validações com Vue.js

- Como recuperar valores de um formulário pelo JavaScript
- Como enviar uma requisição AJAX do tipo POST com Axiom
- Como fazer um tratamento de erro client-side e apresentar mensagens de erro

### ResponseEntity

O Spring é um framework poderoso e geralmente oferece várias formas de atingir um resultado. Isso não é diferente pensando na criação da resposta HTTP de uma Action.

Normalmente basta devolver o modelo dentro do nosso @RestController para gerar a resposta HTTP mas podemos configurar a resposta HTTP mais detalhado, usando a classe ResponseEntity.

A classe ResponseEntity é um builder para definir o corpo (body) da resposta, status e os cabeçalhos. Veja o exemplo:

```
@RestController
public class HomeRestController {

    @GetMapping("/oi")
    ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("Oi Mundo!");
    }
}    
```

Isso não faz nada mais do que devolver o texto no corpo da resposta junto ao status 200. A vantagem aqui é que deixamos a resposta mais explicita e podemos configurar como desejamos para usar um outro código HTTP, por exemplo.

Segue também o exemplo para usar o ResponseEntity na nossa classe OfertaRest. Repare que o corpo não precisa ser uma string:

```
@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    public ResponseEntity<Oferta> criaOferta(@Valid @RequestBody RequisicaoNovaOferta requisicao) {

        //implementação omitida

        return ResponseEntity.ok().body(nova);
    }
}
```

## Aula 7. Monitoramento com Interceptadores

- Que interceptadores ajudam a centralizar código que é comum para várias classes da mesma camada
  - Centralizando, facilitamos a manutenção desse código
- Como criar um interceptador, estendendo a classe HandlerInterceptorAdaptor e registrando no InterceptorRegistry
  - A partir dessas classes, devemos sobrescrever os métodos em questão, como preHandle ou afterCompletion
- Que um interceptador pode parar o fluxo da requisição

### Implementações futuras

- Seção de cadastro de usuário
- Paginação de pedidos
- Paginação de ofertas
- Persistência de estatísticas de acessos
