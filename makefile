compile:
	javac *.java;
run:
	java XMLChecker cd_catalog.xml;
	java XMLChecker cd_catalog_bad.xml;
	java XMLChecker guestbook.xml;
	java XMLChecker guestbook_bad.xml;
clean:
	rm *.class;
