# RelationChainer
RelationChainer is simply in attempt to implement Python's relational operator chaining functionality into a multitude of other programming languages, such as Java, C++, and possibly more to come. This repository also exists for me to become more familiar with the git/github technologies.

# 
These implementations are by no means to look or be very professonial/efficient (though I still aim for them to be so), moreso a simple challenge I have for some reason became eager to take on after seeing a [Lex Fridman video](https://www.youtube.com/watch?v=HPfPFM1wNmE) discussing this interesting Python feature.  

---

## Implementations Explained
  ### For Java:
 (Updated: 29 Aug 2020)
 
All the neccessary computations exist within a single instance method ```relation()``` (with the aid of several helper methods) that is within a class ```RelationChainer```:

```java
public boolean relation(String dummy, String input, Integer... prms) {
...
}
```

As the method signature suggests, there is a heavy reliance on strings; so much so that I decided to use the ```String.format()``` method as the backbone for my method for a multitude reasons.

The most important:

- Existing error handling within this String method
- A formatted string provides an easier linear tranversal (the method is created in a way that it only considers numbers (for now only integer types) and characters related to relational operators)

<b>Limitations of using a string format method:</b>

Since they can take anything as input (as long as it follows certain rules of course), that means anything can be thrown into the string my implementation works with, which would need extensive filtering features.


Lets dig into the parameters by using the method in an example:
```java
public static void main(String[] args) {
    RelationChainer r = new RelationChainer();
    int x = 2, y = 5, z = 3;
    
    System.out.println(r.relation("  x < y > z  ",
    "%d < %d > %d",
    x, y, z));
    
    // This will print out "true"
}
```

With the first parameter,```dummy```, it only exists to provide readability as it is not easy on the eyes to read the second parameter, ```input```. This and the last parameter, ```params```, is essentially how you would implement formatted strings (as you would see using ```System.out.printf(...)```). ```params``` being an array of parameters of ```Integer``` type would only accept values of its own type.

To see this mathematical expression more clearly, ```dummy``` has a special string value of "show" that will display the boolean expression with the given integer values (i.e the command line will read "2 < 5 > 3"). 

If for any who are reading this wish to mess around with the source code, you're more than welcome to do so. I provided another class, ```RelationChainerTest```, that upon instantiation it not only generates a formatted boolean expression but also generates an array of integers with the condition of satisfying the given formatted boolean expression (that is, the integers are created in a way that it evaluates the expression to true). They can be accessed using their corresponding getter methods. If you just wish to see the ```relation()``` method in action, call```genTruthfulExpression()```, which takes RelationChainerTest's ```expression``` and ```true_ints``` fields as relation()'s parameters and behaves in the same manner as it would if you were adding values / variables into the parameter list. 

Here is an example of what has been discussed above:

```java
public static void main(String[] args) {
    RelationChainer r = new RelationChainer();
    RelationChainerTest t = new RelationChainerTest(); 
    
    // upon instantiation, there exists a generated expression and generated array of integers corresponding to the expression
    t.genTruthfulExpression(r); 
}
```
The method prints the following:
```
%d < %d <= %d > %d >= %d
2089 < 6524 <= 8945 > -1490 >= -2977
true
```
As it is seen, the method will first print out a formatted boolean expression, the same expression with the addition of the generated integers, and the return value of relation().

<b>If you by chance encounter any bugs or figured you found a better optimization within the code that could be utilized, don't be afraid to throw them my way.</b>
