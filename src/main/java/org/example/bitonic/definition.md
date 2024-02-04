# Bitonic Arrays

An array composed of an ascending and then descending sequence of integers, with a point called "bitonic point" being
where the array converges from ascending to descending, and the greater value.

## Search implementation

We'll search through the array with a modified version of Binary Search, that finds the bitonic point and performs
search on both sides, or returns null if the key to find is greater than the highest value.

