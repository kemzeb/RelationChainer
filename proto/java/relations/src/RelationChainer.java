package proto.java.relations.src;

import java.util.UnknownFormatConversionException;

public class RelationChainer {

  // * *
  // Instance Methods
  // * *

  public boolean relation(String dummy, String input, Integer... prms) {
    boolean output = true;
    boolean opr_exists = false;
    StringBuilder operator = null;

    // !!! Capture the input string to provide exisitng error handling API
    try {
      input = String.format(input, (Object[])prms);
    } catch(UnknownFormatConversionException e) {
      e.printStackTrace();
    } //* If this passes, 'input' has correct format specifiers

    // Below displays the input string in the console if given a
    // special 'dummy' value of "show"
    if(dummy.equals("show")) {
      System.out.println(input);
    }

    try {
      //* Is the string not empty and is there varargs arguements
      if(!(input.isEmpty()) && prms.length > 1) {

        // !!! If there exists some relation character at the beginning,
        // the entire expression is invalid
        if(input.charAt(0) != '-' && !Character.isDigit(input.charAt(0)) ) {
          throw new RelationChainerException("Expression cannot start with a non-integer.");
        }

        int j = 0; // represents prms indices
        String curr_element; // used to lessen the use of string methods for better readability

        for(int i = 0; i < input.length(); i++) {
          curr_element = Character.toString(input.charAt(i));

          //* Ignore whitespace
          if(Character.isWhitespace(input.charAt(i)) ) {
            continue;
          }
          // Is the current element considered a "relational operator"
          if(isRelationalOp(curr_element)) {
            if(operator == null) {
              operator = new StringBuilder(curr_element);
            }
            else if(operator.length() > 2) {
              throw new RelationChainerException("Expression contains an invalid operator.");
            }
            else {
              operator.append(curr_element); // applies to >=, <=, and ==
            }
          }

          // If the current element is a positive or negative integer
          else if(Character.isDigit(input.charAt(i)) ||
          (i+1 < input.length() && curr_element.equals("-")
          && Character.isDigit(input.charAt(i+1))) ) {
            if(operator == null)
            continue;

            opr_exists = true; // operators exist within the expression
            if(j+1 < prms.length) {
              output = output & calculateBinaryRelation(operator.toString(),
              prms[j], prms[++j]);

              operator = null; // reset operator to null
            }
          }

          // !!! The boolean expression contains invalid characters
          else
            throw new RelationChainerException("Expression contains invalid characters.");
        } // END OF FOR LOOP //

        // !!! If operator is not null, the boolean expression is invalid
        if(operator != null)
          throw new RelationChainerException("Expression is missing an integer value.");
        if(!opr_exists)
          throw new RelationChainerException("Expression contains no relational operators.");
      }

      //* No input string or no varargs arguements
      else {
        throw new RelationChainerException("Expression doesn't exist or no integer arguments exist.");
      }
    } // END OF TRY //
    catch(RelationChainerException e) {
      e.printStackTrace();
    }
    return output;
  }

  // * *
  // Helper Methods
  // * *

  /*
  *         isRelationalOp
  * Parameters:
  *   String op: represents a relational operator
  *
  * Checks if op contains a character that pertains to a relational operator
  */
  public static boolean isRelationalOp(String op) {
    switch(op) {
      case ">":
      case "<":
      case "=": return true;
      default: return false;
    }
  }

  /*
  *         calculateBinaryRelation
  * Parameters:
  *   String in: represents a relational operators
  *   int x: the current element in the prms array in relation()
  *   int y: the next element within the prms array in relation()
  *
  * Calculates a single boolean operation, private as it requires
  * a specific scenario for it to output properly.
  */
  private boolean calculateBinaryRelation(String in, int x, int y) {
    try {
      switch(in) {
        case ">":
        return Integer.compare(x,y) > 0 ? true : false;
        case "<":
        return Integer.compare(x,y) < 0 ? true : false;
        case ">=":
        return Integer.compare(x,y) >= 0 ? true : false;
        case "<=":
        return Integer.compare(x,y) <= 0 ? true : false;
        case "==":
        return Integer.compare(x,y) == 0 ? true : false;
        default: throw new RelationChainerException("Expression contains an incorrect relational operator.");
        // !!! The error would be an incorrect relational string value (i.e <<)
      }
    }
    catch(RelationChainerException e) {
      e.printStackTrace();
    }
    return false;
  }
}
