package proto.java.relations.test;

import proto.java.relations.src.RelationChainer;

public class RelationChainerTest {

  public static void main(String[] args) {
    RelationChainer r = new RelationChainer();
    System.out.println(
    r.relation("x > y",
    "%d > %da", 2, 1));

  }
}
