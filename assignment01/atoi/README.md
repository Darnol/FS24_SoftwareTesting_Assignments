# Problem: String to Integer

## Description

Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).

The algorithm for myAtoi(string s) is as follows:

1. Read in and ignore any leading whitespace.
2. If input is null, return 0.
3. Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
4. Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
5. Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
6. If the integer is out of the 32-bit signed integer range [-2^31, 2^31 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -2^31 should be clamped to -2^31, and integers greater than 2^31 - 1 should be clamped to 2^31 - 1.
7. Return the integer as the final result.

### Note:

Only the space character ' ' is considered a whitespace character.
Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.

### Example 1:

**Input**: `s = "42"`
**Output**: `42`

### Example 2:

**Input**: `s = "   -42"`
**Output**: `-42`


### Example 3:

**Input**: `s = "4193 with words"`
**Output**: `4193`


### Example 4:

**Input**: `s = "abc"`
**Output**: `0`

