package proto.java.relations.src;

//import java.util;
import java.util.Stack;
import java.util.UnknownFormatConversionException;

public class RelationChainer {

//! Little to no error handling as of yet for this method
  public boolean relation(String dummy, String input, Integer... prms) {
    boolean output = true;
    StringBuilder operator = null;
    String formatted = null;

    //* Capture the formatted string to provide exisitng error handling API
    try {
      formatted = String.format(input, (Object[])prms);
    } catch(UnknownFormatConversionException e) {
      e.printStackTrace();
    } //* If this passes, 'input' has correct format specifiers
    System.out.println(formatted);

      //* Is the string empty and is there varargs arguements
    if(!formatted.isEmpty() && prms[0] != null) {
        // If there exists some relation character at the beginning
      if(isRelationalOp(Character.toString(formatted.charAt(0))) ) {
        return false; // !!! Error handling requried
      }

      int j = 0; // represents prms indices
      String curr_element; // used to lessen the use of methods for better readability
      for(int i = 0; i < formatted.length(); i++) {
        curr_element = Character.toString(formatted.charAt(i));

        //* Ignore whitespace
        if(Character.isWhitespace(formatted.charAt(i)) ) {
          continue;
        }
        // Is the current element a "relational opeartor"
        if(isRelationalOp(curr_element)) {
          if(operator == null) {
            operator = new StringBuilder(curr_element);
          }
          else if(operator.length() > 3) {
            return false; // !!! Error handling required (could use break??)
          }
          else {
            operator.append(curr_element); //update stack top

          }
        }

        else if(isDigit(formatted.charAt(i))) { // If current is a number
          if(operator == null) continue;
          if(j+1 < prms.length) {
            output = output & calculateBinaryRelation(operator.toString(),
            prms[j], prms[++j]);
            operator = null;
          }
        }
        else
          return false; // the mathematical expression contains invalid characters
      }
      //* If the StringBuilder is not null, the mathematical expression is invalid
      if(operator != null) return false; // !!! Error handling required
    }

    //* No formatted string or no varargs arguements
    else
      return false;

    return output;
  }

  //* This is a overloaded method in current development
    // It may essentially bypass the need of a formatted string
  public boolean relation(String input, Integer... prms) {
    StringBuilder mutable_input = new StringBuilder(input);

    return false;
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
        // Only these considered as we are transversing a string linearly
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
    //if(in == null) return false;
    return Character.isDigit(in);
  }

  public static void main(String[] args) {
  }
}
