# Alphabetizer
A program that can read in a series of strings and output the strings with the characters in alphabetical order.

# Supported Technologies 
Below is a list of technologies used to build this application. Any previous versions may work, but are not supported.

- Maven 3.6.3
- Java 1.8

# Building
Clone the repository
```
git clone https://github.com/ttamagni/alphabetizer.git
```

Build using Maven
```
mvn clean package
```

# Usage 
Once application is built, you can execute **Alphabetizer.jar** in the target directory 
```
java -jar target/Aplphabetizer.jar
```

To read from standard input
```
java -jar target/Aplphabetizer.jar < testfile.txt
```

# Example Test File 
```
3 Blind Mice
Virginia Tech
H o k I E S
H*o*k*I*E*S
123VT.%$#HoKI$s!
The quick brown fox jumps over the lazy dog.
```




