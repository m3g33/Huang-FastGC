// Copyright (C) 2010 by Yan Huang <yhuang@virginia.edu>

package Test;

import java.util.*;
import java.math.*;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import jargs.gnu.CmdLineParser;

import Utils.*;
import Program.*;
import org.apache.commons.lang3.SystemUtils;

class TestHammingServer {
    static BigInteger bits;
    static int n;
    
    static SecureRandom rnd = new SecureRandom();

    private static void printUsage() {
	System.out.println("Usage: java TestHammingServer [{-n, --bit-length} length]");
    }

    private static void process_cmdline_args(String[] args) {
	CmdLineParser parser = new CmdLineParser();
	CmdLineParser.Option optionBitLength = parser.addIntegerOption('n', "bit-length");

	try {
	    parser.parse(args);
	}
	catch (CmdLineParser.OptionException e) {
	    System.err.println(e.getMessage());
	    printUsage();
	    System.exit(2);
	}

	n = (Integer) parser.getOptionValue(optionBitLength, 100);
    }

    private static void generateData() throws Exception {
	bits = new BigInteger(n, rnd);
    }

    public static void main(String[] args) throws Exception {

	StopWatch.pointTimeStamp("Starting program");
	process_cmdline_args(args);

	generateData();
	    
	HammingDistanceServer hammingserver = new HammingDistanceServer(bits, n);
	hammingserver.run();
	System.out.println("Total time elapsed: " + StopWatch.allTime / 1000d);
	if(SystemUtils.IS_OS_WINDOWS)
		TimeUnit.SECONDS.sleep(10);
    }
}