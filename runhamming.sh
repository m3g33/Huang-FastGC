#!/bin/bash

mkdir -p results

java -cp dist/FasterGC.jar:extlibs/jargs.jar:extlibs/commons-io-1.4.jar Test.TestHammingServer -n 900 1>results/hammingserverout 2>results/hammingservererr &

sleep 0.8

java -cp dist/FasterGC.jar:extlibs/commons-io-1.4.jar:extlibs/jargs.jar Test.TestHammingClient --server localhost  -n 900 1>results/hammingclientout 2>results/hammingclienterr & 