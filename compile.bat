@echo off

rmdir classes /S /Q
mkdir classes

javac -d classes -sourcepath src src/com/lolwoot/ezjargs/Main.java

pause
