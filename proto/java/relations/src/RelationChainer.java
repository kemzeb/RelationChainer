package proto.java.relations.src;

import java.util.Stack;
import java.util.UnknownFormatConversionException;

public class RelationChainer {

// * *
  // Instance Methods
// * *

//! Little to no proper error handling currently for this method
  // Currently, if an error is caused by the end user, this method will
  // just return a false value (i.e 1 <<= 2 >= 0 , this would be a constitute)
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
      if(isRelationalOp(Character.toString(formatted.charAt(0))) ) {
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

        else if(isDigit(formatted.charAt(i))) { // If current ele is a number
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
          return false; // !!! the mathematical expression contains invalid characters
      }
      //* If operator is not null, the mathematical expression is invalid
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

    //* isRelationalOp checks if the formatted string is an relational operator
  private boolean isRelationalOp(String op) {
    switch(op) {
      case ">":
      case "<":
      case "=": return true;
        // Only these chars considered as we are transversing a string linearly
      default: return false;
    }
  }
    //* calculateBinaryRelation calculates a single relation operation
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

    //* Determines whether a given string is a valid number
  private boolean isDigit(Character in) {
    return Character.isDigit(in);
  }

  public static void main(String[] args) {
  }
}
