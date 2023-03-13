// Copyright (C) 2010 by Yan Huang <yhuang@virginia.edu>

package Test;

import java.util.*;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import jargs.gnu.CmdLineParser;

import Utils.*;
import Program.*;
import org.apache.commons.lang3.SystemUtils;

class TestAESEncryptClient {

    static boolean autogen;
    static short[] msg = {0x32, 0x43, 0xf6, 0xa8, 
			  0x88, 0x5a, 0x30, 0x8d, 
			  0x31, 0x31, 0x98, 0xa2, 
			  0xe0, 0x37, 0x07, 0x34};

    static SecureRandom rnd = new SecureRandom();

    private static void printUsage() {
	System.out.println("Usage: java TestAESEncryptClient [{-a, --autogen}] [{-s, --server} servername]");
    }

    private static void process_cmdline_args(String[] args) {
	CmdLineParser parser = new CmdLineParser();
	CmdLineParser.Option optionServerIPname = parser.addStringOption('s', "server");
	CmdLineParser.Option optionAuto = parser.addBooleanOption('a', "autogen");
	CmdLineParser.Option optionIterCount = parser.addIntegerOption('r', "iteration");

	try {
	    parser.parse(args);
	}
	catch (CmdLineParser.OptionException e) {
	    System.err.println(e.getMessage());
	    printUsage();
	    System.exit(2);
	}

	autogen = (Boolean) parser.getOptionValue(optionAuto, false);
	ProgClient.serverIPname = (String) parser.getOptionValue(optionServerIPname, new String("localhost"));
	Program.iterCount = (Integer) parser.getOptionValue(optionIterCount, 1);
    }

    static void generateData() throws Exception {
	msg = new short[16];
	for (int i = 0; i < 16; i++) {
	    msg[i] = (short) rnd.nextInt(0xff);
	}
    }

    public static void main(String[] args) throws Exception {
	StopWatch.pointTimeStamp("Starting program");
	process_cmdline_args(args);

	if (autogen)
	    generateData();

	AESEncryptClient aesEncryptClient = new AESEncryptClient(msg, 4);
	aesEncryptClient.run();
	System.out.println("Total time elapsed: " + StopWatch.allTime / 1000d);
	if(SystemUtils.IS_OS_WINDOWS)
		TimeUnit.SECONDS.sleep(10);
    }
}