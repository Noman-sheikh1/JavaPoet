

# JavaPoet

## What is JavaPoet?

**JavaPoet is a Java library (API) used to generate Java source code (.java files) programmatically.**

Normally, when we create a Java class, we write something like:

```java
public class Student {
    private String name;

    public void printName() {
        System.out.println(name);
    }
}
```

We type this ourselves.

With **JavaPoet**, we don't manually write this class.

Instead, we write Java code that **creates another Java file automatically**.

Think of it like this:

```
Your Java Program
        │
        │
        ▼
 JavaPoet Library
        │
        │
        ▼
Generated Student.java file
```

So JavaPoet writes Java code for you.

---

# Official Definition

> JavaPoet is a Java API for generating .java source files.

Let's understand every word.

### Java API

API (Application Programming Interface) means **a collection of classes and methods that we can use**.

Just like:

```java
ArrayList<String> list = new ArrayList<>();
```

Here, `ArrayList` is part of Java's API.

Similarly,

JavaPoet provides classes like

* JavaFile
* TypeSpec
* MethodSpec
* FieldSpec

which help us generate Java code.

---

### Generating

Generating means

**creating automatically.**

Instead of writing

```
Student.java
Teacher.java
Employee.java
```

your Java program creates them.

---

### .java source files

Java source files are simply Java files.

Example:

```
Student.java

Employee.java

Main.java
```

JavaPoet generates these files.

---

# Why do we need JavaPoet?

Imagine a company has

```
1000 database tables.
```

For every table, they need

```
Employee.java

Department.java

Salary.java

Attendance.java
...
```

Each class contains

* fields
* getters
* setters
* constructors

Writing all of this manually is very time-consuming.

JavaPoet can generate all these files in seconds.

---

# Official Line

> Source file generation can be useful when doing things such as annotation processing or interacting with metadata files.

This sentence contains two important concepts.

---

# 1. Source File Generation

Source file generation simply means

> Creating Java source code automatically.

Instead of

```
Create Student.java manually
```

JavaPoet creates it.

---

# 2. Annotation Processing

This is one of JavaPoet's biggest use cases.

Suppose you write

```java
@Entity
public class Student {

}
```

The compiler sees `@Entity`.

During compilation, an **annotation processor** can examine this annotation and generate additional Java code automatically.

For example,

```
Student.java

↓

Annotation Processor

↓

StudentDao.java

StudentRepository.java

StudentMapper.java
```

Many popular libraries use this idea:

* Lombok
* Dagger
* AutoValue

These libraries often generate code during compilation, and JavaPoet has historically been a helpful tool for creating that generated source.

---

# 3. Metadata Files

The official documentation also says

> interacting with metadata files

First understand

## What is Metadata?

Metadata means

> Data about data.

Example:

Suppose a database contains

```
Table: Student

Columns:

id

name

age
```

This information is metadata.

It describes the structure of the data.

---

Another example

Suppose you have a JSON schema

```json
{
  "name": "Student",
  "fields": [
    "id",
    "name",
    "age"
  ]
}
```

This is metadata.

JavaPoet can read this and generate

```java
public class Student {

    int id;

    String name;

    int age;

}
```

No manual coding.

---

# Database Schema Example

Suppose the database has

```
Employee

id INT

name VARCHAR

salary DOUBLE
```

JavaPoet can generate

```java
public class Employee {

    int id;

    String name;

    double salary;

}
```

Automatically.

---

# Protocol Formats

The documentation also mentions

> protocol formats

Examples include:

* Protocol Buffers (protobuf)
* gRPC definitions
* OpenAPI specifications
* GraphQL schemas

These describe how systems communicate.

JavaPoet can generate Java classes from those definitions.

Example:

Protocol definition

```
message User {

int id;

string name;

}
```

↓

Generated Java class

```java
public class User {

    int id;

    String name;

}
```

---

# Official Line

> By generating code, you eliminate the need to write boilerplate

This is one of the biggest reasons JavaPoet exists.

---

## What is Boilerplate Code?

Boilerplate means

**code that is repetitive and almost identical every time.**

Example

```java
private String name;

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}
```

You'll write similar code in almost every class.

JavaPoet can generate this repetitive code automatically.

---

# Official Line

> keeping a single source of truth

This is an important software engineering concept.

Suppose you have

Database

```
Employee

id

name

salary
```

If you manually create Java classes,

```
Employee.java
```

and later the database changes,

```
salary

↓

monthlySalary
```

You now have to update every Java file yourself.

Instead, if the database schema is the **single source of truth**, JavaPoet regenerates all Java files from it. That way, everything stays consistent.

```
Database Schema
        │
        ▼
    JavaPoet
        │
        ▼
Generated Java Classes
```

Only the schema needs to be maintained.

---

# Real-World Analogy

Imagine you're a school administrator.

You have details for 5,000 students in Excel.

Instead of typing 5,000 ID cards manually, you create a program that reads the Excel sheet and prints every ID card automatically.

Here:

* Excel sheet = Metadata
* Program = JavaPoet
* ID cards = Generated Java files

---

# Key Terms to Remember

| Term                   | Meaning                                                      |
| ---------------------- | ------------------------------------------------------------ |
| JavaPoet               | A Java library that generates Java source code               |
| Generate               | Create automatically                                         |
| Source File            | A `.java` file                                               |
| Boilerplate            | Repetitive code written again and again                      |
| Annotation Processing  | Generating code based on annotations during compilation      |
| Metadata               | Information describing the structure of data (e.g., schemas) |
| Single Source of Truth | One authoritative place where information is maintained      |

---

# One-Line Summary

**JavaPoet is a Java library that lets you write Java code to automatically generate other Java source files. It is especially useful for creating repetitive (boilerplate) code from metadata, annotations, or schemas, ensuring consistency and reducing manual work.**


---

# JavaPoet Example: HelloWorld

The official documentation first shows a normal Java class:

```java
package com.example.helloworld;

public final class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello, JavaPoet!");
    }

}
```

This is just a normal Java program.

When you compile it, it prints:

```text
Hello, JavaPoet!
```

Nothing special so far.

---

# The Goal

Instead of **writing this class manually**, we want JavaPoet to **generate it automatically**.

Think of JavaPoet as a **builder**.

Instead of typing Java code yourself, you tell JavaPoet:

> Create a class.

> Add a method.

> Add a parameter.

> Add a print statement.

Finally JavaPoet writes the Java file for you.

---

# Visual Overview

```
                    You write this

            MethodSpec
                 │
                 ▼
           TypeSpec (Class)
                 │
                 ▼
            JavaFile (.java)
                 │
                 ▼
      HelloWorld.java is generated
```

This is exactly how almost every JavaPoet program works.

---

# Step 1: Creating the Main Method

```java
MethodSpec main = MethodSpec.methodBuilder("main")
```

## What is MethodSpec?

MethodSpec is a JavaPoet class that represents **a Java method**.

For example,

```java
public void show() {

}
```

or

```java
public static void main(String[] args) {

}
```

Every Java method can be represented by a MethodSpec object.

---

### methodBuilder("main")

```java
MethodSpec.methodBuilder("main")
```

means

> Create a new method named **main**.

At this point JavaPoet only knows:

```
Method Name = main
```

Nothing else.

It doesn't know

* return type
* parameters
* modifiers
* code

We'll add those next.

---

# Step 2: Add Modifiers

```java
.addModifiers(
    Modifier.PUBLIC,
    Modifier.STATIC
)
```

This generates

```java
public static
```

So now JavaPoet knows

```
public static main(...)
```

Think of it as filling in pieces of the method.

---

# Step 3: Return Type

```java
.returns(void.class)
```

means

```java
void
```

Now the generated method becomes

```java
public static void main(...)
```

---

# Step 4: Add Parameters

```java
.addParameter(String[].class, "args")
```

This means

Parameter Type

```java
String[]
```

Parameter Name

```java
args
```

Generated code becomes

```java
public static void main(String[] args)
```

---

# Step 5: Add Code

```java
.addStatement(
    "$T.out.println($S)",
    System.class,
    "Hello, JavaPoet!"
)
```

This is probably the most confusing line for beginners.

Let's break it down.

---

## addStatement()

Means

> Add one line of Java code.

Equivalent to writing

```java
System.out.println("Hello");
```

inside the method.

---

## What is `$T`?

```
$T
```

means

> Type

JavaPoet will insert a Java class.

Here

```java
System.class
```

becomes

```java
System
```

---

## What is `$S`?

```
$S
```

means

> String Literal

If you pass

```java
"Hello"
```

JavaPoet automatically generates

```java
"Hello"
```

including the quotation marks.

---

So

```java
.addStatement(
    "$T.out.println($S)",
    System.class,
    "Hello, JavaPoet!"
)
```

becomes

```java
System.out.println("Hello, JavaPoet!");
```

---

### Why use `$T` and `$S` instead of writing the whole line as a string?

Suppose you wrote:

```java
.addStatement("System.out.println(\"Hello\")");
```

This works, but it becomes difficult for more complex code.

JavaPoet's placeholders make generation safer and easier.

Some common placeholders are:

| Placeholder | Meaning                       | Example Output         |
| ----------- | ----------------------------- | ---------------------- |
| `$T`        | Java Type/Class               | `System`               |
| `$S`        | String literal                | `"Hello"`              |
| `$L`        | Literal value                 | `100`, `true`, `x + y` |
| `$N`        | Name (method/field/parameter) | `count`                |

You'll see these frequently in JavaPoet.

---

# Step 6: build()

```java
.build();
```

Every JavaPoet builder ends with `build()`.

Before `build()`, you're still describing what you want. After `build()`, JavaPoet creates the immutable object representing that method.

So after this line, `main` represents:

```java
public static void main(String[] args){
    System.out.println("Hello, JavaPoet!");
}
```

---

# Step 7: Create the Class

```java
TypeSpec helloWorld =
    TypeSpec.classBuilder("HelloWorld")
```

## What is TypeSpec?

TypeSpec represents a **Java type**.

In Java, a type can be:

* Class
* Interface
* Enum
* Annotation

Here we're creating a **class**.

---

### classBuilder()

means

Create a class named

```
HelloWorld
```

---

# Step 8: Add Modifiers

```java
.addModifiers(
    Modifier.PUBLIC,
    Modifier.FINAL
)
```

Generates

```java
public final class HelloWorld
```

---

# Step 9: Add the Method

```java
.addMethod(main)
```

Remember we already created the main method.

Now we're saying

> Put that method inside this class.

Think of it like:

```
Class
    │
    └── Method
```

Without this line, the class would be empty.

---

# Step 10: build()

Again,

```java
.build();
```

Now the class is complete.

It represents

```java
public final class HelloWorld {

    public static void main(String[] args) {

    }

}
```

---

# Step 11: Create the Java File

```java
JavaFile javaFile =
    JavaFile.builder(
        "com.example.helloworld",
        helloWorld
    )
    .build();
```

## What is JavaFile?

JavaFile represents an entire `.java` source file.

A `.java` file contains:

* Package
* Imports (if needed)
* Class(es)

So JavaFile combines everything into one complete source file.

---

### Package

```java
"com.example.helloworld"
```

becomes

```java
package com.example.helloworld;
```

---

### Second Parameter

```java
helloWorld
```

means

Put this class into that package.

---

# Step 12: Write the File

```java
javaFile.writeTo(System.out);
```

This does **not** create a file on disk.

Instead, it prints the generated Java code to the console.

Output:

```java
package com.example.helloworld;

public final class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello, JavaPoet!");
    }

}
```

---

# Other Ways to Output

The documentation mentions two other options:

### 1. Convert to a String

```java
javaFile.toString();
```

Returns the generated code as a Java `String`. Useful if you want to inspect it or include it in logs.

---

### 2. Write to a File

```java
javaFile.writeTo(path);
```

Writes the generated `.java` file to the filesystem, which is what annotation processors commonly do.

---

# How Everything Connects

```
MethodSpec
     │
     ▼
Creates Method

     │
     ▼

TypeSpec
     │
     ▼
Creates Class

     │
     ▼

JavaFile
     │
     ▼
Creates .java File

     │
     ▼

HelloWorld.java
```

This is the core workflow of JavaPoet.

---

# Builder Pattern

One thing you may notice is that every object is built in the same way:

```java
MethodSpec.methodBuilder(...)
          .addModifiers(...)
          .returns(...)
          .addParameter(...)
          .addStatement(...)
          .build();
```

This is called the **Builder Pattern**. Each method adds one piece of information, and `build()` creates the final object. JavaPoet uses this pattern throughout its API because it makes constructing complex Java code readable and flexible.

---

# Notes for Your README

## JavaPoet HelloWorld Example

### Main Classes

| Class        | Purpose                                                 |
| ------------ | ------------------------------------------------------- |
| `MethodSpec` | Represents a Java method                                |
| `TypeSpec`   | Represents a Java class, interface, enum, or annotation |
| `JavaFile`   | Represents an entire `.java` source file                |

### Flow of Code Generation

```
MethodSpec
      ↓
TypeSpec
      ↓
JavaFile
      ↓
Generated .java file
```

### Common Builder Methods

| Method            | Purpose                                         |
| ----------------- | ----------------------------------------------- |
| `methodBuilder()` | Creates a new method                            |
| `classBuilder()`  | Creates a new class                             |
| `addModifiers()`  | Adds modifiers like `public`, `static`, `final` |
| `returns()`       | Sets the return type                            |
| `addParameter()`  | Adds method parameters                          |
| `addStatement()`  | Adds a line of Java code                        |
| `addMethod()`     | Adds a method to a class                        |
| `build()`         | Creates the final immutable object              |

### Common Placeholders

| Placeholder | Meaning                         | Example                |
| ----------- | ------------------------------- | ---------------------- |
| `$T`        | Java Type/Class                 | `System`               |
| `$S`        | String Literal                  | `"Hello"`              |
| `$L`        | Literal Value                   | `100`, `true`, `x + y` |
| `$N`        | Name (method, field, parameter) | `count`                |

### Output Methods

| Method                | Purpose                                         |
| --------------------- | ----------------------------------------------- |
| `writeTo(System.out)` | Prints the generated code to the console        |
| `toString()`          | Returns the generated source code as a `String` |
| `writeTo(Path/File)`  | Writes the generated `.java` file to disk       |

> **Key idea to remember:** You are **not writing Java source code directly**. Instead, you're writing Java code that *describes* a Java class. JavaPoet then generates the actual `.java` file from that description. Once this mental model clicks, the rest of the JavaPoet API becomes much easier to understand.
----

Perfect! This is actually one of the **most important sections** in the JavaPoet documentation because it teaches you **how to generate method bodies** (the code inside methods like loops, `if-else`, `try-catch`, etc.).

I'll explain it in a way that even if you've never used JavaPoet before, you'll understand it.

---

# Code & Control Flow

Before reading the code, first understand **what problem JavaPoet is solving.**

Suppose you want JavaPoet to generate this Java method:

```java
void calculate() {
    int total = 0;

    for(int i = 0; i < 10; i++) {
        total += i;
    }
}
```

Normally you would type this yourself.

With JavaPoet, **you never type this method directly.**

Instead you tell JavaPoet

> Create a method.

> Inside it write

```java
int total = 0;
```

Then write

```java
for(...)
```

Then write

```java
total += i;
```

JavaPoet then generates the method.

---

# First Paragraph

The documentation says

> Most of JavaPoet's API uses immutable Java objects.

Don't worry about the complicated words.

It simply means

Every Java thing has its own object.

| Java Thing | JavaPoet Class |
| ---------- | -------------- |
| Class      | TypeSpec       |
| Method     | MethodSpec     |
| Field      | FieldSpec      |
| Parameter  | ParameterSpec  |
| Annotation | AnnotationSpec |

Think of these as different LEGO pieces.

```
Class

↓

TypeSpec
```

```
Method

↓

MethodSpec
```

```
Field

↓

FieldSpec
```

Each Java thing has its own builder.

---

# But there is one exception

Look at this Java method.

```java
public void sum(){

    int total = 0;

    total++;

}
```

Here,

JavaPoet understands

✔ Method

✔ Class

✔ Parameter

✔ Field

But what about these lines?

```java
int total = 0;
```

```java
total++;
```

These are called **method body**.

The documentation says

JavaPoet **does not model every Java statement individually**.

Instead,

it simply stores them as strings.

That is why we have

```java
.addCode()
```

---

# First Example

```java
MethodSpec main = MethodSpec.methodBuilder("main")
```

Creates a method called

```
main
```

---

Then

```java
.addCode(
""
+ "int total = 0;\n"
+ "for(int i=0;i<10;i++){\n"
+ " total += i;\n"
+ "}\n"
)
```

Imagine you're writing a paragraph inside Microsoft Word.

JavaPoet simply copies these lines exactly.

So

```java
.addCode(
"int total = 0;\n"
)
```

becomes

```java
int total = 0;
```

Notice

```
\n
```

means

> Go to the next line.

---

Then

```java
"for(int i=0;i<10;i++){\n"
```

becomes

```java
for(int i=0;i<10;i++){
```

Then

```java
"total += i;\n"
```

becomes

```java
total += i;
```

---

Generated code

```java
void main(){

    int total = 0;

    for(int i=0;i<10;i++){

        total += i;

    }

}
```

---

# Problem with addCode()

The documentation says

Writing everything manually is **tedious**.

Why?

Because you have to remember

semicolon

```java
;
```

newline

```
\n
```

spaces

indentation

opening brace

```java
{
```

closing brace

```java
}
```

Imagine writing a 500-line method.

It becomes very difficult.

---

# JavaPoet Solution

Instead of

```java
.addCode(
"int total = 0;\n"
)
```

JavaPoet says

Use

```java
.addStatement()
```

---

# addStatement()

```java
.addStatement("int total = 0")
```

You didn't write

```java
;
```

JavaPoet automatically adds

```java
;
```

Generated

```java
int total = 0;
```

Notice

You wrote

```java
int total = 0
```

JavaPoet generated

```java
int total = 0;
```

Nice!

---

# beginControlFlow()

This is one of the most useful methods.

Suppose you want

```java
for(int i=0;i<10;i++){

}
```

Instead of writing

```java
.addCode("for(...){")
```

you simply write

```java
.beginControlFlow(
"for(...)"
)
```

JavaPoet automatically writes

```java
for(...){

}
```

---

# Example

```java
.beginControlFlow(
"for(int i=0;i<10;i++)"
)
```

JavaPoet writes

```java
for(int i=0;i<10;i++){

```

Notice

You didn't write

```
{
```

JavaPoet did.

---

Then

```java
.addStatement(
"total += i"
)
```

becomes

```java
total += i;
```

---

Finally

```java
.endControlFlow()
```

adds

```java
}
```

Automatically.

---

So

```java
MethodSpec.methodBuilder("main")
.addStatement("int total = 0")
.beginControlFlow("for(int i=0;i<10;i++)")
.addStatement("total += i")
.endControlFlow()
```

generates

```java
void main(){

    int total = 0;

    for(int i=0;i<10;i++){

        total += i;

    }

}
```

---

# Real Life Analogy

Imagine you're writing an essay.

Without JavaPoet

```
Write every comma.

Write every bracket.

Write every semicolon.

Write every indentation.
```

With JavaPoet

```
Start paragraph

↓

Write sentence

↓

End paragraph
```

JavaPoet fills in the formatting.

---

# computeRange()

This example is more interesting.

The documentation creates

a method

that generates another method.

Read that again.

A method

creates another method.

---

Suppose

You call

```java
computeRange(
"multiply10to20",
10,
20,
"*"
)
```

Look carefully.

Arguments

```
Name

↓

multiply10to20
```

```
From

↓

10
```

```
To

↓

20
```

```
Operation

↓

*
```

JavaPoet replaces these values while generating the code.

Generated method

```java
int multiply10to20(){

    int result = 1;

    for(int i=10;i<20;i++){

        result = result * i;

    }

    return result;

}
```

See what happened?

The inputs (`10`, `20`, `"*"`, and the method name) became part of the generated source code.

---

# nextControlFlow()

This is used when one control-flow block continues into another.

For example,

Normal Java

```java
if(condition){

}
else{

}
```

In JavaPoet

```java
.beginControlFlow("if(condition)")
```

↓

```java
.nextControlFlow("else")
```

↓

```java
.endControlFlow()
```

JavaPoet generates

```java
if(condition){

}
else{

}
```

---

It also works for

```java
if
```

↓

```java
else if
```

↓

```java
else
```

Exactly like the GitHub example.

---

# try-catch Example

Normal Java

```java
try{

}
catch(Exception e){

}
```

JavaPoet

```java
.beginControlFlow("try")
```

↓

```java
.nextControlFlow("catch(Exception e)")
```

↓

```java
.endControlFlow()
```

Generated

```java
try{

}
catch(Exception e){

}
```

---

# The Four Most Important Methods in This Section

| JavaPoet Method      | What it Generates                               | Easy way to remember                      |
| -------------------- | ----------------------------------------------- | ----------------------------------------- |
| `addCode()`          | Raw code exactly as you write it                | Copy-paste text into the method body      |
| `addStatement()`     | One Java statement                              | JavaPoet adds the `;` and newline for you |
| `beginControlFlow()` | Starts a block like `if`, `for`, `while`, `try` | Opens `{` automatically                   |
| `endControlFlow()`   | Ends the current block                          | Closes `}` automatically                  |
| `nextControlFlow()`  | Continues with `else`, `else if`, or `catch`    | Connects one control block to the next    |

---

## ⭐ The most important thing to remember from this section

When generating **method bodies**, JavaPoet doesn't build a detailed syntax tree for every statement. Instead, you describe the code using helper methods:

* Use `addStatement()` for normal lines of code.
* Use `beginControlFlow()` and `endControlFlow()` for blocks like `for`, `if`, `while`, and `try`.
* Use `nextControlFlow()` when one block naturally continues into another, such as `else`, `else if`, or `catch`.

This makes the generated code cleaner, easier to read, and saves you from manually writing braces, semicolons, newlines, and indentation. Once you understand these four methods, you'll be able to generate most method bodies with JavaPoet.

