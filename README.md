# Case Técnico: Integração com HubSpot
Case técnico da Meetime, cujo o objetivo é desenvolver uma API REST em Java para integrar com a API do HubSpot, 
implementando autenticação via OAuth 2.0, mais especificamente com o fluxo de 
authorization code flow, a implementação de endpoint de integração com a API e o 
recebimento de notificações via webhooks. 

## O que é necessário instalar
- JDK 21
- Maven
- Alguma forma de testar a API, como Hibernate e Postman.
- Conta no HubSpot de desenvolvedor e usuário comercial (Não permitem que os testes de chamadas sejam executadas pela conta de desenvolvedor)
- Alguma forma de expor a API na internet para que os webhooks possam ser enviados para ela (no meu caso, para os testes eu utilizei o ngrok)

## O que é necessário configurar
- No aplicativo HubSpot: 
Crie um aplicativo HubSpot usando uma conta desenvolvedor, na aba "Autenticação" coloque a URL de redirecionamento da API (por padrão da API sendo localhost:8080/oauth/callback)
No menu lateral, em "Webhooks", configure a URL de destino como o link público da sua aplicação/webhook/contact-creation e crie um webhook para contact.creation

- Configurar seu application.properties:
O application.properties vai ser onde você vai colocar suas credenciais do HubSpot, como client_id e client_secret
![image](https://github.com/user-attachments/assets/7e46a032-a95a-4964-9d95-a0c0090e2d8f)
Substitua os valores pelas suas credenciais encontradas na aba "Autenticação" do seu aplicativo no HubSpot.

- Clone e build do aplicativo:
Clone o aplicativo através do comando git clone
No terminal, navegue até a pasta do projeto usando o comando cd
Ainda no terminal, use os comandos mvn clean install e mvn spring-boot:run
A aplicação estará rodando em http://localhost:8080/

## Endpoints
A aplicação possui os seguintes endpoints:
- /oauth/authorize - Endpoint do tipo GET que vai gerar o link para chamada de autenticação do HubSpot

- /oauth/callback - Endpoint do tipo GET que o HubSpot vai redirecionar após autenticar o usuário, no qual também será fornecido o token de acesso

- /contact/create-contact - Endpoint do tipo POST que você vai poder enviar as informações para criação de contato, no seguinte modelo JSON (modelo requisitado pelo HubSpot):
{
  "properties": {
    "email": "testegeral@gmail.com",
    "firstname": "Teste",
    "lastname": "Geral",
    "phone": "(555) 43842-2549",
    "company": "testegeral",
    "website": "testegeral.com",
    "lifecyclestage": "marketingqualifiedlead"
  }
}
Para informações dos tipos das variáveis, consultar o arquivo ContactRequestModel ou verificar a documentação da API do HubSpot.

- /webhook/contact-creation -Endpoint do tipo POST que o HubSpot vai enviar o webhook quando um evento acontecer, que no caso é a criação do contato.


## Fluxo

- 1) Gere o link para a autenticação no HubSpot através do endpoint /oauth/authorize:
![image](https://github.com/user-attachments/assets/9874a033-345b-4b63-a6e1-e82838f0f850)

- 2) Copie o link e cole no seu navegador, para simular o redirecionamento:
![image](https://github.com/user-attachments/assets/260c962b-e05f-4c8b-b020-63a87f12f03a)

- 3) Faça o login e autentique sua conta, logo depois o HubSpot vai te redirecionar automaticamente para sua redirect-uri, lá você vai ter o access-token:
![image](https://github.com/user-attachments/assets/ee9e16ab-3811-41b9-9f93-0ee1192d734d)
O access token também pode ser visto no console da aplicação:
![image](https://github.com/user-attachments/assets/0a03d35a-4276-43be-8376-d352e4091bbe)

- 4) Copie o access token e use no header para testar a criação de usuário, junto com o modelo do JSON para a criação de contatos:
![image](https://github.com/user-attachments/assets/aed4a241-33ef-43fb-9b62-b59acdcead50)
![image](https://github.com/user-attachments/assets/b19c1421-f099-4d30-86a1-987086f53c92)

- 5) Confira no seu aplicativo do HubSpot, na aba "Chamadas de API" em monitoramento a criação do usuário:
![image](https://github.com/user-attachments/assets/2020c29e-6ec1-4bbe-8d4d-12206fc07d34)

- 6) Confira no console da sua aplicação o recebimento do webhook, e confira na aba "Webhooks" em monitoramento no aplicativo o envio do webhook:
![image](https://github.com/user-attachments/assets/10127c2d-937b-446c-920a-46474395d6f3)
![image](https://github.com/user-attachments/assets/69f424ca-1e77-4090-a27e-9cfcc4e64ad3)

## Libs utilizadas e motivação
- 1. Spring Boot Starter Web - Utilizado para fornecer as dependências necessárias para o desenvolvimento web com Spring boot
- 2. Spring Boot Starter Security - Utilizado para configurar o OAuth2 e proteger os endpoints da aplicação
- 3. Spring Boot DevTools - Ferramentas de Debug e reinicialização automática
- 4. Guava - Para utilização do RateLimiter

## Organização do Projeto
A api está organizada da seguinte maneira:
- Módulo Config: Configurações de segurança
- Módulo Controllers: Configurações de endpoints da API
- Módulo Service: Os arquivos nos quais as lógicas das operações dos endpoints foram implementadas
- Módulo Model: Os arquivos de modelos para a request de criação de usuário e payload do Webhook do Hubspot, criados para facilitar a visualização de informações necessárias e seus tipos,
além de encapsular e proteger melhor as informações

