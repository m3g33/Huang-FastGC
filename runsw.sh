#!/bin/bash

##################
# Smith-Waterman #
##################

mkdir -p results

java -cp dist/FasterGC.jar:extlibs/jargs.jar:extlibs/commons-io-1.4.jar Test.TestSWServer -m matrices/blosum20x20 -a -n 30 1>results/swserverout 2>results/swservererr &

sleep 0.8

java -cp dist/FasterGC.jar:extlibs/commons-io-1.4.jar:extlibs/jargs.jar Test.TestSWClient  -m matrices/blosum20x20 --server localhost -a -n 30 1>results/swclientout 2>results/swclienterr & 