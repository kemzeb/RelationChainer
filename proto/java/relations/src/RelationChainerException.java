package relations.src;

import java.lang.RuntimeException;

// * This is not an extensive implementaion of a runtime exception,
  // but I thought having some level of exception handling is better
  // than having runtime exceptions return false.
public class RelationChainerException extends RuntimeException {

    RelationChainerException(String message) {
      super(message);
    }
}
