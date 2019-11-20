@echo off

call compile.bat

javac -d classes -sourcepath src examples/com/lolwoot/ezjargs/Main2.java

echo Run Main2 with parameters: -value "Hello world!!!" C:\dev\file.txt C:\dev\file1.txt C:\dev\file2.txt
java -cp classes com.lolwoot.ezjargs.Main2 -value "Hello world!!!" C:\dev\file.txt C:\dev\file1.txt C:\dev\file2.txt
echo ------------------------------------------------------------------------

pause
