##################
# Smith-Waterman #
##################

@echo off

mkdir results

start "" java -cp dist/FasterGC.jar\;extlibs/jargs.jar\;extlibs/commons-io-1.4.jar Test.TestSWServer -m matrices/blosum20x20 -a -n 30

powershell -nop -c "& {sleep -m 800}"

start "" java -cp dist/FasterGC.jar\;extlibs/commons-io-1.4.jar\;extlibs/jargs.jar Test.TestSWClient  -m matrices/blosum20x20 --server localhost -a -n 30