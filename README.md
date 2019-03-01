
Google Hashcode 2019
=============================

UST Global Official Team solution for [Google Hashcode 2019](https://hashcode.withgoogle.com/).

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