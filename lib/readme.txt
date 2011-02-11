My original goal was to be able to incorporate the standard RESTClient UI JAR file into this project. This would allow
the RESTClient version to be easily updated by simply replacing the JAR. Unfortunately, the RESTClient JAR cannot be
used as-is. In order to prevent a conflict between the XML parsing APIs included in the JAR and the APIs included in
IDEA, the org.xml.* package must be removed from the standard RESTClient JAR. The modified JAR is checked into this
project's lib directory and distributed within the plugin. Newer versions of this JAR will need to be similarly modified
in the future.