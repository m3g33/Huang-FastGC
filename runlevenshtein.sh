#!/bin/bash

mkdir -p results

java -cp dist/FasterGC.jar:extlibs/jargs.jar:extlibs/commons-io-1.4.jar Test.TestEDServer -a -g 8 -n 100 1>results/levenshteinserverout 2>results/levenshteinservererr &

sleep 0.8

java -cp dist/FasterGC.jar:extlibs/commons-io-1.4.jar:extlibs/jargs.jar Test.TestEDClient --server localhost -r 1 -a -g 8 -n 100 1>results/levenshteinclientout 2>results/levenshteinclienterr & 
