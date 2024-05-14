# Book reviews

## A
1. I will mock the `BookRatingsFetcher`, since its `all` method communicates with the database, which if not mocked could lead to slow tests and complicated databse setup necessary for the tests. There is no need to mock `DatabaseConnection` since no method of it is directly called during our tests. However, it will be entirely removed from the `BookManager` class as part of the refactoring. The books will not be mocked, as they are simple objects without external dependencies.

2. To make the testing easier, dependency injection is necessary. As `BookRatingsFetcher` will be mocked, its now passed as a argument to the `highRatedBooks` method. Since the instantiation of the object doesnt take place inside `BookManager` anymore, `DatabaseConnection` wont be necessary as an argument. The Instantiation will have to be done outside of this codesnippet and this would have to be done with further reformatting. Controlability and Observability are sufficient already.

3. If there was a change in the `BookRatngsFetcher`, for example it would return an array instead of a list of books, the tests would not fail, since the mocks still return a list. So if the fetcher was changed, the changes and violations of contracts would have to be carefully considered, also in the test suite. Another problem is is the increased coupling of the method with the tests. Because the fetcher is mocked, I need to know that the `all` method of the fetcher is called and consider this in my tests, so if the implementation of `highRatedBooks` changed, my tests might have to change aswell, even if the contract stayed the same.

## B

I extended the `Book` with an author field and getter. Then created the `uniqueAuthors` method and finally created `BookAuthorFetcher` for this new method.

1. For the tests I mocked `BookAuthorFetcher` for the same reasons as in A.

2. I also used dependency injection via method parameter as in A.

3. If there was a change in the `BookAuthorFetcher`, for example it would return an array instead of a list of authors, the tests would not fail, since the mocks still return a list. So if the fetcher was changed, the changes and violations of contracts would have to be carefully considered, also in the test suite. Another problem is is the increased coupling of the method with the tests. Because the fetcher is mocked, I need to know that the `all` method of the fetcher is called and consider this in my tests, so if the implementation of `uniqueAuthors` changed, my tests might have to change aswell, even if the contract stayed the same.

To follow best practices, I gave the test functions very descriptive words. I also 
Todo, make Creating and closing mock a pre and post test execution.

