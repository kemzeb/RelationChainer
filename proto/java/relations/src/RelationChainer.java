package proto.java.relations.src;

import java.util.UnknownFormatConversionException;

public class RelationChainer {

// * *
  // Instance Methods
// * *

//! Little to no proper error handling currently for this method
  // If some runtime exception is caused by the end user, this method will
  // just return a false value (i.e 1 <<= 2 >= 0 will return false rather than
  // an exception)
  public boolean relation(String dummy, String input, Integer... prms) {
    boolean output = true;
    boolean opr_exists = false;
    StringBuilder operator = null;
    String formatted = null;

    //* Capture the formatted string to provide exisitng error handling API
    try {
      formatted = String.format(input, (Object[])prms);
    } catch(UnknownFormatConversionException e) {
      e.printStackTrace();
    } //* If this passes, 'input' has correct format specifiers

    // Below displays the formatted string in the console if given a
      // special 'dummy' value of "show"
    if(dummy.equals("show")) {
      System.out.println(formatted);
    }

      //* Is the string empty and is there varargs arguements
    if(!formatted.isEmpty() && prms.length >= 0) {
        // If there exists some relation character at the beginning,
          // the entire expression is invalid
      if(!Character.isDigit(formatted.charAt(0)) ) {
        return false; // !!! Error handling requried
      }

      int j = 0; // represents prms indices
      String curr_element; // used to lessen the use of string methods for better readability
      for(int i = 0; i < formatted.length(); i++) {
        curr_element = Character.toString(formatted.charAt(i));

        //* Ignore whitespace
        if(Character.isWhitespace(formatted.charAt(i)) ) {
          continue;
        }
        // Is the current element a "relational operator"
        if(isRelationalOp(curr_element)) {
          if(operator == null) {
            operator = new StringBuilder(curr_element);
          }
          else if(operator.length() > 2) {
            return false; // !!! Error handling required (could use break??)
          }
          else {
            operator.append(curr_element); // applies to >=, <=, and ==
          }
        }

        // If the current element is a positive or negative integer
        else if(Character.isDigit(formatted.charAt(i)) ||
        (i+1 < formatted.length() && curr_element.equals("-")
        && Character.isDigit(formatted.charAt(i+1))) ) {
          if(operator == null)
            continue;

          opr_exists = true; // operators exist within the expression (EH)
          if(j+1 < prms.length) {
            output = output & calculateBinaryRelation(operator.toString(),
            prms[j], prms[++j]);
            operator = null;
          }
        }
        else
          return false; // !!! the boolean expression contains invalid characters
      }
      //* If operator is not null, the boolean expression is invalid
      if(operator != null) return false; // !!! Error handling required
      if(!opr_exists) return false; // !!! Error handling required
    }

    //* No formatted string or no varargs arguements
    else {
      return false;
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
      default: return false; // !!! Error handling required
    }
  }

  /*
  *         calculateBinaryRelation
  * Parameters:
  *   String in: represents a relational operators
  *   int x: the current element in the prms array in relation()
  *   int y: the next element within the prms array in relation()
  *
  * Calculates a single boolean operation
  */
  private boolean calculateBinaryRelation(String in, int x, int y) {
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
      default: return false; // !!! Error handling required
        // The error would be an incorrect relational string value (i.e <<)
    }
  }

  public static void main(String[] args) {
  }
}
