Categories

Special cases
array contains null x
1 or 2 null, x
1 or 2 not sorted x
Both empty x
one empty x

Even/uneven integrated into other tests x

array lenght: empty, one, >one integrated into other tests x

Negative x

all numbers in one array bigger than the other or intemixed x

all same number x

Edge cases:
negative --> zero

Added
if (nums1.length == 0 && nums2.length == 0) return 0;

p1 = 0; p2 = 0;

if (array[i] > array[i + 1]) {

Check null before length otherwise exception

Even uneven integrated into other tests

Contains null not possible due to type checking

with jacoco I missed these lines

        } else if (p2 < nums2.length) {
            return nums2[p2++];
        }
        return -1;