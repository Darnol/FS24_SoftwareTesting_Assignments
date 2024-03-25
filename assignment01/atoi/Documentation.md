## atoi

### 1. Specification-Based Testing

#### 1.1 Understand the Requirement
To understand the requirements the README.md file is thoroughly analyzed. So far no uncertainty or ambiguities in the
specification were identified.

#### 1.2 Explore the Program
First explore the "normal" behaviour of the program characterised by the simplest cases described in the README.md under
point 4 and 5. Then move on the special inputs as described in 2, more exotic behaviour as in 3, and edge cases as in
point 6. No difference between the specified behaviour and actual behaviour was found.

#### 1.3 Identify the Partitions
Now after establishing the context of the function we try to identify the partitions. The ground of the numbers
partitions are the valid numbers:

`-inf < x < -2^31`,
`-2^31 <= x < 0`,
`0`,
`0 < x <= 2^31 - 1`,
`2^31 - 1 < x < inf`

Note that `0` as well as the positive number could also appear with a `+` in front. `0` could also appear with a `-` in
front. Further we partition the valid numbers into the numbers with leading zeros and without leading zeros. The signs
can be partitioned into `-`, `+` and no sign. At last the whole input needs to be partitioned into input with and 
without leading spaces, into input with characters after the first non-digit character and nothing after said
character, and invalid inputs. In the following is a
representation of the input where the partitions can be read from.

`<spaces><sign><zeros><number><non-digit char><chars>`

#### 1.4 Analyze the Boundaries
We talk about the boundaries for each partition. While trying to encode some partitions, `|` is used to represent 
boundaries. For the number partitions we have the following boundaries:

`-2^31 - 1 | -2^31`,
`-1 | 0`,
`0 | 1`,
`2^31 - 1 | 2^31`

The boundary for an input with leading zeros and no leading zero is:

`0<number> | <number>`

The boundary for the sign partitions is:

`negative numbers | zero | positive numbers`

The boundary for leading spaces partition is:

`<space><non-space char> | <non-space char>`

The boundary for additional characters after the non-digit character is:

`<non-digit char> | <non-digit char><chars>`

The boundaries for the partition of having a digit before a non digit character (invalid inputs) are:

`null | <empty> | <whitespaces><sign> | <non-digit char (excl. ' ' and signs)> | <digit><non-digit char>`

#### 1.5 Devise Test Cases
Each partition and its boundaries were tested separately. 

#### 1.6 Automate Test Cases
The tests from the step before are implemented using the JUnit library.

#### 1.7 Augment
In the end a few partitions were mixed and more creative tests were written. The authors doubts that these tests indeed
cover cases that were not covered before.

### 2. Structural Testing

Using the Jacoco plugin we saw that the instruction coverage was 96% and the branch coverage was 100%. The instruction
coverage was not 100% because the line declaring the atoi class itself was not covered in the tests. This was ignored
because our goal was to test the function properly.

### 3. Mutation Testing

One mutation in the mutation testing was not killed. On line 28:

`            if (num > (Integer.MAX_VALUE - digit) / 10) {`

The change of the conditional test was not detected. Two tests were added that also test the number right next to the 
largest integer in the valid partitions. Afterward all mutations were killed.