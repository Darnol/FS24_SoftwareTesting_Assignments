# Movie Streaming:

### Unit Tests for the Methods in MovieStreamingManager Class
The MovieStreamingManager class has one method `streamMovie()` which takes only an id. It also requires a FileStreamService instance and a CacheService instance. Both of these are injected into the MovieStreamingManager class and can be mocked. However, as mocking has its disadvantages, it is important to choose when and what to mock.

1. Both FileStreamService and CacheService are external dependencies that need to be mocked. When `streamMovie()` is examined, it can be seen that the method works on a cache-hit or cache-miss basis. To make sure the MovieStreamingManager interacts with the services correctly, one test case with cache-hit and one test case with cache-miss were created.

2. This part created a few problems. Firstly, `updateMovieMetadata(String movieId, MovieMetadata metadata)` and `validateStreamingToken(String token)` methods did not exist in the MovieStreamingManager class. I created a `updateMovieMetadata(String movieId, MovieMetadata metadata)` method that just calls upon the same method in FileStreamService. However, as the job of `validateStreamingToken(String token)` was not done by any other method, I could not quite figure out how to handle it. Regardless, even for the `updateMovieMetadata()` method, because there was no logic behind it, creating tests for it seemed illogical.

3. As per assignment directions, 2 test cases with simulated failures in external dependencies were added to the test suite to ensure the behavior of the MovieStreamingManager under these circumstances. The results were as expected, and the tests all passed. First test simulated a simple FileStreamService error that can be considered as downtime, and the second test simulated a token generation error. As the tests created for task 1 covered cache misses, no test cases were added for it.

## Some Notes
I changed the project structure, created `zest` package, and created a separate folder for tests.

Tests were written in a manner that prevents flakiness as much as possible. A `setUp()` method was used to ensure each test would have clean, reliable resources to use.

For task 2, `assertEquals()` assertion did not work for comparing streaming details and cache details. Therefore, I used an argument captor and compared all the information in the details one by one.
