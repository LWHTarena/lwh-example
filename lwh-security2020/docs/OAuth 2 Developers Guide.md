# OAuth 2 Developers Guide
https://projects.spring.io/spring-security-oauth/docs/oauth2.html

## 介绍
这是OAuth 2.0支持的用户指南。对于OAuth 1.0，一切都不同，因此请参阅其用户指南。

本用户指南分为两部分，第一部分用于OAuth 2.0提供程序，第二部分用于OAuth 2.0客户端。对于提供者和客户而言，
示例代码的最佳来源是集成测试和[示例应用程序](https://github.com/spring-projects/spring-security-oauth/tree/master/samples/oauth2)。

## OAuth 2.0提供程序
OAuth 2.0提供程序机制负责公开受OAuth 2.0保护的资源。该配置涉及建立OAuth 2.0客户端，这些客户端可以独立或
代表用户访问其受保护的资源。提供者通过管理和验证用于访问受保护资源的OAuth 2.0令牌来实现此目的。如果适用，提
供者还必须为用户提供一个界面，以确认可以授予客户端访问受保护资源的权限（即确认页面）。

## OAuth 2.0提供程序实现
OAuth 2.0中的提供者角色实际上是在授权服务和资源服务之间分配的，尽管它们有时驻留在同一应用程序中，但使用
Spring Security OAuth，您可以选择将它们拆分到两个应用程序中，并具有多个共享的资源服务授权服务。对令牌
的请求由Spring MVC控制器端点处理，对受保护资源的访问由标准Spring Security请求过滤器处理。为了实现
OAuth 2.0授权服务器，Spring Security过滤器链中需要以下端点：

- `AuthorizationEndpoint`用于服务授权请求。预设网址：`/oauth/authorize`。
- `TokenEndpoint`用于服务访问令牌的请求。预设网址：`/oauth/token`。

要实现OAuth 2.0资源服务器，需要以下过滤器：

- `OAuth2AuthenticationProcessingFilter`用于在给定经过身份验证的访问令牌的情况下为请求加载身份验证。

对于所有OAuth 2.0提供程序功能，可使用特殊的Spring OAuth `@Configuration`适配器简化配置。还有一个用于
OAuth配置的XML名称空间，该模式位于https://www.springframework.org/schema/security/spring-security-oauth2.xsd。
命名空间为http://www.springframework.org/schema/security/oauth2。

## 授权服务器配置(Authorization Server Configuration)
在配置授权服务器时，必须考虑客户端用于从最终用户获取访问令牌的授予类型（例如，授权代码，用户凭据，刷新令牌）。
服务器的配置用于提供客户端详细信息服务和令牌服务的实现，并全局启用或禁用该机制的某些方面。但是请注意，可以为
每个客户端专门配置权限，使其能够使用某些授权机制和访问授权。即仅仅因为您的提供程序配置为支持“客户端凭据”授予
类型，并不意味着授权特定客户端使用该授予类型。

`@EnableAuthorizationServer`批注用于配置OAuth 2.0授权服务器机制，以及实现AuthorizationServerConfigurer的
任何`@Bean`（有一个空方法的便捷适配器实现）。以下功能委托给由Spring创建并传递到 `AuthorizationServerConfigurer`中的单独的配置器：

- `ClientDetailsS​​erviceConfigurer`：定义客户端详细信息服务的配置器。可以初始化客户端详细信息，或者您可以仅引用现有商店。
- `AuthorizationServerSecurityConfigurer`：定义令牌端点上的安全约束。
- `AuthorizationServerEndpointsConfigurer`：定义授权和令牌端点以及令牌服务。

提供者配置的一个重要方面是将授权代码提供给OAuth客户端的方式（在授权代码授予中）。 OAuth客户端通过将最终用户定向到授权
页面（用户可以在其中输入她的凭据）来获得授权码，从而导致从提供者授权服务器重定向回带有授权码的OAuth客户端。 OAuth 2规
范中对此进行了详细说明。

在XML中，有一个`<authorization-server />`元素，该元素以类似的方式用于配置OAuth 2.0授权服务器。

## 配置客户端详细信息
`ClientDetailsS​​erviceConfigurer`（来自AuthorizationServerConfigurer的回调）可用于定义客户端详细信息服务的内存中或JDBC实现。客户的重要属性是

- `clientId` ：（必填）客户端ID。
- `secret`：（对于受信任的客户端是必需的）客户端机密（如果有）。
- `scope`：客户端所限制的范围。如果范围未定义或为空（默认值），则客户端不受范围的限制。
- `AuthorizedGrantTypes`：授权客户端使用的授权类型。默认值为空。
- `authorities`：授予客户端的权威（常规的Spring Security权威）。

可以通过直接访问基础存储区（例如，对于`JdbcClientDetailsS​​ervice`使用数据库表）或通过`ClientDetailsManager`接口
（`ClientDetailsS​​ervice`的两个实现也都实现）来在运行的应用程序中更新客户端详细信息。

注意：JDBC服务的模式未随库一起打包（因为在实践中可能要使用太多变体），但是有一个示例可以从github的[测试代码](https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql)开始。

## 管理令牌(token)
`AuthorizationServerTokenServices`接口定义管理OAuth 2.0令牌所需的操作。请注意以下几点：

- 创建访问令牌后，必须存储身份验证，以便接受访问令牌的资源以后可以引用它。
- 访问令牌用于加载用于授权其创建的身份验证。

在创建`AuthorizationServerTokenServices`实现时，您可能要考虑使用`DefaultTokenServices`，它可以插入许多策略来更改访问
令牌的格式和存储。默认情况下，它通过随机值创建令牌并处理所有事务，除了将其委派给TokenStore的令牌的持久性之外。默认存储是内存中
的实现，但是还有其他一些可用的实现。这是一个说明，并对其进行了一些讨论

- 默认的`InMemoryTokenStore`非常适合单台服务器（即低流量，并且在发生故障时不与备份服务器进行热交换）。大多数项目都可以从此处
开始，并且可以在开发模式下以这种方式运行，从而可以轻松启动没有依赖性的服务器。

- `JdbcTokenStore`是同一事物的JDBC版本，它将令牌数据存储在关系数据库中。如果可以在服务器之间共享数据库，请使用JDBC版本；
如果只有一个，则可以按比例放大同一服务器的实例；如果有多个组件，则可以使用授权和资源服务器。要使用`JdbcTokenStore`，
您需要在类路径上使用“ spring-jdbc”。

- `JSON Web Token (JWT) version`(存储的JSON Web令牌（JWT）版本)将有关授权的所有数据编码到令牌本身中（因此根本没有后端存储，这是一个很大的优势）。一个缺点是
您不能轻易地撤消访问令牌，因此授予它们的期限通常很短，并且撤消是在刷新令牌中进行的。另一个缺点是，如果您在令牌中存储了大量用户
凭证信息，则令牌会变得很大。从某种意义上说，JwtTokenStore并不是持久性的，它实际上并不是一个“存储”，但是它在翻
译DefaultTokenServices中的令牌值和身份验证信息之间起着相同的作用。

**注意:** JDBC服务的模式未随库一起打包（因为在实践中可能要使用太多变化），但是有一个示例可以从github的测试代码开始。确保使
用`@EnableTransactionManagement`来防止在创建令牌时客户端应用程序在争用同一行之间发生冲突。还要注意，样本模式具有显
式的`PRIMARY KEY`声明-在并发环境中，这些声明也是必需的。

## JWT Tokens
要使用JWT令牌，您需要在授权服务器中有一个`JwtTokenStore`。资源服务器还需要能够解码令牌，因此`JwtTokenStore`依赖于
`JwtAccessTokenConverter`，并且授权服务器和资源服务器都需要相同的实现。默认情况下，令牌是经过签名的，并且资源服务
器还必须能够验证签名，因此它要么需要与授权服务器相同的对称（签名）密钥（共享密钥或对称密钥），要么需要公用与授权服务器
中的私钥（签名密钥）匹配的私钥（验证者密钥）（公私钥或非对称密钥）。授权服务器在`/oauth/token_key`端点上公开公用密钥（
如果可用），默认情况下，使用访问规则“ denyAll（）”保护该公用密钥的安全。您可以通过将标准SpEL表达式注入
`AuthorizationServerSecurityConfigurer`中来打开它（例如，“ permitAll（）”由于是公共密钥，因此可能就足够了）。

要使用`JwtTokenStore`，您需要在类路径上使用“spring-security-jwt”（您可以在与Spring OAuth相同的github存储库中找到它，但发布周期不同）。

## Grant Types
可以通过`AuthorizationServerEndpointsConfigurer`配置`AuthorizationEndpoint`支持的授权类型。默认情况下，除密码外，所
有授权类型均受支持（有关如何打开密码的详细信息，请参见下文）。以下属性影响授予类型：

- `authenticationManager`：通过注入AuthenticationManager来打开密码授予。
- `userDetailsS​​ervice`：如果您注入`UserDetailsS​​ervice`或无论如何都已全局配置（例如，在`GlobalAuthenticationConfigurerAdapter`中），
则刷新令牌授予将包含对用户详细信息的检查，以确保该帐户仍处于活动状态
- `authorizationCodeServices`：定义用于授权码授予的授权码服务（`AuthorizationCodeServices`的实例）。
- `implicitGrantService`：在隐式授予期间管理状态。
- `tokenGranter`：TokenGranter（完全控制授予并忽略上面的其他属性）

In XML grant types are included as child elements of the `authorization-server`.

## 配置端点URL（Configuring the Endpoint URLs）
`AuthorizationServerEndpointsConfigurer`具有`pathMapping()`方法。它有两个参数：

- 端点的默认（框架实现）URL路径
- 所需的自定义路径（以“`/`”开头）

框架提供的URL路径是`/oauth/authorize`（授权端点），`/oauth/token`（令牌端点），`/oauth/confirm_access`（用户在此处发布对批准的批准,
`/oauth/error`（用于呈现错误）在授权服务器中),`/oauth/check_token`（由资源服务器用于解码访问令牌）和`/oauth/token_key`（如果使用JWT令牌，则公开用于令牌验证的公钥）。

N.B.应该使用Spring Security保护Authorization端点`/oauth/authorize`（或它的映射替代项），以便只有经过身份验证的用户才能访问它。
例如，使用标准的Spring Security `WebSecurityConfigurer`：

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests().antMatchers("/login").permitAll().and()
    // default protection for all resources (including /oauth/authorize)
        .authorizeRequests()
            .anyRequest().hasRole("USER")
    // ... more configuration, e.g. for form login
}
```

注意：如果您的授权服务器也是资源服务器，那么还有另一条安全过滤器链，具有较低的优先级来控制API资源。 对于那些受访问令牌保护的请求，
您需要它们的路径与面向用户的主过滤器链中的路径不匹配，因此请确保在上面的`WebSecurityConfigurer`中包括一个仅选择非API资源的请求匹配器。

默认情况下，Spring OAuth在`@Configuration`支持中使用客户端密钥的`HTTP Basic`身份验证为您保护令牌终结点。 XML中不是这种情况（因此应该对其进行显式保护）。

在XML中，`<authorization-server />`元素具有一些属性，可用于以类似方式更改默认端点URL。 必须显式启用`/check_token`端点（使用`check-token-enabled`属性）。

## Customizing the UI（自定义UI）

大多数Authorization Server端点主要由机器使用，但是有一些资源需要UI，其中包括`/oauth/confirm_access`的GET和`/oauth/error`的HTML响应。
它们是使用框架中的whitelabel实现提供的，因此大多数真实世界的Authorization Server实例都希望提供自己的实例，以便它们可以控制样式和内容。您需
要做的就是为这些端点提供一个带有`@RequestMapping`的Spring MVC控制器，并且框架默认值在调度程序中将具有较低的优先级。在`/oauth/confirm_access`端点
中，可以期望一个绑定到会话的AuthorizationRequest携带所有需要获得用户批准的数据（默认实现是WhitelabelApprovalEndpoint，因此请在此处查找要复制的起点）。
您可以从该请求中获取所有数据并按自己的喜好进行渲染，然后用户需要做的就是向回发到/ oauth / authorize并提供有关批准或拒绝授予的信息。请求参数直接传递到
AuthorizationEndpoint中的UserApprovalHandler，因此您可以根据需要或多或少地解释数据。默认的UserApprovalHandler取决于您是否
在AuthorizationServerEndpointsConfigurer中提供了ApprovalStore（在这种情况下，它是ApprovalStoreUserApprovalHandler）（
在这种情况下，是TokenStoreUserApprovalHandler）。标准批准处理程序接受以下内容：

- `TokenStoreUserApprovalHandler`：通过user_oauth_approval进行的简单是/否决定等于“ true”或“ false”。

- `ApprovalStoreUserApprovalHandler`：a set of `scope.*` parameter keys with `"*"` 参数的值可以是“ true”或“ approved”（如果用户批准了授予），
否则用户将被视为拒绝该范围。如果至少一个范围得到批准，则授予成功。

注意：不要忘记在为用户呈现的表单中包含CSRF保护。 Spring Security期望默认使用一个名为“_csrf”的请求参数（它在request属性中提供值）。
有关更多信息，请参见Spring Security用户指南，或查看whitelabel实现以获取指导。

## Enforcing SSL 
普通HTTP可以很好地进行测试，但是授权服务器只能在生产环境中通过SSL使用。您可以在安全的容器中或在代理后面运行该应用，如果正确设置了代理
和容器（与OAuth2无关），它应该可以正常运行。您可能还想使用Spring Security `requireChannel（）`约束来保护端点。对于`/authorize`端点，
这取决于您作为常规应用程序安全性的一部分来执行此操作。对于`/token`终结点，可以使用sslOnly（）方法在`AuthorizationServerEndpointsConfigurer`中设置
一个标志。在这两种情况下，安全通道设置都是可选的，但是如果它在一个不安全的通道上检测到请求，它将导致Spring Security重定向到它认为是安全通道的位置。

## Customizing the Error Handling(自定义错误处理)
授权服务器中的错误处理使用标准的Spring MVC功能，即端点本身中的`@ExceptionHandler`方法。用户还可以向端点本身提供`WebResponseExceptionTranslator`，
这是更改响应内容的最佳方法，而不是呈现它们的方法。如果是令牌端点，则将异常委托呈现给`HttpMesssageConverters`（可以将其添加到MVC配置中），如果是授权端点，
则将呈现呈现给OAuth错误视图（`/oauth/error`）。为HTML响应提供了whitelabel错误端点，但是用户可能需要提供自定义实现（例如，只需添加带
有`@RequestMapping（“/oauth/error”）`的`@Controller`）。

## Mapping User Roles to Scopes (将用户角色映射到范围)
有时不仅通过分配给客户端的范围来限制令牌的范围，还可以根据用户自己的权限来限制令牌的范围。如果在`AuthorizationEndpoint`中使用
`DefaultOAuth2RequestFactory`，则可以设置标志`checkUserScopes = true`以将允许的范围限制为仅与用户角色匹配的范围。您还可以
将`OAuth2RequestFactory`注入`TokenEndpoint`，但仅当您还安装了`TokenEndpointAuthenticationFilter`时，该方法才有效（即使用密码授予）
-您只需要在HTTP `BasicAuthenticationFilter`之后添加该过滤器即可。当然，您也可以实现自己的规则，以将作用域映射到角色并安装自己
的OAuth2RequestFactory版本。 `AuthorizationServerEndpointsConfigurer`允许您注入自定义的`OAuth2RequestFactory`，因此，
如果使用`@EnableAuthorizationServer`，则可以使用该功能来建立工厂。

## Resource Server Configuration (资源服务器配置)
资源服务器（可以与授权服务器或单独的应用程序相同）提供受OAuth2令牌保护的资源。 Spring OAuth提供了实现此保护的Spring Security身份验证过滤器。
您可以使用`@Configuration`类上的`@EnableResourceServer`将其打开，并使用`ResourceServerConfigurer`（根据需要）对其进行配置。可以配置以下功能：

- `tokenServices`：定义令牌服务的bean（ResourceServerTokenServices的实例）。
- `resourceId`：资源的ID（可选，但建议使用，将由auth服务器验证（如果存在））。
- 资源服务器的其他扩展点（例如用于从传入请求中提取令牌的tokenExtractor）
- 请求受保护资源的匹配器（默认为全部）
- 受保护资源的访问规则（默认为普通的“已认证”）
- Spring Security中HttpSecurity配置程序允许的受保护资源的其他自定义

`@EnableResourceServer`批注会自动将类型为`OAuth2AuthenticationProcessingFilter`的过滤器添加到Spring Security过滤器链。

在XML中，有一个带有id属性的`<resource-server />`元素-这是Servlet过滤器的bean id，然后可以将其手动添加到标准Spring Security链中。

您的`ResourceServerTokenServices`是与授权服务器签订的合同的另一半。如果资源服务器和授权服务器在同一个应用程序中，并且您使用`DefaultTokenServices`，
那么您不必为此考虑太多，因为它实现了所有必需的接口，因此它是自动一致的。如果您的资源服务器是单独的应用程序，则必须确保与授权服务器的功能匹配，并提供知
道如何正确解码令牌的`ResourceServerTokenServices`。与授权服务器一样，您通常可以使用`DefaultTokenServices`，并且大多数选择是通过`T​​okenStore`（后
端存储或本地编码）表示的。替代方法是`RemoteTokenServices`，它是Spring OAuth功能（不是规范的一部分），允许资源服务器通过授权服务器（`/oauth/check_token`）
上的HTTP资源解码令牌。如果资源服务器中流量不大（每个请求都必须通过授权服务器进行验证），或者您可以负担得起的结果，RemoteTokenServices便很方便。
要使用`/oauth/check_token`端点，您需要通过在`AuthorizationServerSecurityConfigurer中`更改其访问规则（默认为“ denyAll（）”）来公开它，例如

```java
@Override
public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    oauthServer.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')").checkTokenAccess(
            "hasAuthority('ROLE_TRUSTED_CLIENT')");
}
```

在此示例中，我们同时配置了`/oauth/check_token`端点和`/oauth/token_key`端点（以便受信任的资源可以获得用于JWT验证的公钥）。
这两个端点通过使用客户端凭据的HTTP Basic身份验证进行保护。

## Configuring An OAuth-Aware Expression Handler (配置可识别OAuth的表达式处理程序)

您可能想利用Spring Security的基于表达式的访问控制。默认情况下，表达式处理程序将在`@EnableResourceServer`设置中注册。
表达式包括＃oauth2.clientHasRole，＃oauth2.clientHasAnyRole和＃oath2.denyClient，可用于基于oauth客户端的角色
提供访问权限（有关完整列表，请参阅OAuth2SecurityExpressionMethods）。在XML中，您可以使用常规<http />安全配置
的expression-handler元素注册可识别oauth的表达式处理程序。

## OAuth 2.0客户端(OAuth 2.0 Client)
OAuth 2.0客户端机制负责访问其他服务器的OAuth 2.0保护的资源。配置涉及建立用户可能有权访问的相关受保护资源。可能还需要向客户端提供用于存储用户的授权码和访问令牌的机制。

## Protected Resource Configuration (受保护的资源配置)
可以使用`OAuth2ProtectedResourceDetails`类型的bean定义来定义受保护的资源（或“remote resources”）。受保护的资源具有以下属性：

- id：资源的ID。该ID仅由客户端用于查找资源。在OAuth协议中从未使用过。它也用作bean的ID。
- clientId：OAuth客户端ID。这是OAuth提供程序用来标识您的客户端的ID。
- clientSecret：与资源关联的机密。默认情况下，没有秘密为空。
- accessTokenUri：提供访问令牌的提供程序OAuth端点的URI。
- scope：逗号分隔的字符串列表，用于指定对资源的访问范围。默认情况下，将不指定范围。
- clientAuthenticationScheme：客户端用于对访问令牌端点进行身份验证的方案。建议值：“ http_basic”和“ form”。默认值：“ http_basic”。请参阅OAuth 2规范的第2.1节。

不同的授予类型具有`OAuth2ProtectedResourceDetails`的不同具体实现（例如，“client_credentials”授予类型的`ClientCredentialsResource`）。对于
需要用户授权的授予类型，还有一个属性：

- userAuthorizationUri：如果曾经需要用户授权对资源的访问，则将用户重定向到的uri。请注意，这并非总是必需的，具体取决于所支持的OAuth 2配置文件。
在XML中，有一个`<resource />`元素可用于创建`OAuth2ProtectedResourceDetails`类型的Bean。它具有与上述所有属性匹配的属性。

## 客户端配置(Client Configuration)

对于OAuth 2.0客户端，使用`@EnableOAuth2Client`简化了配置。 这有两件事：

- 创建一个过滤器bean（标识为`oauth2ClientContextFilter`）来存储当前请求和上下文。 在请求期间需要进行身份验证的情况下，它管理与OAuth身份验证uri之间的重定向。

- 在请求范围内创建`AccessTokenRequest`类型的Bean。 授权代码（或隐式）授权客户端可以使用此方法来防止与单个用户相关的状态冲突。

过滤器必须连接到应用程序中（例如，使用Servlet初始化程序或具有相同名称的`DelegatingFilterProxy`的`web.xml`配置）。

可以在`OAuth2RestTemplate`中使用`AccessTokenRequest`，如下所示：

```java
@Autowired
private OAuth2ClientContext oauth2Context;

@Bean
public OAuth2RestTemplate sparklrRestTemplate() {
	return new OAuth2RestTemplate(sparklr(), oauth2Context);
}
```


`OAuth2ClientContext`（为您）放置在会话范围内，以保持不同用户的状态分离。否则，您将不得不自己在服务器上管理等效的数据结构，
将传入的请求映射到用户，并将每个用户与OAuth2ClientContext的单独实例相关联。

在XML中，有一个带id属性的`<client />`元素-这是Servlet过滤器的Bean ID，必须像@Configuration案例中那样映射到`DelegatingFilterProxy`（具有相同的名称）。

## Accessing Protected Resources （访问受保护的资源）
为资源提供所有配置后，现在就可以访问这些资源。建议的访问这些资源的方法是使用Spring 3中引入的RestTemplate。OAuthfor Spring Security提
供了RestTemplate的扩展，只需要提供`OAuth2ProtectedResourceDetails`的实例即可。要将其与用户令牌（授权代码授予）一起使用，您应该考虑使
用`@EnableOAuth2Client`配置（或XML等效的`<oauth：rest-template/>`），该配置创建一些请求和会话范围的上下文对象，以便针对不同用户的请求在运行时不会发生冲突。

通常，Web应用程序不应使用密码授予，因此，如果可以支持AuthorizationCodeResourceDetails，请避免使用ResourceOwnerPasswordResourceDetails。
如果您迫切需要密码授予才能从Java客户端工作，请使用相同的机制来配置OAuth2RestTemplate并将凭据添加到AccessTokenRequest（这是一个地图，是短暂的）
而非ResourceOwnerPasswordResourceDetails（在所有访问令牌之间共享） 。

## Persisting Tokens in a Client 在客户端中保留令牌
客户端不需要持久化令牌，但是每次重新启动客户端应用程序时，不需要用户批准新的令牌授予就可以了。 `ClientTokenServices`接口定义了为特定用户
持久保存OAuth 2.0令牌所必需的操作。提供了JDBC实现，但是如果您愿意实现自己的服务，则可以在持久数据库中存储访问令牌和关联的身份验证实例。
如果您想使用此功能，则需要向`OAuth2RestTemplate`提供经过特殊配置的`TokenProvider`，例如

```java
@Bean
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public OAuth2RestOperations restTemplate() {
	OAuth2RestTemplate template = new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(accessTokenRequest));
	AccessTokenProviderChain provider = new AccessTokenProviderChain(Arrays.asList(new AuthorizationCodeAccessTokenProvider()));
	provider.setClientTokenServices(clientTokenServices());
	return template;
}
```


##Customizations for Clients of External OAuth2 Providers（外部OAuth2提供程序的客户端的自定义）

某些外部OAuth2提供程序（例如Facebook）不能完全正确地实现规范，或者它们只是停留在比Spring Security OAuth更旧的规范版本上。 要在客户端应用程序中使
用这些提供程序，您可能需要调整客户端基础结构的各个部分。

以Facebook为例，在tonr2应用程序中有一个Facebook功能（您需要更改配置以添加自己的，有效的客户ID和密码-它们很容易在Facebook网站上生成）。

Facebook令牌响应在令牌的到期时间中还包含一个不兼容的JSON条目（使用到期而不是expires_in），因此，如果您想在应用程序中使用到期时间，则必须使用
自定义OAuth2SerializationService对其进行手动解码 。
