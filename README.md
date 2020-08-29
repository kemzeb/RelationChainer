# RelationChainer
RelationChainer is simply in attempt to implement Python's relational operator chaining functionality into a multitude of other programming languages, such as Java, C++, and possibly more to come. This repository also exists for me to become more familiar with the git/github technologies.

# 
These implementations are by no means to look or be very professonial/efficient (though I still aim for them to be so), moreso a simple challenge I have for some reason became eager to take on after seeing a [Lex Fridman video](https://www.youtube.com/watch?v=HPfPFM1wNmE) discussing this interesting Python feature.  

---

## Implementations Explained
  ### For Java:
 (Updated: 27 Aug 2020)
 
All the neccessary computations exist within a single instance method ```relation()``` (with the aid of several helper methods) that is within a class ```RelationChainer```:

```java
public boolean relation(String dummy, String input, Integer... prms) {
...
}
```

As the method signatures suggest there is a heavy reliance on strings, so much so that I decided to use the ```String.format()``` method as the backbone for my method for a multitude reasons.

The most important:

- Existing error handling within this String method
- A formatted string provides an easier linear tranversal (the method is created in a way that it only consider numbers (for now only integer types) and characters related to relational operators)

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

To see this mathematical expression more clearly, ```dummy``` has a special string value of "show" that will display the formatted string with the given integer values (i.e the command line will read "2 < 5 > 3"). 

If for those who are reading this wish to mess around with the source code, you're more than welcome to do so. I provided another class, ```RelationChainerTest```, that generates a chain of relational expressions in case you don't wish to type some down yourself (though you still need to provide integer parameters). If you by chance encounter any bugs, don't be afraid to report them back to me.
