## Palindrome:

# Specification-based testing
I started by reading the specifications of the program. I realized the behaviour of the function was not described for cases where only one of the input strings was empty. I decided to make the function return -1 in this case, and added the line below:
```
if (haystack.isEmpty() || needle.isEmpty()) return -1;
```

For the inputs hay and needle, I devised the partitions. Taking into consideration the partitions and the possible boundaries, I ended up with the following test cases:
- Null cases
	- Both needle and hay are null
	- Only needle is null
	- Only hay is null
- Empty string cases
	- Both needle and hay are empty
	- Only needle is empty
	- Only hay is empty
- Needle is not in haystack
	- Only half of the needle is in haystack
	- Needle is shorter than haystack but no part of needle is in haystack
	- Needle is longer than haystack
- Needle is in haystack
	- Needle is in haystack once
	- Needle is in haystack multiple times
	- Needle starts in the first position
	- Needle starts in somewhere in the middle
- Needle, hay or both has special characters (space, +, ? etc.)

In order to keep the number of test cases small, I haven't combined all of these cases. For example, I only tested null cases and empty string cases seperately from the rest. I also did not combine special characters case with any other case.

Results:
- Passed all the tests

# Structural testing
For this step, JaCoCo plugin was used to see the code coverage of the test suite. The structural testing revealed 100% instruction coverage and 100% branch coverage for the method, and 96% instruction coverage and 100% branch coverage for the class. The missing line 
```class NeedleInHay {```
 does not create an issue and can be overlooked.

# Mutation Testing
As the last step, I utilized mutation testing to further enhance my test suite.

NeedleInHay (mutation coverage 95%, 19 mutants killed out of 20):
- CONDITIONALS_BOUNDARY: Conditional boundary in the line ```for(int i = 0; i < (lenHay-lenNed + 1); i++)``` was changed to ```for(int i = 0; i <= (lenHay-lenNed + 1); i++)```, and the test suite did not catch it. This line basically means that we will iterate through the haystack string until the remaining length of haystack is smaller than that of the needle, hence it is impossible for the remaining of the haystack to contain the needle. The mutant does not change the behaviour of our program, only adds a redundant iteration to our for loop. This extra iteration would pose a problem if ```lenHay - lenNed + 1 > lenHay```, because then in the last iteration, the method would try to reach an index that is out of bounds. However, ```lenHay - lenNed + 1 > lenHay``` implies that ```1 > lenNed```, and the method already covers this case from the very beginning. Therefore, I decided not to write a test case to kill this mutant.


