@echo off

mkdir results

start "" java -cp dist/FasterGC.jar\;extlibs/jargs.jar\;extlibs/commons-io-1.4.jar\;commons-lang3-3.12.0.jar Test.TestEDServer -a -g 8 -n 100

powershell -nop -c "& {sleep -m 800}"

start "" java -cp dist/FasterGC.jar\;extlibs/commons-io-1.4.jar\;extlibs/jargs.jar\;commons-lang3-3.12.0.jar Test.TestEDClient --server localhost -r 1 -a -g 8 -n 100