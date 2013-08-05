/*******************************************************************************
 * Redis Java Bindings - http://subbaraman.wordpress.com/2009/04/03/redis-java-client/
 *
 * Copyright 2009 Kiran Subbaraman
 * Released under the same license as Redis.
 *
 * Version: 0.1
 *
 * $Revision: 1 $
 * $Date: 2009-04-03 22:59:40 +0530 (Fri, 03 April 2009) $
 *
 ******************************************************************************/

package redis.client.test;

public interface RedisClientBenchmarkInterface {
	int SET = 0;
	int GET = 1;
	int SETNX = 2;
	int PING = 3;
	int INCR = 4;
	int LPUSH = 5;
	int LPOP = 6;
}