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

class TestEDServer {
    static BigInteger dna;
    static boolean autogen;
    static int n;
    
    static SecureRandom rnd = new SecureRandom();

    private static void printUsage() {
	System.out.println("Usage: java TestEDServer [{-d, --dna} dna] [{-L, --EDBitLength} L] [{-a, --autogen}] [{-n, --DNALength} length]");
    }

    private static void process_cmdline_args(String[] args) {
	CmdLineParser parser = new CmdLineParser();
	CmdLineParser.Option optionAuto = parser.addBooleanOption('a', "autogen");
	CmdLineParser.Option optionDNALength = parser.addIntegerOption('n', "DNALength");
	CmdLineParser.Option optionSigma = parser.addIntegerOption('g', "sigma");
	
	try {
	    parser.parse(args);
	}
	catch (CmdLineParser.OptionException e) {
	    System.err.println(e.getMessage());
	    printUsage();
	    System.exit(2);
	}

	// dna = (String) parser.getOptionValue(optionDNA, new String("AGCT"));
	// EditDistanceServer.L = ((Integer) parser.getOptionValue(optionL, 5)).intValue();
	autogen = (Boolean) parser.getOptionValue(optionAuto, false);
	n = (Integer) parser.getOptionValue(optionDNALength, 100);
	EditDistanceCommon.sigma = (Integer) parser.getOptionValue(optionSigma, 2);
    }

    private static void generateData() throws Exception {
	dna = new BigInteger(EditDistanceCommon.sigma*n, rnd);
	// dna = EditDistanceServer.getDNAString(r, n);
    }

    public static void main(String[] args) throws Exception {

	StopWatch.pointTimeStamp("Starting program");
	process_cmdline_args(args);

	if (autogen)
	    generateData();

	EditDistanceServer edserver = new EditDistanceServer(dna, n);
	edserver.run();
	System.out.println("Total time elapsed: " + StopWatch.allTime / 1000d);

	if(SystemUtils.IS_OS_WINDOWS)
		TimeUnit.SECONDS.sleep(10);
    }
}