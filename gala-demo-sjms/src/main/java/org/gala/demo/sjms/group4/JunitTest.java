package org.gala.demo.sjms.group4;

import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author yuan.li
 *
 */
public class JunitTest {

	private static Reactor reactor;

	private static Acceptor acceptor;

	@BeforeClass
	public static void before() throws IOException {
		reactor = new Reactor(8888);
		acceptor = new Acceptor(reactor);
	}

	@AfterClass
	public static void after() {
	}

	@Test
	public void test() {
		reactor.run();
		acceptor.run();
	}
}
