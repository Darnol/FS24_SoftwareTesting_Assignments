## atoi

### 1. Specification-Based Testing

#### 1.1 Understand the Requirement
To understand the requirements the README.md file is thoroughly analyzed. The fact that the combinations can be returned
in any order seems to give rise to much ambiguity in the behaviour of the function. The last sentence in the description
is also rather confusing. One could ask themselves whether the function makes sure that the combinations do not exceed 
150 or whether for the sake of this exercise we should avoid such complex cases. We interpret it as the latter. 

#### 1.2 Explore the Program
Compared to the atoi function the requirements of the function are very poorly specified. The description says it should
work for integers, but we immediately realize that the function breaks if negative integers are used as input. For the
further discussion we assume that the requirements intend to work for the integers greater or equal one. We want to 
emphasize again that the function is so poorly specified that most of the inputs become untestable, because we are
lacking context. It is not specified what should happen to empty lists, null inputs and negative numbers. We can only
assume that the function is intended to work for strictly positive numbers.

#### 1.3 Identify the Partitions
For the number of elements of the input array we have the following partitions: Empty (unspecified), one element, and 
multiple elements. We can also differ between the input array having duplicates (unspecified) and not having duplicates.
The target value has the following partitions: Non-positive (unspecified) and strictly positive. The output could be
divided into the partitions: No possible combination, single possible combination, and multiple combinations. Additional
partitions for the target can be chosen in combination with the input array: Target smaller than all elements, target 
smaller than some and bigger than some elements, and target larger than all elements combined. Since we are lacking
context for the unspecified partitions we will omit testing them. In a real-world scenario we would contact the
development team for more concise specifications instead of guessing what the function is supposed to do.

Technically, all permutations of solutions are possible solutions since it explicitly says in the requirements that the 
combinations can be in any order. This implies that there are a wide range of equivalence classes regarding the output 
of the function. Technically, we would have to allow our tests to pass if one the possible permutations is returned. For
the sake of simplicity the only canonical solution is tested/allowed. Meaning that smallest elements come first in lists
and lists with smaller elements come first.

#### 1.4 Analyze the Boundaries
...

#### 1.5 Devise Test Cases
Again each partition and its boundaries were tested separately. 

#### 1.6 Automate Test Cases
The tests from the step before are implemented using the JUnit library.

#### 1.7 Augment
In the end a few partitions were mixed and more creative tests were written. The authors doubts that these tests indeed
cover cases that were not covered before.

### 2. Structural Testing

Using the Jacoco plugin we saw as before that the instruction coverage was not 100% but only because the line declaring 
the class itself was not covered in the tests. This time the branch coverage was not 100%. On line 23:

`} else if (target == 0) {`

One branch was missed. We implement a test case where the target is a negative number to cover the remaining branch. In 
a real-scenario we would of course contact the developer team since this case was never specified. Afterward branch
coverage becomes 100%.

### 3. Mutation Testing

One mutation in the mutation testing was not killed. On line 9:

`Arrays.sort(candidates);`

To kill this mutant we take an already implemented test, copy it, and reverse the order of the candidates. Afterward 
100% of the mutants are killed.