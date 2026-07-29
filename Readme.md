

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

---


# JavaPoet Placeholders (`$L`, `$S`, `$T`, `$N`) - Complete Notes

> **This is one of the most important topics in JavaPoet.**
>
> Almost every JavaPoet program uses these four placeholders:
>
> * `$L` → Literal
> * `$S` → String
> * `$T` → Type
> * `$N` → Name

---

# Why do we need Placeholders?

When I first saw JavaPoet, I had one question:

> **Java already has `%s` in `String.format()`. Why did JavaPoet create `$L`, `$S`, `$T`, `$N`?**

The answer is simple.

`String.format()` is used to **format text**.

JavaPoet is used to **generate Java source code**.

These are two completely different things.

---

## Example using `%s`

```java
String code = String.format(
    "System.out.println(%s)",
    "Hello"
);

System.out.println(code);
```

Output

```java
System.out.println(Hello)
```

This is **wrong Java code**.

Why?

Because Java strings require quotation marks.

Correct Java code should be

```java
System.out.println("Hello");
```

Now look at JavaPoet.

```java
.addStatement(
    "System.out.println($S)",
    "Hello"
)
```

Generated code

```java
System.out.println("Hello");
```

JavaPoet automatically added the quotation marks.

That is why JavaPoet doesn't simply use `%s`.

It understands Java syntax.

---

# Difference Between `String.format()` and JavaPoet

| String.format()                 | JavaPoet                                  |
| ------------------------------- | ----------------------------------------- |
| Formats text                    | Generates Java source code                |
| Doesn't understand Java         | Understands Java syntax                   |
| Doesn't add imports             | Automatically generates imports           |
| Doesn't know classes or methods | Knows Java classes, methods, fields, etc. |

---

# Think of Placeholders Like Empty Spaces

Imagine writing

```
Hello ________
```

If I fill the blank with

```
Noman
```

Output

```
Hello Noman
```

JavaPoet placeholders work exactly the same way.

Instead of writing Java code manually,

you leave blanks

and JavaPoet fills them correctly.

---

# 1. `$L` → Literal

## Meaning

**Literal means:**

> Put the value exactly as it is.

JavaPoet does not modify it.

It simply copies it into the generated code.

---

## Example 1

```java
.addStatement(
    "int age = $L",
    22
)
```

Generated

```java
int age = 22;
```

Notice

JavaPoet copied

```
22
```

exactly.

---

## Example 2

```java
.addStatement(
    "double pi = $L",
    3.14
)
```

Generated

```java
double pi = 3.14;
```

---

## Example 3

```java
.addStatement(
    "boolean passed = $L",
    true
)
```

Generated

```java
boolean passed = true;
```

---

## Example 4

Suppose

```java
String operation="*";
```

JavaPoet

```java
.addStatement(
    "result = result $L i",
    operation
)
```

Generated

```java
result = result * i;
```

Notice

It copied

```
*
```

without quotation marks.

---

## Real-Life Analogy

Imagine a form.

```
Age : ______
```

Fill

```
22
```

Result

```
Age : 22
```

Nothing extra is added.

That is exactly what `$L` does.

---

# When should we use `$L`?

Use `$L` whenever you want JavaPoet to insert a value directly into the generated code.

Examples

* int
* long
* float
* double
* boolean
* operators (`+`, `-`, `*`, `/`)
* Java expressions
* numeric values

---

# 2. `$S` → String

## Meaning

`$S` means

> This is a Java String.

JavaPoet automatically

* adds quotation marks
* escapes special characters if necessary

---

## Example

```java
.addStatement(
    "return $S",
    "Hello"
)
```

Generated

```java
return "Hello";
```

Notice

You didn't write

```
"Hello"
```

JavaPoet added the quotation marks automatically.

---

## Another Example

```java
String student="Noman";

.addStatement(
    "System.out.println($S)",
    student
)
```

Generated

```java
System.out.println("Noman");
```

---

# Difference between `$L` and `$S`

Suppose

```
Hello
```

Using `$L`

```java
.addStatement("$L","Hello")
```

Generated

```java
Hello
```

No quotation marks.

---

Using `$S`

```java
.addStatement("$S","Hello")
```

Generated

```java
"Hello"
```

Quotation marks added automatically.

---

# Memory Trick

```
L

↓

Literal

↓

Copy exactly
```

```
S

↓

String

↓

Add quotation marks
```

---

# 3. `$T` → Type

This is probably the most powerful placeholder in JavaPoet.

## Meaning

`$T` means

> Insert a Java Class (Type).

JavaPoet also generates the necessary import statement automatically.

---

## Example

```java
MethodSpec method = MethodSpec.methodBuilder("today")
    .returns(Date.class)
    .addStatement("return new $T()", Date.class)
    .build();
```

Generated code

```java
import java.util.Date;

Date today() {
    return new Date();
}
```

Notice

You never wrote

```java
import java.util.Date;
```

JavaPoet generated it automatically.

---

## Another Example

```java
.addStatement(
    "$T list = new $T()",
    ArrayList.class,
    ArrayList.class
)
```

Generated

```java
import java.util.ArrayList;

ArrayList list = new ArrayList();
```

---

# Why is `$T` useful?

Imagine your project has

```
100 Java files
```

Suppose

```
Date
```

changes to another class.

Normally

You must manually update imports.

With JavaPoet

Just change

```java
Date.class
```

Everything updates automatically.

---

# ClassName

GitHub shows

```java
ClassName hoverboard =
ClassName.get(
    "com.mattel",
    "Hoverboard"
);
```

This confuses many beginners.

Suppose

```
Hoverboard.java
```

doesn't exist yet.

Can JavaPoet still generate code?

Yes.

Because

`ClassName`

stores

* package
* class name

Only.

Think of it as storing the address of a future class.

Even if the class does not exist yet,

JavaPoet can still generate

```java
import com.mattel.Hoverboard;
```

---

# ParameterizedTypeName

Java Generics

```
List<String>

List<Student>

List<Integer>
```

In JavaPoet

Generic types are created using

```java
ParameterizedTypeName
```

Example

```java
TypeName list =
ParameterizedTypeName.get(
    List.class,
    String.class
);
```

Generated

```java
List<String>
```

---

# 4. `$N` → Name

This placeholder is unique.

It means

> Use the name of another generated declaration.

---

Suppose we generated

```java
calculate()
```

Another generated method wants to call it.

Without `$N`

```java
.addStatement(
    "calculate()"
)
```

If tomorrow

you rename

```
calculate()
```

to

```
calculateMarks()
```

You must update every reference manually.

---

Using `$N`

```java
.addStatement(
    "$N()",
    calculateMethod
)
```

JavaPoet automatically extracts

```
calculate
```

from the MethodSpec.

Generated

```java
calculate();
```

If the method name changes,

every generated reference also changes automatically.

---

# GitHub Example

JavaPoet generates

```java
char hexDigit(int i){

}
```

Another generated method

```java
byteToHex()
```

calls

```java
hexDigit()
```

Instead of writing

```java
hexDigit(...)
```

manually,

JavaPoet writes

```java
$N
```

and passes the `MethodSpec` object.

JavaPoet reads its name

```
hexDigit
```

and generates

```java
result[0] = hexDigit(...);
```

---

# Import Static

Suppose normal Java

```java
Collections.sort(list);
```

With static import

```java
import static java.util.Collections.sort;
```

Now we can write

```java
sort(list);
```

JavaPoet supports this using

```java
.addStaticImport(Collections.class, "*")
```

Generated

```java
import static java.util.Collections.*;

sort(list);
```

---

# Comparison with `String.format()`

Many beginners ask

> Why doesn't JavaPoet simply use `%s`?

Because `%s` only replaces text.

It doesn't understand Java.

---

Suppose

```java
String.format(
    "return %s",
    "Hello"
)
```

Generated

```java
return Hello;
```

Wrong.

---

JavaPoet

```java
.addStatement(
    "return $S",
    "Hello"
)
```

Generated

```java
return "Hello";
```

Correct.

---

Suppose

```java
String.format(
    "%s",
    Date.class
)
```

Output

```
class java.util.Date
```

JavaPoet

```java
$T
```

Generated

```java
Date
```

along with

```java
import java.util.Date;
```

---

# Java `printf()` Placeholders vs JavaPoet Placeholders

Many people confuse these two.

## `printf()` / `String.format()`

| Placeholder | Meaning      |
| ----------- | ------------ |
| `%s`        | String       |
| `%d`        | Integer      |
| `%f`        | Float/Double |
| `%c`        | Character    |
| `%b`        | Boolean      |
| `%x`        | Hexadecimal  |

Example

```java
System.out.printf(
    "Age=%d",
    22
);
```

---

## JavaPoet

JavaPoet does **not** have

```
$d

$f

%b
```

Instead

it uses

```
$L
```

for every literal.

Example

Integer

```java
.addStatement(
    "int age = $L",
    22
)
```

Generated

```java
int age = 22;
```

Float

```java
.addStatement(
    "float marks = $L",
    92.5f
)
```

Generated

```java
float marks = 92.5f;
```

Boolean

```java
.addStatement(
    "boolean passed = $L",
    true
)
```

Generated

```java
boolean passed = true;
```

---

# Complete Placeholder Summary

| Placeholder | Full Form | Used For                                              | Automatically Does                                     | Example Input               | Generated Output                  |
| ----------- | --------- | ----------------------------------------------------- | ------------------------------------------------------ | --------------------------- | --------------------------------- |
| `$L`        | Literal   | Numbers, booleans, operators, expressions, raw values | Inserts the value exactly as it is                     | `22`, `3.14`, `true`, `"*"` | `22`, `3.14`, `true`, `*`         |
| `$S`        | String    | Java String literals                                  | Adds quotation marks and escapes special characters    | `"Hello"`                   | `"Hello"`                         |
| `$T`        | Type      | Java classes and types                                | Uses simple class names and generates required imports | `Date.class`                | `Date` + `import java.util.Date;` |
| `$N`        | Name      | Names of generated methods, fields, parameters, etc.  | Uses the generated declaration's name                  | `MethodSpec hexDigit`       | `hexDigit`                        |

---

# Memory Trick

```
$L
↓
Literal
↓
Copy exactly

-------------------------

$S
↓
String
↓
Add quotation marks

-------------------------

$T
↓
Type
↓
Java Class + Automatic Imports

-------------------------

$N
↓
Name
↓
Use the name of another generated declaration
```

---

# ⭐ Final Takeaway

JavaPoet placeholders are **not just text replacements** like `%s` in `String.format()`.

They understand the structure of Java source code:

* Use **`$L`** when you want to insert a literal value directly.
* Use **`$S`** when you want a Java string literal (with quotes).
* Use **`$T`** when referring to Java types or classes, letting JavaPoet manage imports.
* Use **`$N`** when referring to the name of another generated declaration, so references stay correct even if names change.

> **A simple way to remember them:**
>
> * **`$L`** → **Literal** (copy as-is)
> * **`$S`** → **String** (add quotes)
> * **`$T`** → **Type** (class + imports)
> * **`$N`** → **Name** (reference generated methods, fields, or parameters)
---
Excellent! You've reached one of the **most important sections of JavaPoet**. Up to now, you've learned:

* ✅ `MethodSpec`
* ✅ `TypeSpec`
* ✅ `JavaFile`
* ✅ `$L`, `$S`, `$T`, `$N`
* ✅ Control Flow

Now the GitHub documentation explains **how placeholders receive their values** and then introduces more features of `MethodSpec`.

Let's go through it **slowly**, with simple examples, so you can understand it deeply and make good notes.

---

# Part 1: Code Block Format Strings

## What is a Code Block?

Whenever you write:

```java
.addStatement("int age = $L", 22)
```

or

```java
.addStatement("$T.out.println($S)", System.class, "Hello")
```

the text inside quotes is called a **Code Block Format String**.

Think of it as a **template**.

Example:

```text
Template:
Hello, $S

Value:
"Noman"

Generated Code:
Hello, "Noman"
```

JavaPoet replaces placeholders with the values you provide.

---

# Three Ways to Pass Values

JavaPoet allows you to pass values in **three different ways**.

---

# 1. Relative Arguments (Most Common)

This is what you've been using.

### Syntax

```java
.add("I ate $L $L", 3, "tacos")
```

Let's understand it.

Template:

```text
I ate $L $L
```

Arguments:

```text
3
"tacos"
```

JavaPoet fills placeholders **from left to right**.

```
First $L  → 3

Second $L → tacos
```

Generated code:

```java
I ate 3 tacos
```

---

## Another Example

```java
.addStatement("int age = $L", 22)
```

Template

```text
int age = $L
```

Argument

```text
22
```

Generated

```java
int age = 22;
```

---

## Another Example

```java
.addStatement("$T.out.println($S)",
        System.class,
        "Hello")
```

First placeholder

```
$T
↓

System.class
```

Second placeholder

```
$S
↓

"Hello"
```

Generated

```java
System.out.println("Hello");
```

---

## Rule

Relative arguments work exactly like this:

```
Placeholder 1 ← Argument 1

Placeholder 2 ← Argument 2

Placeholder 3 ← Argument 3
```

This is why it's called **Relative**.

---

# 2. Positional Arguments

Suppose you want to use arguments in a different order.

Instead of writing

```java
3
"tacos"
```

you pass

```java
"tacos"
3
```

Normally this would generate

```java
I ate tacos 3
```

which is wrong.

JavaPoet solves this using **positions**.

Example

```java
.add("I ate $2L $1L", "tacos", 3)
```

Arguments

```
1 → "tacos"

2 → 3
```

Notice

```
$2L
```

means

```
Use argument number 2
```

```
$1L
```

means

```
Use argument number 1
```

Generated

```java
I ate 3 tacos
```

---

## Another Example

```java
.addStatement("$2T.out.println($1S)",
        "Hello",
        System.class)
```

Arguments

```
1 = Hello

2 = System.class
```

Generated

```java
System.out.println("Hello");
```

---

## Why use positional arguments?

Suppose the same value is used many times.

Instead of

```java
.addStatement("$T.out.println($S)",
System.class,
"Hello")
```

you can reuse arguments by their positions.

Useful for long statements.

---

# 3. Named Arguments

This is the easiest to read.

Instead of remembering

```
Argument 1

Argument 2

Argument 3
```

you give each argument a **name**.

Example

```java
Map<String,Object> map = new LinkedHashMap<>();

map.put("food","tacos");

map.put("count",3);
```

Now use

```java
.addNamed(
"I ate $count:L $food:L",
map)
```

JavaPoet checks

```
$count

↓

3
```

```
$food

↓

tacos
```

Generated

```java
I ate 3 tacos
```

---

## Another Example

```java
Map<String,Object> map = new LinkedHashMap<>();

map.put("name","Noman");

map.put("age",22);
```

Template

```java
"$name:S is $age:L years old"
```

Generated

```java
"Noman" is 22 years old
```

Notice

```
$name:S
```

means

```
Take value whose key is "name"

Treat it as String
```

---

## Which style should you use?

| Style      | Best For                        |
| ---------- | ------------------------------- |
| Relative   | Small statements (Most Common)  |
| Positional | Reordering or reusing arguments |
| Named      | Large, readable templates       |

Most JavaPoet code you'll write uses **Relative Arguments** because they're the simplest.

---

# Part 2: Methods

Now the documentation moves from placeholders to **MethodSpec**.

You already know this.

```java
MethodSpec greet =
MethodSpec.methodBuilder("greet")
```

creates

```java
void greet(){

}
```

---

## Normal Method

```java
MethodSpec.methodBuilder("show")
```

generates

```java
void show(){

}
```

---

# Abstract Method

Suppose you want

```java
abstract void show();
```

Notice

```
No body

No {}

Ends with ;
```

JavaPoet does this with

```java
.addModifiers(
Modifier.ABSTRACT)
```

Example

```java
MethodSpec flux =
MethodSpec.methodBuilder("flux")
.addModifiers(
Modifier.PROTECTED,
Modifier.ABSTRACT)
.build();
```

Generated

```java
protected abstract void flux();
```

Notice

No

```java
{

}
```

because abstract methods don't have implementations.

---

# Why?

Because Java doesn't allow

```java
abstract void show(){

}
```

This is illegal.

An abstract method only declares **what** should exist, not **how** it works.

---

# Abstract Class

If a class contains an abstract method,

the class must also be abstract.

So

```java
TypeSpec.classBuilder("HelloWorld")
.addModifiers(
Modifier.PUBLIC,
Modifier.ABSTRACT)
```

generates

```java
public abstract class HelloWorld
```

---

Combined output

```java
public abstract class HelloWorld {

    protected abstract void flux();

}
```

Exactly like the documentation.

---

# Why is Modifier used?

You often write

```java
Modifier.PUBLIC
```

```java
Modifier.STATIC
```

```java
Modifier.FINAL
```

```java
Modifier.ABSTRACT
```

These correspond directly to Java keywords.

| Java Keyword | JavaPoet             |
| ------------ | -------------------- |
| public       | `Modifier.PUBLIC`    |
| private      | `Modifier.PRIVATE`   |
| protected    | `Modifier.PROTECTED` |
| static       | `Modifier.STATIC`    |
| final        | `Modifier.FINAL`     |
| abstract     | `Modifier.ABSTRACT`  |

So when you write:

```java
.addModifiers(
Modifier.PUBLIC,
Modifier.STATIC)
```

JavaPoet generates:

```java
public static
```

---

# MethodSpec Can Configure Much More

The documentation ends by saying:

> Methods also have parameters, exceptions, varargs, Javadoc, annotations, type variables, and a return type.

This means `MethodSpec.Builder` can generate methods like:

```java
/**
 * Calculates the square.
 */
@Override
public final int square(int n) throws Exception {
    return n * n;
}
```

using builder methods such as:

* `.addParameter(...)`
* `.addException(...)`
* `.addJavadoc(...)`
* `.addAnnotation(...)`
* `.returns(...)`
* `.varargs(true)`

The GitHub guide covers these features in later sections, and you'll learn them one by one.

---

# 📘 Notes Summary

### Code Block Argument Styles

| Style      | Syntax           | Example                                    | Generated Output |
| ---------- | ---------------- | ------------------------------------------ | ---------------- |
| Relative   | `$L`, `$S`, `$T` | `.add("I ate $L $L", 3, "tacos")`          | `I ate 3 tacos`  |
| Positional | `$1L`, `$2L`     | `.add("I ate $2L $1L", "tacos", 3)`        | `I ate 3 tacos`  |
| Named      | `$name:L`        | `.addNamed("I ate $count:L $food:L", map)` | `I ate 3 tacos`  |

### MethodSpec Highlights

| JavaPoet             | Generated Java |
| -------------------- | -------------- |
| `Modifier.PUBLIC`    | `public`       |
| `Modifier.PRIVATE`   | `private`      |
| `Modifier.PROTECTED` | `protected`    |
| `Modifier.STATIC`    | `static`       |
| `Modifier.FINAL`     | `final`        |
| `Modifier.ABSTRACT`  | `abstract`     |

### Abstract Method Example

**JavaPoet**

```java
MethodSpec.methodBuilder("flux")
    .addModifiers(Modifier.PROTECTED, Modifier.ABSTRACT)
    .build();
```

**Generated Java**

```java
protected abstract void flux();
```
----
This is one of the **largest and most important sections** of the JavaPoet documentation. It teaches you how to generate almost every Java language feature.

Since you're preparing for a **team demo**, I'll explain each topic in this format:

1. **What is this concept in Java?**
2. **How do we write it normally?**
3. **How does JavaPoet generate it?**
4. **Simple real-world example**
5. **Important interview/demo points**
6. **Notes for GitHub**

---

# 1. Constructors

## First understand Constructors in Java

Suppose we have a Student class.

```java
public class Student {

    private String name;

    public Student(String name) {
        this.name = name;
    }

}
```

Question:

When does this constructor run?

Whenever we write

```java
Student s = new Student("Noman");
```

Java automatically calls

```java
Student(String name)
```

Constructors initialize objects.

---

## How do we create constructors in JavaPoet?

Instead of

```java
MethodSpec.methodBuilder("display")
```

we use

```java
MethodSpec.constructorBuilder()
```

Notice there is **no constructor name**.

Why?

Because Java already knows the constructor name is the class name.

---

### JavaPoet

```java
MethodSpec constructor =
        MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(String.class,"name")
                .addStatement("this.$N = $N","name","name")
                .build();
```

Generated

```java
public Student(String name){
    this.name=name;
}
```

---

## What does

```java
this.$N = $N
```

mean?

Suppose

```java
this.$N = $N
```

Arguments

```java
"name"

"name"
```

Generated

```java
this.name=name;
```

Very useful.

---

## Real-life Example

Imagine creating an Employee.

```java
Employee emp = new Employee("Noman");
```

Constructor stores

```java
this.name=name;
```

---

## Demo Point

> "JavaPoet doesn't create constructors using `methodBuilder()`. It provides `constructorBuilder()` specifically for constructors."

---

# 2. Parameters

A method usually needs input.

Example

```java
public void greet(String name)
```

Here

```java
String name
```

is a parameter.

---

## Normal Java

```java
void add(int a,int b)
```

---

## JavaPoet Easy Way

```java
.addParameter(int.class,"a")
.addParameter(int.class,"b")
```

Generated

```java
void add(int a,int b)
```

---

## Builder Way

Suppose parameter has

* annotation
* final modifier
* documentation

then use

```java
ParameterSpec.builder()
```

Example

```java
ParameterSpec.builder(
String.class,
"name")
.addModifiers(Modifier.FINAL)
.build();
```

Generated

```java
final String name
```

---

## Real-life Example

```java
public void login(final String username)
```

---

## When should we use ParameterSpec?

Simple parameter

↓

```java
.addParameter()
```

Complex parameter

↓

```java
ParameterSpec.builder()
```

---

# 3. Fields

What is a field?

Field = Variable inside a class.

Example

```java
class Student{

    private String name;

}
```

"name"

is called a field.

---

## JavaPoet

Simple

```java
.addField(String.class,
"name",
Modifier.PRIVATE)
```

Generated

```java
private String name;
```

---

Builder version

```java
FieldSpec.builder(
String.class,
"name")
```

---

When to use builder?

If field has

* annotation

* javadoc

* initializer

---

## Field Initializer

Suppose

```java
private int age=22;
```

JavaPoet

```java
.initializer("$L",22)
```

Generated

```java
private int age=22;
```

---

Another

```java
.initializer("$S","Noman")
```

Generated

```java
private String name="Noman";
```

---

## Real-life Example

```java
class Student{

private final double cgpa=8.61;

}
```

---

# 4. Interfaces

First remember Java.

Interface

```java
interface Animal{

void sound();

}
```

Notice

No body.

---

Methods inside interface are automatically

```java
public abstract
```

Fields are automatically

```java
public static final
```

---

JavaPoet

```java
TypeSpec.interfaceBuilder("Animal")
```

Generated

```java
public interface Animal
```

---

Method

```java
.addMethod(...)
```

Generated

```java
void sound();
```

Notice

No

```java
{

}
```

---

Field

```java
String TYPE="DOG";
```

---

### Interesting Point

Documentation says

You still write

```java
Modifier.PUBLIC

Modifier.ABSTRACT
```

inside JavaPoet

But generated code becomes

```java
void beep();
```

instead of

```java
public abstract void beep();
```

Why?

Because Java already assumes it.

Cleaner code.

---

## Real-life Example

```java
interface Payment{

void pay();

}
```

---

# 5. Enums

Suppose

Traffic Light

Only

RED

GREEN

YELLOW

Possible.

Perfect use of enum.

---

Java

```java
enum Day{

MONDAY,

TUESDAY

}
```

---

JavaPoet

```java
TypeSpec.enumBuilder("Day")
```

Values

```java
.addEnumConstant("MONDAY")
```

Generated

```java
public enum Day{

MONDAY

}
```

---

## Advanced Enum

Enum can also have

Fields

Constructors

Methods

Example

```java
enum Status{

SUCCESS("Green"),

FAILED("Red");

}
```

Documentation shows exactly that.

---

# Real-life Example

```java
enum PaymentStatus{

SUCCESS,

FAILED,

PENDING

}
```

---

# 6. Anonymous Inner Classes

This sounds scary.

Actually very easy.

Suppose

```java
Comparator<String> c=
new Comparator<String>(){

@Override

public int compare(...){

}

};
```

Notice

We created

a class

without giving it a name.

That's anonymous class.

---

Normally

```java
class MyComparator
```

has a name.

Anonymous

↓

No name.

---

JavaPoet

```java
TypeSpec.anonymousClassBuilder("")
```

Generated

```java
new Comparator<String>(){

}
```

---

Real-life Example

Sorting strings.

```java
Collections.sort(list,

new Comparator<String>(){

});
```

---

# 7. Annotations

Simple annotation

```java
@Override
```

JavaPoet

```java
.addAnnotation(
Override.class)
```

Generated

```java
@Override
```

Easy.

---

Suppose

```java
@Deprecated
```

JavaPoet

```java
.addAnnotation(
Deprecated.class)
```

---

## Complex Annotation

Suppose

```java
@JsonProperty("name")
```

has values.

Need

```java
AnnotationSpec.builder()
```

---

Example

```java
AnnotationSpec.builder(JsonProperty.class)

.addMember("value",

"$S",

"name")
```

Generated

```java
@JsonProperty(

value="name"

)
```

---

## Nested Annotation

Documentation shows

Annotation

inside

Annotation

Example

```java
@HeaderList(

@Header(...)

)
```

Very advanced.

JavaPoet supports it.

---

# Quick Revision Table

| Java Concept       | JavaPoet API                       | Generated Java                                 |
| ------------------ | ---------------------------------- | ---------------------------------------------- |
| Constructor        | `MethodSpec.constructorBuilder()`  | `public Student(){}`                           |
| Method Parameter   | `.addParameter()`                  | `void greet(String name)`                      |
| Complex Parameter  | `ParameterSpec.builder()`          | `final String name`                            |
| Field              | `.addField()`                      | `private String name;`                         |
| Complex Field      | `FieldSpec.builder()`              | Field with initializer, annotation, or Javadoc |
| Interface          | `TypeSpec.interfaceBuilder()`      | `interface Animal {}`                          |
| Enum               | `TypeSpec.enumBuilder()`           | `enum Day { MONDAY }`                          |
| Anonymous Class    | `TypeSpec.anonymousClassBuilder()` | `new Comparator<>() {}`                        |
| Simple Annotation  | `.addAnnotation()`                 | `@Override`                                    |
| Complex Annotation | `AnnotationSpec.builder()`         | `@Headers(...)`                                |

---

# How to explain this in your demo

You can summarize the progression like this:

> "JavaPoet provides builder classes that mirror Java language constructs. `MethodSpec` generates methods and constructors, `ParameterSpec` generates method parameters, `FieldSpec` generates class fields, `TypeSpec` generates classes, interfaces, enums, and anonymous inner classes, and `AnnotationSpec` generates annotations. Instead of writing Java source code manually, we describe its structure using these builders, and JavaPoet generates clean, compilable Java code with imports, formatting, and indentation handled automatically."

---

## My recommendation for your learning repository

So far, you've created separate examples for:

* ✅ Hello World
* ✅ Control Flow
* ✅ `$L`
* ✅ `$S`
* ✅ `$T`
* ✅ `$N`

Continue the same pattern for these topics. Create one focused generator for each:

```
ConstructorGenerator.java
ParameterGenerator.java
FieldGenerator.java
InterfaceGenerator.java
EnumGenerator.java
AnonymousClassGenerator.java
AnnotationGenerator.java
```

---

Absolutely! Below is a **README-ready explanation** that combines everything we've discussed. It starts from the basic problem, explains why JavaPoet exists, shows the difference between normal Java and JavaPoet, and ends with a simple real-world example. You can copy and paste this directly into your GitHub repository.

---

# 🤔 Why JavaPoet?

When I first started learning JavaPoet, the first question that came to my mind was:

> **"Why do we need JavaPoet when we can write everything using normal Java?"**

This is a completely valid question because JavaPoet itself does **not** add any new Java language features. Whatever JavaPoet generates can also be written manually in Java.

The real purpose of JavaPoet is different.

> **JavaPoet is not used to write Java programs. It is used to write Java programs that generate other Java programs.**

This concept is known as **code generation**.

---

# Normal Java vs JavaPoet

## Normal Java

In normal Java, we write code to solve a problem.

Example:

```java
public class Student {

    private String name;

    public void display() {
        System.out.println(name);
    }
}
```

When we run this program, it executes the code and produces an output.

```
Input
      ↓
Java Program
      ↓
Output
```

The goal is to solve a business problem.

---

## JavaPoet

With JavaPoet, we don't directly write the `Student` class.

Instead, we write another Java program that generates the `Student.java` file for us.

Example:

```java
TypeSpec student = TypeSpec.classBuilder("Student")
        .addField(String.class, "name")
        .build();

JavaFile.builder("com.example", student)
        .build()
        .writeTo(System.out);
```

When this JavaPoet program runs, it **does not execute** the Student class.

Instead, it generates the source code:

```java
package com.example;

public class Student {

    private String name;

}
```

So the output of a JavaPoet program is **another Java source file (.java)**.

```
JavaPoet Program
        ↓
Generates
        ↓
Student.java
        ↓
Compile & Run
        ↓
Output
```

This is the biggest difference between normal Java and JavaPoet.

---

# Think of Two Different Java Programs

One of the easiest ways to understand JavaPoet is to remember that there are **two Java programs**.

## Program 1 - Generator Program

This is the program that we write using JavaPoet.

Example:

```java
public class HelloWorldGenerator {

    public static void main(String[] args) {
        // JavaPoet code
    }

}
```

Its job is **not** to print "Hello World".

Its job is to **generate another Java file**.

---

## Program 2 - Generated Program

After running the generator, JavaPoet creates:

```java
public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello JavaPoet!");
    }

}
```

Now this generated file is compiled and executed like any normal Java program.

So the flow becomes:

```
HelloWorldGenerator.java
           │
           │ Run
           ▼
Creates
           │
           ▼
HelloWorld.java
           │
           │ Compile & Run
           ▼
Hello JavaPoet!
```

---

# Why Not Just Write the Java Class Manually?

If we only need **one class**, writing it manually is actually easier.

Example:

```java
public class Student {

}
```

No need for JavaPoet.

But imagine your manager says:

> Create **5,000 Java classes** with the same structure.

For example:

```
Student.java
Employee.java
Teacher.java
Doctor.java
Customer.java
Product.java
Department.java
Order.java
Invoice.java
...
5000 more classes
```

Writing these manually would take days or even weeks.

Instead, we write **one JavaPoet generator**, and it creates all 5,000 classes automatically.

---

# Real-World Example

Imagine a company database with 500 tables.

```
Student
Employee
Department
Product
Order
Customer
```

Each table needs a Java model class.

Without JavaPoet, developers must manually write:

```java
public class Student {
    private int id;
    private String name;
}
```

```java
public class Employee {
    private int id;
    private String name;
}
```

```java
public class Department {
    private int id;
    private String name;
}
```

...

500 times.

Instead, a JavaPoet program can:

1. Read the database schema.
2. Detect all tables and columns.
3. Automatically generate all corresponding Java classes.

One generator program can create hundreds or thousands of Java files in a few seconds.

---

# Why Not Generate Code Using Strings?

Without JavaPoet, we might generate Java code like this:

```java
String code =
    "public class Student {\n" +
    "    private String name;\n" +
    "}";
```

This becomes difficult to maintain because we must manually handle:

* quotation marks
* line breaks
* indentation
* semicolons
* braces
* imports
* formatting

JavaPoet removes all this complexity.

Instead, we simply describe the structure:

```java
TypeSpec.classBuilder("Student")
        .addField(String.class, "name")
        .build();
```

JavaPoet automatically generates properly formatted Java code with correct imports and indentation.

---

# Where is JavaPoet Used?

JavaPoet is useful whenever Java code needs to be generated automatically.

Common use cases include:

* Annotation Processors
* Database Entity Generation
* REST API Client Generation
* SDK Generation
* Compiler Plugins
* Code Generators
* Boilerplate Code Generation
* Internal Developer Tools

Many frameworks generate Java source code behind the scenes instead of requiring developers to write repetitive code manually.

---

# Simple Analogy

Imagine your manager asks you to prepare **10,000 offer letters**.

You have two options.

### Option 1

Write every offer letter manually.

Very slow.

### Option 2

Create a template:

```
Dear {Name},

Congratulations!
```

Then let a program replace `{Name}` for every employee.

The program automatically generates all 10,000 letters.

JavaPoet works exactly the same way.

Instead of generating documents,

it generates Java source code.

---

# Key Difference

| Normal Java                                   | JavaPoet                                                      |
| --------------------------------------------- | ------------------------------------------------------------- |
| Write Java code manually                      | Write Java code that generates Java code                      |
| Output is data (text, numbers, objects, etc.) | Output is a `.java` source file                               |
| Used for business logic                       | Used for automatic code generation                            |
| Best when writing a few classes               | Best when generating hundreds or thousands of similar classes |
| Developer writes every class                  | Generator creates classes automatically                       |
| Manual formatting and imports                 | JavaPoet automatically handles formatting and imports         |

---

# One-Line Summary

> **Normal Java is used to solve problems by writing Java code. JavaPoet is used to solve repetitive coding problems by automatically generating Java source code. Instead of manually writing hundreds or thousands of similar classes, we write one JavaPoet generator, and it creates all those Java files for us with proper formatting, imports, and syntax.**

---

## 💡 The Sentence I Always Remember

> **"In normal Java, we write code that solves a problem. In JavaPoet, we write code that writes more Java code."**

---

Yes! In fact, **this is probably the best demo you can give** because it shows the real purpose of JavaPoet instead of just generating a single `HelloWorld.java`.

---

# Demo Scenario: Generate Multiple Employee Classes from One Template

Imagine your manager says:

> "We have employees in different departments. Every department needs a Java class with the same structure."

Instead of writing:

```java
public class HREmployee {
    private String name;
    private int id;
}
```

```java
public class DeveloperEmployee {
    private String name;
    private int id;
}
```

```java
public class TesterEmployee {
    private String name;
    private int id;
}
```

```java
public class ManagerEmployee {
    private String name;
    private int id;
}
```

You write **one JavaPoet generator**.

---

# Generator Code (JavaPoet)

Create a file named **EmployeeGenerator.java**

```java
package org.springcore.demo;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Paths;

public class EmployeeGenerator {

    public static void main(String[] args) throws IOException {

        // Different class names
        String[] departments = {
                "HREmployee",
                "DeveloperEmployee",
                "TesterEmployee",
                "ManagerEmployee"
        };

        // Loop through every department
        for (String className : departments) {

            // Field: private String name;
            FieldSpec name =
                    FieldSpec.builder(String.class, "name")
                            .addModifiers(Modifier.PRIVATE)
                            .build();

            // Field: private int id;
            FieldSpec id =
                    FieldSpec.builder(int.class, "id")
                            .addModifiers(Modifier.PRIVATE)
                            .build();

            // Method: display()
            MethodSpec display =
                    MethodSpec.methodBuilder("display")
                            .addModifiers(Modifier.PUBLIC)
                            .returns(void.class)
                            .addStatement("$T.out.println($S + name)", System.class, "Employee : ")
                            .addStatement("$T.out.println($S + id)", System.class, "ID : ")
                            .build();

            // Build Class
            TypeSpec employee =
                    TypeSpec.classBuilder(className)
                            .addModifiers(Modifier.PUBLIC)
                            .addField(name)
                            .addField(id)
                            .addMethod(display)
                            .build();

            // Generate File
            JavaFile.builder("com.example.employee", employee)
                    .build()
                    .writeTo(Paths.get("generated"));
        }

        System.out.println("All Employee Classes Generated Successfully!");
    }
}
```

---

# What happens when you run this?

Only **one** Java file executes:

```
EmployeeGenerator.java
```

But JavaPoet generates **four** Java files automatically.

```
generated
│
└── com
    └── example
        └── employee
            │
            ├── HREmployee.java
            ├── DeveloperEmployee.java
            ├── TesterEmployee.java
            └── ManagerEmployee.java
```

---

# Generated File Example

`HREmployee.java`

```java
package com.example.employee;

public class HREmployee {

    private String name;

    private int id;

    public void display() {
        System.out.println("Employee : " + name);
        System.out.println("ID : " + id);
    }
}
```

---

`DeveloperEmployee.java`

```java
package com.example.employee;

public class DeveloperEmployee {

    private String name;

    private int id;

    public void display() {
        System.out.println("Employee : " + name);
        System.out.println("ID : " + id);
    }
}
```

Notice something?

The only thing that changed is the **class name**.

Everything else came from the **same template**.

---

# Visual Flow (Perfect for README)

```
                 EmployeeGenerator.java
                         │
                         │
                  String[] departments
                         │
     ┌───────────────────┼───────────────────┐
     │                   │                   │
     ▼                   ▼                   ▼
 HREmployee      DeveloperEmployee     TesterEmployee
     │                   │                   │
     └───────────────────┼───────────────────┘
                         │
                         ▼
                 JavaPoet Generator
                         │
                         ▼
         Generates Multiple Java Files
```

---

# How to Explain This in Your Demo

You can say:

> "Suppose my company has multiple departments like HR, Development, Testing, and Management. Every department needs a Java class with the same fields and methods. Instead of writing each class manually, I create one JavaPoet generator. The generator loops through the department names and creates a separate Java source file for each one. This saves time, reduces repetitive coding, and ensures all generated classes have the same structure."

---

# A More Realistic Industry Example

Companies often have database tables like:

```
Student
Employee
Department
Course
Teacher
Library
Subject
Fees
Attendance
```

A JavaPoet generator can read these table names and generate:

```
Student.java
Employee.java
Department.java
Course.java
Teacher.java
Library.java
Subject.java
Fees.java
Attendance.java
```

from the **same template**, changing only the class name and fields based on the metadata.

---

Yes! **This is exactly the kind of example where JavaPoet shines in the real world.** In fact, this is a much better demo than `HelloWorld` because this is what companies actually do.

Let's build a realistic scenario.

---

# 🏦 Scenario: World Bank

Suppose World Bank has branches in different cities.

The database contains these tables:

```text
Delhi_Branch
Mumbai_Branch
Bangalore_Branch
Chennai_Branch
Hyderabad_Branch
Pune_Branch
```

Every table has exactly the same structure.

| customer_id | customer_name | age | gender | city | phone | email | total_amount | withdrawn | balance |
| ----------- | ------------- | --- | ------ | ---- | ----- | ----- | ------------ | --------- | ------- |

Only the **table name changes**.

---

## Without JavaPoet

You manually create

```text
DelhiBranchDTO.java
MumbaiBranchDTO.java
BangaloreBranchDTO.java
ChennaiBranchDTO.java
HyderabadBranchDTO.java
PuneBranchDTO.java
```

Each file contains almost identical code.

Example

```java
public class DelhiBranchDTO {

    private int customerId;
    private String customerName;
    private int age;
    private String gender;
    private String city;
    private String phone;
    private String email;
    private double totalAmount;
    private double withdrawn;
    private double balance;

}
```

Now imagine **200 branches worldwide**.

You would write

```text
200 DTO Classes
```

manually.

Very repetitive.

---

# With JavaPoet

We create **one template**.

That template reads all table names and automatically generates

```text
DelhiBranchDTO.java
MumbaiBranchDTO.java
BangaloreBranchDTO.java
...
```

---

# Visual Flow

```text
Database

Delhi_Branch
Mumbai_Branch
Bangalore_Branch
Chennai_Branch
Hyderabad_Branch

          │
          │
          ▼

JavaPoet Generator

          │

One DTO Template

          │

Loop through every table

          │
          ▼

DelhiBranchDTO.java

MumbaiBranchDTO.java

BangaloreBranchDTO.java

ChennaiBranchDTO.java

HyderabadBranchDTO.java
```

---

# JavaPoet Generator

```java
package org.springcore.dto;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Paths;

public class BankDtoGenerator {

    public static void main(String[] args) throws IOException {

        // Imagine these names came from the database
        String[] tables = {
                "DelhiBranch",
                "MumbaiBranch",
                "BangaloreBranch",
                "ChennaiBranch",
                "HyderabadBranch"
        };

        for (String table : tables) {

            TypeSpec dto = createDTO(table + "DTO");

            JavaFile.builder("com.worldbank.dto", dto)
                    .build()
                    .writeTo(Paths.get("generated"));
        }

        System.out.println("All DTOs Generated Successfully!");
    }

    private static TypeSpec createDTO(String className) {

        return TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)

                .addField(int.class, "customerId", Modifier.PRIVATE)
                .addField(String.class, "customerName", Modifier.PRIVATE)
                .addField(int.class, "age", Modifier.PRIVATE)
                .addField(String.class, "gender", Modifier.PRIVATE)
                .addField(String.class, "city", Modifier.PRIVATE)
                .addField(String.class, "phone", Modifier.PRIVATE)
                .addField(String.class, "email", Modifier.PRIVATE)
                .addField(double.class, "totalAmount", Modifier.PRIVATE)
                .addField(double.class, "withdrawn", Modifier.PRIVATE)
                .addField(double.class, "balance", Modifier.PRIVATE)

                .build();
    }
}
```

---

# What gets generated?

## DelhiBranchDTO.java

```java
package com.worldbank.dto;

public class DelhiBranchDTO {

    private int customerId;
    private String customerName;
    private int age;
    private String gender;
    private String city;
    private String phone;
    private String email;
    private double totalAmount;
    private double withdrawn;
    private double balance;

}
```

---

## MumbaiBranchDTO.java

```java
package com.worldbank.dto;

public class MumbaiBranchDTO {

    private int customerId;
    private String customerName;
    private int age;
    private String gender;
    private String city;
    private String phone;
    private String email;
    private double totalAmount;
    private double withdrawn;
    private double balance;

}
```

---

## BangaloreBranchDTO.java

```java
package com.worldbank.dto;

public class BangaloreBranchDTO {

    private int customerId;
    private String customerName;
    private int age;
    private String gender;
    private String city;
    private String phone;
    private String email;
    private double totalAmount;
    private double withdrawn;
    private double balance;

}
```

Notice that **the only thing changing is the class name**. The template is reused for every branch.

---

# But This Is Still Hardcoded...

Exactly! And this is where **real projects** go one step further.

Instead of writing:

```java
String[] tables = {
    "DelhiBranch",
    "MumbaiBranch",
    "BangaloreBranch"
};
```

a real application connects to the database.

For example:

```text
Database

↓

Read all table names

↓

Read all columns of each table

↓

JavaPoet generates DTOs automatically

↓

Developer gets ready-to-use Java classes
```

So your generator doesn't know in advance whether there are 5 tables or 500 tables—it discovers them from the database metadata.

---

# Real Company Flow

Imagine this database:

```text
Database
│
├── Delhi_Branch
├── Mumbai_Branch
├── Chennai_Branch
├── Bangalore_Branch
├── London_Branch
├── Dubai_Branch
├── Paris_Branch
├── Tokyo_Branch
└── NewYork_Branch
```

Your generator does this:

```text
Read Database Metadata

↓

For every table

↓

Generate DTO

↓

Save into

src/main/java/com/worldbank/dto
```

After running once, your project automatically contains:

```text
DelhiBranchDTO.java
MumbaiBranchDTO.java
ChennaiBranchDTO.java
BangaloreBranchDTO.java
LondonBranchDTO.java
DubaiBranchDTO.java
ParisBranchDTO.java
TokyoBranchDTO.java
NewYorkBranchDTO.java
```

without writing any of those classes manually.

---

# How to Explain This in Your Demo

You can say:

> "Suppose a bank has hundreds of branch tables with the same schema but different table names. Writing a DTO for every table would be repetitive and error-prone. With JavaPoet, I write one DTO template and loop through the table names. JavaPoet generates a separate DTO class for each table automatically. In a real application, the table names and columns would come from the database metadata, allowing hundreds of DTOs to be generated in seconds."

---

## 💡 Even More Realistic (Recommended for Your Demo)

After you've understood this version, the next step would be to make it **fully dynamic**:

* Connect to a real MySQL/PostgreSQL database using JDBC.
* Read all table names from the database metadata (`DatabaseMetaData`).
* Read the columns and their data types for each table.
* Map SQL types (`VARCHAR`, `INT`, `DECIMAL`, etc.) to Java types (`String`, `int`, `double`, etc.).
* Use JavaPoet to generate DTO classes with the correct fields automatically.

That demonstrates JavaPoet in a way that's very close to how code generators work in real enterprise applications.


