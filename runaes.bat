@echo off

start "" java -cp dist/FasterGC.jar\;extlibs/jargs.jar\;extlibs/commons-io-1.4.jar Test.TestAESEncryptServer 1>results/aesserverout 2>results/aesservererr

powershell -nop -c "& {sleep -m 800}"

start "" java -cp dist/FasterGC.jar\;extlibs/commons-io-1.4.jar\;extlibs/jargs.jar Test.TestAESEncryptClient --server localhost -r 1 1>results/aesclientout 2>results/aesclienterr