# 🐮 Boe

### Execução deste projeto
Para executar este projeto, clone-o pelo <strong><em>Git Bash</em></strong>:
```
git clone https://github.com/RebecaBaruch/boeMobileApp.git
```
Em seguida, abra o repositório clonado na ferramenta <strong><em>Android Studio</em></strong> e execute o projeto. Existem duas formas para executar o projeto:
<ol>
 <li>Conexão USB com dispositivo móvel</li>
 <li>Pelo emulador</li>
</ol>

Caso escolha a opção (1), ative o modo de desenvolvedor de seu dispositivo e certifique-se de que é o seu dispositivo que está selecionado como o dispositivo alvo. Após, apenas é preciso executar o projeto.
Caso escolha a opção (2), execute o projeto, mas certifique-se de que o emulador está conectado a uma rede Wifi.
Caso o projeto apresentar falhas de conexão, instale a API localmente, seguindo os passos abaixo:

<p>Execute o seguinte comando em seu terminal Git Bash para instalar este projeto:</p>

```
git clone https://github.com/RebecaBaruch/boe_app.git
```

<p>Depois de clonar o repositório, instale suas dependências:</p>

```
pip install -r Requirements.txt
```

<p>Caso ocorra problemas na instalação com o comando acima, aqui se encontra o comando para instalar todas as bibliotecas usadas no desenvolvimento da aplicação:</p>

```
pip install bcrypt bson cachelib Flask Flask-Caching Flask-Cors opencv-python Pillow PyJWT pymongo python-dateutil python-dotenv werkzeug
```

⚠️ É necessário ter o MongoDB Compass instalado e dentro do MongoDB criar uma base de dados: boe-app
⚠️ Certifique-se de que é possível conectar com o MongoDB Compass

### Como executar este projeto?
<p>Para executar este projeto, em seu terminal do repositório, execute a aplicação em Python pelo seguinte comando:</p>

```
py app.py
```

<p>Após rodar a aplicação em Python, execute a página login.html pela extensão LiveServer, por exemplo.</p>

Após todo esse processo a aplicação deve rodar normalmente.

Depois apenas abra o projeto no Android Studio e execute-o.
