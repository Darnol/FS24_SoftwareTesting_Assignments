1. There is one external dependency, which is `HttpUtil`. I will mock this dependency, because it communicated with an external API, which can make the tests slow or flaky (If the external API isn't available for some reason)

2. To replace the dependency easily with a mock in testing, I have to use dependency injection. I will pass an `HttpUtil` object to the `CatFactsRetriever` in the constructor. (Not as a method parameter, because all methods in `CatFactsRetriever` use it). I also made the `get` method non-static, to make it easily mockable with mockito. By doing that I can pass in a Mock for my tests. Controlability and Observability are sufficient already.



When mocking limit=0 , i went to check what happened and realised, a long list instead of empty is sent, so when mocking, the contract of external service needs to be understood well. Same with negatice numbers so I will implement a precondition.