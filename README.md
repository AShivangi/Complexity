# Complexity
This project compares the running times of Strassen's multiplication and regular multiplication. To test he two algorithms, JUnit was used
The running times are as follows:-
For N = 4,
	Running time of regular multiplication is 0.027633 ns
	Running time for strassen's multiplication is 0.778461 ns
	Running time of regular multiplication where array is given is 0.022896 ns
	Running time for strassen's multiplication where array is given is 0.413706 ns

For N = 16,
	Running time of regular multiplication is 1 ns
	Running time for strassen's multiplication is 5 ns
	
For N = 512,
	Running time of regular multiplication is 150 ns
	Running time for strassen's multiplication is 8665 ns
	
For N = 1024,
	Running time of regular multiplication is 39886 ns
	Running time for strassen's multiplication is 494154 ns
	
Conclusion: 
The algorithms have almost the same outputs (with a difference of 0.0001) which can be concluded from the JUnit tests. In all the above 
cases, the regular multiplication is faster than strassen's multiplication as in strassen's algorithm, the constant is significantly large 
and hence effects the running time. Asymptotically, strassen's algorithm is faster than regular multiplication but due to small sizes of 
the matrices, solving sub-matrices in strassen's costs more time than regular multiplication.
