@echo off

start "" java -cp dist/FasterGC.jar\;extlibs/jargs.jar\;extlibs/commons-io-1.4.jar Test.TestHammingServer -n 900 1>results/hammingserverout 2>results/hammingservererr

powershell -nop -c "& {sleep -m 800}"

start "" java -cp dist/FasterGC.jar\;extlibs/commons-io-1.4.jar\;extlibs/jargs.jar Test.TestHammingClient --server localhost  -n 900 1>results/hammingclientout 2>results/hammingclienterr