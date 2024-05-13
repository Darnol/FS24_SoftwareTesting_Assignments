# Book reviews

## A
1. I will mock the `BookRatingsFetcher`, since its `all` method communicates with the database, which if not mocked could lead to slow tests and complicated databse setup necessary for the tests. There is no need to mock `DatabaseConnection` since no method of it is directly called during our tests. However, it will be entirely removed from the `BookManager` class as part of the refactoring. The books will not be mocked, as they are simple objects without external dependencies.

2. To make the testing easier, dependency injection is necessary. As `BookRatingsFetcher` will be mocked, its now passed as a argument to the `highRatedBooks` method. Since the instantiation of the object doesnt take place inside `BookManager` anymore, `DatabaseConnection` wont be necessary as an argument. The Instantiation will have to be done outside of this codesnippet and this would have to be done with further reformatting.

3. ...

## B

Extended Book with author field and getter
Changed other tests to recieve author when instantiating object
Created Method in BookManager
Created BookAuthorFetcher for this new method.

Todo, make Creating and closing mock a pre and post test execution.