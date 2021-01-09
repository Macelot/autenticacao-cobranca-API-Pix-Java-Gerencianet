# exemploAutenticaco

Neste exemplo faremos autenticação depois uma cobrança.
Você precisa colocar neste projeto seu certificado .p12
Para autenticar é necessário:
client_id 
client_secret
certificado p12 (neste projeto original em meu computador coloquei o certificado no diretório principal do projeto, não esqueça de colocar seu certificado no projeto em seu computador, este é o único arquivo que não coloquei no repositório)

Detalhes do projeto na IDE Eclipse:
Maven project 
groupId: br.com.empresa 
artifactId: exemploAutenticacao

---------------------------------
Autenticação: colocar arquivo .p12 na pasta do projeto 
vai na Classe App 
coloca o código: https://dev.gerencianet.com.br/docs/api-pix  <exemplo Java>
preencha com seu client_id, client_secret, .p12 
Dependências org.json e com.github.mifmif
Criar cobrança, o EndPoint é: https://api-pix-h.gerencianet.com.br/v2/cob/ 
A URL de cobrança precisa de do txid, que é uma expressão regular no formato [a-zA-Z0-9]{26,35} ficando por exemplo assim: 
https://api-pix-h.gerencianet.com.br/v2/cob/Xqk854M89A22a56J10FlypJ1wu5 
A cobrança precisa do token da autenticação. Não será necessário o certificado para a cobrança, apenas Bearer + token 
Na cobrança você deve enviar sua chave pix, para receber depois o valor da cobrança.
Será criada uma cobrança que ficará com o status ativo e uma data de criação neste formato: "criacao":"2021-01-06T21:49:14.398Z"
Vídeo com mais detalhes deste projeto:

Para mais informações consulte a documentação oficial da API Pix Gerencianet, disponível em: https://dev.gerencianet.com.br/docs/api-pix#section-criar-cobran-a-
