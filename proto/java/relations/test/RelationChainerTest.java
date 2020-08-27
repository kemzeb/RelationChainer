package proto.java.relations.test;

import proto.java.relations.src.RelationChainer;

public class RelationChainerTest {
  private StringBuilder format; // a randomly generated format relation expression

  public RelationChainerTest() {
    format = new StringBuilder();
    genFormattedString(format);
  }

  public int intRandomizer(int min, int max) {
    //int range = (max - min) + 1;
    return (int)(Math.random() * (max - min + 1)) + min;
  }

    //* initStringInput generates a unique string format
  public void genFormattedString(StringBuilder sb) {
    int num_of_operators = intRandomizer(1, 4);

    for(int i = 0; i < num_of_operators; i++) {
      int choice = intRandomizer(1,5);
      sb.append("%d");
      chooseRelationalOp(sb, choice);
    }
    sb.append("%d");
  }

  private StringBuilder chooseRelationalOp(StringBuilder input, int choice) {
    switch(choice) {
      case 1:
      return input.append(" > ");
      case 2:
      return input.append(" < ");
      case 3:
      return input.append(" >= ");
      case 4:
      return input.append(" <= ");
      case 5:
      return input.append(" == ");
      default: return input.append("invalid");
    }
  }

  //* getStrFormat returns a generated formatted string
  public StringBuilder getFormat() { return format; }

  public static void main(String[] args) {
    RelationChainer r = new RelationChainer();
    RelationChainerTest t = new RelationChainerTest();

    /* ** ** **
      Testing
public boolean relation(String dummy, String input, Integer... prms)
    ** ** ** */


  }
}
