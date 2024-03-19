## Maximum Subarray:

I started by reading the specifications. I realized, there was no description of the behaviour if null was passed to the function, so I decided to make the function return 0 in that case. I then started to construct categories and partitions. In the book, there was mostly one category per input parameter. Since this function had only one input parameter, I went straight onto partitions:


Null

Negative numbers (Variations: All negative, Negative and positive)

Input array length (Variatins: empty, 1, >1)

Subarray length (Variatins: empty, 1, >1)

Subarray Position (Variations: Beginning, Middle, End)

Zeros (Variations: Leading, Trailing, Both)

Multiple largest subarrays

To decrease The number of tests, Special cases Null, empty and array with length one are only tested once. For the other partitions, I did some overlapping thests where I saw it but other than that tested them all in combination with each other. I probably ended up writing a few non-useful tests, but the effort of weeding these out would have exceeded the effort of just writing a few more. I ended up catching one bug with testMaxSubArrayEmpty(), where the empty array case was not defined. I added this code to the function:

```
if (a == null){
        return 0;
    }

if (a.length == 0) {
        return 0;
}
```
The structural testing revealed a 100% line coverage of the method and 93% line coverage of the class. Since it is a static method, the class is not inteded to be initialised, so the missing line:
```public class MaximumSubarray {```
Is not an issue. 
Condition + Branch coverage was 100%.

The Mutation testing with Pitest immediately had the result of 6/6 Mutants killed.
The specification based testing was good enough, such that no missing test cases were revealed with structural and mutation testing.
