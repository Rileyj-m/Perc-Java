/******************************************************************************
 *  Name: Riley Marsden
 *
 *  Hours to complete assignment (optional): around 18 hours.
 *
 ******************************************************************************/

Programming Assignment 1: Percolation


/******************************************************************************
 *  Describe how you implemented Percolation.java. How did you check
 *  whether the system percolates? This should be something like 3-5 sentences.
 *****************************************************************************/
I created a 2D boolean array, with a site above and below (top Holder, Bottom holder).
This allowed me to check if the system percolates because any site that is in the
first row of the array is connected to the top holder, and same for the bottom holder;
if everything is connected to those top and bottom elements then we know the system percolates.
This is true because of my implementation of open() by checking if it's open with isOpen() - that's simply checking if row, col = true,
and then checking to see if I can union the specific position to the top holder, bottom holder, or any other
index(left, right, top bottom); this allowed me to check if the system percolates.
I also implemented isFull() to check if at any index, it is connected to the top or bottom holder,
if it was then it's isFull().



/******************************************************************************
 *  Perform computational experiments to estimate the running time of
 *  PercolationStats.java for values values of n and T when implementing
 *  Percolation.java with QuickFindUF.java.
 *
 *  To do so, fill in the two tables below. Each table must have at least
 *  4 data points, ranging in time from around 0.1 seconds to at most a few
 *  minutes. Do not include data points that takes less than 0.1 seconds.
 *  Each data point should be double the size of the preceding data point
 *****************************************************************************/

(keep T constant; n doubles)

 n          time (seconds)
------------------------------
100         0.401
200         2.843
400         7.22
800         57.43
(keep n constant; T doubles)

 T          time (seconds)
------------------------------
100         1.718
200         2.395
400         3.893
800         6.799
/******************************************************************************
 *  Using the empirical data from the above two tables, give an estimate
 *  of the running time of PercolationStats.java as function of both n and 
 *  T, using asymptotic notation, like:
 *
 *       O( n^5.0 * T^1.5 )
 *
 *  Recall that with asymptotic notation, you are interested in the
 *  growth rate of run time as input sizes grow - you drop lower-order
 *  terms and constant factors. In class, we've discussed a way to 
 *  estimate polynomial factors from empirical run times.
 *
 *  How does this estimate compare to the theoretical run time growth
 *  rate, according to analysis of your code?
 *
 *****************************************************************************/
Growth Rate: O(N^2 * T)
This is right on track with the theoretical run time growth rate of
quick-find.



/******************************************************************************
 *  Repeat the previous three questions, but using WeightedQuickUnionUF
 *  (instead of QuickFindUF).
 *****************************************************************************/

(keep T constant; n doubles)

 n          time (seconds)
------------------------------
100         0.526
200         1.053
400         2.69
800         9.902
(keep n constant; T doubles)

 T          time (seconds)
------------------------------
100         1.017
200         1.623
400         2.293
800         3.435
Run time estimate: O(NlgN * lgT)

This seems like it is a little slower than the average runtime of WQU. Although
I'm unsure of my analysis, this was extremely difficult for me. I will be coming
to office hours

 
/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/
I think that you talked about this in class, and I think that it was called
back-washing? Where some connected sites at the bottom might show up as blue
when they aren't connected to the top. I really tried to see if I could solve this
issue, but I couldn't figure it out. (I didn't want to spend too much time on it,
I thought that I remembered you telling us not to worry about it, but I could
be wrong.)



/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, TA,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/
I did talk to several people from the 232 class about this percolation assignment,
but we didn't really talk about specific code or go over any troubles that I was
having. It was basically just a check in between us, so I'm not going to attribute
them by name.

I did get help from my boss at the internship that I did this year, and he helped
me really understand what you were asking in this assignment. He also helped me with
the percolation stats, specifically the confidence intervals.

/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/
The biggest problem that I faced was indexing. I had talked about it with you
when we met the first week of school. But I couldn't remember what the index
formula was for some reason. So I managed to come up with one on my own that works.

I also had some trouble with indexing in general. I would get out of bounds errors
and eventually just put some code in place to catch whenever something was going to always
return false, to keep the conditions that I had already set. Maybe that was because
I was using a 2d array, and I had to index to find the specific spot in the first place.

/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed (or hated) doing it.                                             
 *****************************************************************************/
I learned so much from this assignment, especially in regard to quick union. This
assignment really helped me to understand quick union, and solidified the topics of quick union.

I didn't hate anything about this, the only part that really frustrated me was the
indexing, and also the fact that I spend about 1 hour writing an algorithm to
find the number of open sites in O(n) time, only to realize that I can to it
in constant time by counting it when I open a site. (fail lol)
