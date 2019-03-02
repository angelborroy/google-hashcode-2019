
Google Hashcode 2019
=============================

UST Global Official Team solution for [Google Hashcode 2019](https://hashcode.withgoogle.com/).

**Strategy**

1. Compile slides from Vertical Photos finding tags clouds without common strings or with the minimal common strings
2. Take first slide and find the first one returning the max value for Google formula
3. Continue iterating till no more slides left

*Note that a more refined strategy can be designed to obtain higher scores*

**Scoring**

```
A - Example                    2
B - Lovely landscapes    202,569
C - Memorable moments      1,472
D - Pet pictures         401,434
E - Shiny selfies        406,415
----------------------------------
Total score 			     1,011,892
```

Compiling
---------

```bash
$ mvn clean package
```

Running
-------

```bash
$ java -jar target/hashcode-2019-1.0.0.jar \
  --fileIn=in/a_example.in \
  --fileOut=a_example.out
```

Generating ZIP Source file
--------------------------

```bash
$ ./zip.sh

$ ls *.zip
src.zip
```
