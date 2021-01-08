# Chock Norris Joke APP

### Tenha uma sempre uma lista de fatos sobre Chuck Norris com você.
### A utilização passa por abrir o aplicativo e rolar pelas piadas. Além disso, você também pode compartilhar as piadas com os seus amigos.

Aplicativo de fundamentação de teoria e prática de programação Android baseado em uma api de piadas/fatos sobre CHUCK NORRIS

https://api.chucknorris.io

* Arquitetura MVVM
* Utilizado prática TDD (ish) com testes unitários e instrumentados (JUnit e Espresso)
* Cobertura do Jacoco https://drive.google.com/file/d/185i9VdVW04K7_reUHa2xYkazntKCu8E-/view?usp=sharing
Rodar task jacocoTestReportClean do gradle para verificar (Exige android físico para o espresso e podem ocorrer problemas em alguns aparelhos)

### Próximos passos
* Pesquisa de piadas
* Listas de piadas por assunto

## Principais Bibliotecas

### Implementação do Timber para logs
* https://github.com/JakeWharton/timber
* https://medium.com/android-dev-br/dominando-seus-logs-com-o-timber-8e9730b2090b

### Implementação do retrofit para a comunicação com a API
* https://square.github.io/retrofit/
* https://vickychijwani.me/retrofit-vs-volley/

### Implementação do Gson para a tradução de json
* https://github.com/google/gson

### Implementação do Glide para otenção e cache de URL de imagens
* https://bumptech.github.io/glide/

### Implementação do Coroutines para trabalho assíncrono
* https://kotlinlang.org/docs/reference/coroutines-overview.html
* https://developer.android.com/kotlin/coroutines

### Implementação do Koin para injeção de dependências
* https://insert-koin.io/
* https://github.com/InsertKoinIO/koin
* https://medium.com/collabcode/inje%C3%A7%C3%A3o-de-depend%C3%AAncia-no-kotlin-com-koin-4d093f80cb63

### Implementação do Okhttplogger para logar conteúdo de requisições web
* https://square.github.io/okhttp/interceptors/
