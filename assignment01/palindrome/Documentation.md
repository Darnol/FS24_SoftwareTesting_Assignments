I started by reading the specifications of the given program. The program only had one input and one output, an integer and a boolean value respectively. Thinking of the possible values the input could take that would make the program behave differently, I tried to identify the partitions. Originally, I came up with three partitions: negative integers, palindromic positive integers, and non-palindromic positive integers. Although these three partitions felt quite complete, I thought the program might behave differently for varying number of digits. Hence, I divided the positive integer partition into palindromic integers with even number of digits, palindromic integers with odd number of digits, non-palindromic integer with even number of digits, and non-palindromic integer with odd number of digits. I also wanted to try single digits integers, as they should automatically be considered palindromes. Finally, I wanted to the test the program with the minimum and the maximum integer values as they were specified in the constraints. I ended up with the following test cases:
- Negative integers
- Positive integers
	- Palindromic, odd number of digits
	- Palindromic, even number of digits
	- Non-palindromic, odd number of digits
	- Non-palindromic, even number of digits
- Single digits (0... 9)
- Min value -2^20
- Max value 2^20-1

Note: During the process of deciding test cases, I played the devil's advocate and tried to think of ways to break the program. One specific case that came to my mind was integers with leftmost 0's. What would happen if the program allowed users to enter integers manually and a user tried to test the value 010, for example? As the leading 0's don't contribute to the value of the integer and therefore 010 is actually 10, one might expect the program to return "false". On the other hand, another person might think that the backwards of 010 is still 010 and hence the result should be "true". I wrote a test case for this specific situation, and turns out Java considers any integer with leading 0's to be an octal integer. So according to Java, 010 is not decimal 10 but actually decimal 8. Since 8 is a palindrome, when presented with the input 010, the program gave the output "true". Because the specifications did not indicate how the program should behave in this case, and because of Java's own behaviour, I decided not to tackle this problem. However, it is something worth noting.

In total, I wrote 8 different test cases. I used JUnit's parameterized tests and ran the tests with different input values. Results were as given below:
- PalindromeOne passed all the tests
- PalindromeTwo failed the single digits case for 0

Because PalindromeTwo failed the single digits case for 0, I added the line below to the function:
```
if (x == 0) return true
```

Then, I used JaCoCo plugin to see the code coverage of my test suite. For PalindromeOne, the structural testing revealed 100% instruction coverage and 100% branch coverage for the method, and 96% instruction coverage and 100% branch coverage for the class. The missing line 
```public class PalindromeOne {```
 does not create an issue and can be overlooked. The same is also true for PalindromeTwo, which has 100% instruction coverage and 100% branch coverage for the method.

As the last step, I utilized mutation testing to further enhance my test suites. Unfortunately neither test suite killed all the mutants.
PalindromeOne (mutation coverage 88%):
- CONDITIONALS_BOUNDARY: Conditional boundary in the line "while (start < end)" was changed to "while (start <= end)", and the test suite did not catch it. This change does not affect the behaviour of our method. Actually, the condition could have been written as "while (start <= end)" from the get go, and it would not have been wrong. The mutant version simply means that we will be checking if the character in the middle of the integer is equal to itself, which is a redundant step but it will always return true. Thus, the mutant is irrelevant.
PalindromeTwo (mutation coverage 81%):
- CONDITIONALS_BOUNDARY:
- MATH: 

