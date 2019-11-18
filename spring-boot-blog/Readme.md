##OAuth2 Login Flow:
 1) OAuth2 login flow будет инициирован фронтэнд клиентом при отправке юзера на эндроинт: 
 {server-host}/oauth2/authorize/{provider}?redirect_uri=<redirect_uri_after_login> . 
    * **Переменная provider** может принимать следующие значения: facebook, google, github.
    * **Переменная redirect_uri** - URI на который пользователь должен быть перенаправлен  после успешной OAuth2
аутентификации. Это не тоже самое что и OAuth2 redirectUri.
 
 2) При получении запроса на авторизацию, Spring Security’s OAuth2 перенаправит пользователя к AuthorizationUrl 
 соответствующего провайдера. Все состояния, связанные с запросом авторизации, сохраняются с помощью 
 authorizationRequestRepository, указанного в SecurityConfig. Теперь пользователь дает / запрещает 
 права приложению доступ к странице провайдера. Если пользователь дал права вашему приложению, то провайдер перенавправит
 юзера на страницу {server-host}/oauth2/callback/{provider} c авторизованным кодом. Если пользовател не даст права
 вашему приложению, то он будет перенаправлен на ту же саму страницу, но с ошибкой.
 
 3) Если OAuth2 callback содержит ошибку, Spring security вызовет oAuth2AuthenticationFailureHandler, определенном в 
 SecurityConfig.
 
 4) Если OAuth2 callback успешен и содержит авторизованный код, то Spring Security обменивает authorization_code на
 access_token и вызывает customOAuth2UserService, определенный в SecurityConfig.
 
 5) customOAuth2UserService получает детали аутентифицированного пользователя и создает новую запись в базе данных или
 апдейтит уже существующую запись (с такой же почтой).
 
 6) Наконец вызывается oAuth2AuthenticationSuccessHandler - он создает JWT токен для пользователя и перенаправляет
 пользователя на redirect_uri.