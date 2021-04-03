JAVA_CLASS_FILE_PATH 	= proto/java/relations/bin
JAVA_SRC_PATH 	= proto/java/relations/src/
JAVA_TEST_PATH 	= proto/java/relations/test/

.PHONY: java-testbuild
java-testbuild:
			javac -d $(JAVA_CLASS_FILE_PATH) \
				$(JAVA_SRC_PATH)RelationChainer.java \
				$(JAVA_SRC_PATH)RelationChainerException.java \
				$(JAVA_TEST_PATH)RelationChainerTest.java
			
			java -cp $(JAVA_CLASS_FILE_PATH) \
				relations.test.RelationChainerTest
