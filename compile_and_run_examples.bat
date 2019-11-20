@echo off

call compile.bat

javac -d classes -sourcepath src examples/com/lolwoot/ezjargs/Main.java examples/com/lolwoot/ezjargs/Main1.java

echo Run Main:
java -cp classes com.lolwoot.ezjargs.Main
echo ------------------------------------------------------------------------

echo Run Main1:
java -cp classes com.lolwoot.ezjargs.Main1
echo ------------------------------------------------------------------------

pause
