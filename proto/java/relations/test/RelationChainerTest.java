package relations.test;

import java.util.SplittableRandom;

import relations.src.RelationChainer;

public class RelationChainerTest {
  private StringBuilder expression; // a randomly generated boolean expression
  private int num_of_operators; // # of operators corresponding to the most recent method call of genFormattedExpression()
  private Integer[] true_ints; // an integer array generated that is dependent upon 'expression'
  private int mini, maxi;


  public RelationChainerTest() {
    expression = new StringBuilder();
    genFormattedExpression(); // The constructor creates a formatted string

    mini = -10_000;
    maxi = 10_000;

    true_ints = new Integer[num_of_operators+1];
    genTrueIntValues();
  }

  /*
  *         RelationChainerTest (constructor)
  * Fields utilized:
  *   int min : the minimum value for an integer element in true_ints
  *   int max : the max value for an integer element in true_ints
  *
  * note: The 'intRandomizer()' seems to have some issues with particular min
  * and max values as it will based on their values generate a negative value
  * with simple arithmetic and passed as a parameter to nextInt() method.
  *
  * An overloaded constructor.
  */
  public RelationChainerTest(int min, int max) {
    expression = new StringBuilder();
    genFormattedExpression(); // The constructor creates a formatted string

    mini = min;
    maxi = max;

    true_ints = new Integer[num_of_operators+1];
    genTrueIntValues();
  }

  /*
  *         genFormattedExpression
  * Fields utilized:
  *   int num_of_operators
  *   StringBuilder expression
  *
  * Generates a boolean expression (with %d format specifiers)
  */
  private void genFormattedExpression() {
    num_of_operators = intRandomizer(1,5); // # of operators for the boolean expression

    for(int i = 0; i < num_of_operators; i++) {
      expression.append("%d");
      chooseRelationalOp(expression, intRandomizer(1,6)); // chooses an operator based on a randomized choice
    }
    expression.append("%d");
  }

  /*
  *         genTrueIntValues
  * Fields utilized:
  *   Integer[] true_ints
  *   StringBuilder expression
  *
  * Generates integers with the condition of statisfying the given
  * formatted boolean expression. This method is somewhat analogous in its
  * implementaion with RelationChainer's relation() method.
  */
  private void genTrueIntValues() {
    StringBuilder operator = null;
    String curr_element = null;
    int j = 0;

    // note: This is a utility funciton, no error handling exists
    // as the expression is created not by an end user but by
    // 'genFormattedExpression()'

    // Initialize the first element with an arbitrary integer
    true_ints[0] = intRandomizer(mini, maxi);

    for(int i = 0; i < expression.length(); i++) {
      curr_element = Character.toString(expression.charAt(i));

      if(Character.isWhitespace(expression.charAt(i)))
      continue;

      if(RelationChainer.isRelationalOp(curr_element)) {
        if(operator == null)
        operator = new StringBuilder(curr_element);

        else {
          operator.append(curr_element); // applies to >=, <=, and ==
        }
      }

      // If we encounter a format specifer
      else if(curr_element.equals("%")) {
        if(operator == null) // no operators to work with
        continue;

        if(j+1 < true_ints.length) {
          determineTrueInt(operator.toString(), j++);
          operator = null; // remove the current operator
        }
      }
    }
  }

  /*
  *         genTruthfulExpression
  *
  * Utilizes the 'expression' and 'true_ints' fields as
  * parameters for RelationChainer's relation() methods
  */
  public void printTruthfulExpression(RelationChainer r) {
    System.out.println(expression);
    System.out.println(r.relation("show",
    expression.toString(), true_ints));
  }

  // * *
  // Helper Methods
  // * *

  /*
  *         intRandomizer
  * Parameters:
  *   int min: an integer lower bound for number randomization
  *   int max: an integer upper bound for number randomization
  *
  * Randomizes a number given a lower and upper bound using
  * java.util.SplittableRandom class
  */
  public int intRandomizer(int min, int max) {
    return new SplittableRandom().nextInt(max - min) + min;
  }

  /*
  *         chooseRelationalOp
  * Parameters:
  *   StringBuilder input: represents a relational operator
  *   int choice: an integer that determines what relational operator to choose
  *
  * Appends a string to some StringBuilder given a specific 'choice'
  */
  private StringBuilder chooseRelationalOp(StringBuilder input, int choice) {
    switch(choice) {
      case 1  : return input.append(" > ");
      case 2  : return input.append(" < ");
      case 3  : return input.append(" >= ");
      case 4  : return input.append(" <= ");
      case 5  : return input.append(" == ");
      default : return input.append("invalid");
    }
  }

  /*
  *         determineTrueInt
  * Parameters:
  *   String expression: represents a relational operator
  *   int j: the current index of the field 'true_ints'
  *
  * Generates a random number given a certain relational
  * operator.
  */
  private void determineTrueInt(String expression, int j) {
    switch(expression) {
      case ">"  : true_ints[j+1] = intRandomizer(mini, true_ints[j]-1); break;
      case "<"  : true_ints[j+1] = intRandomizer(true_ints[j]+1, maxi); break;
      case ">=" : true_ints[j+1] = intRandomizer(mini, true_ints[j]); break;
      case "<=" : true_ints[j+1] = intRandomizer(true_ints[j], maxi); break;
      case "==" : true_ints[j+1] = true_ints[j]; break;
      default: ;
    }
  }

  //* Getters
  public StringBuilder getExpression() { return expression; }
  public Integer[] getTrueInts() { return true_ints; }
  public int getNumOfOperators() { return num_of_operators; }

  public static void main(String[] args) {
    RelationChainer r = new RelationChainer();
    RelationChainerTest t = new RelationChainerTest();

    new RelationChainerTest(-100000, 100000).printTruthfulExpression(r);

    /* ** ** **
    Testing
    public boolean relation(String dummy, String input, Integer... prms)
    ** ** ** */
  }
}
