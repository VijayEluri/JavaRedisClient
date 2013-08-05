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

import java.io.IOException;
import java.lang.reflect.Field;

import redis.client.RedisClient;
import redis.client.RedisClientException;

public class RedisClientBenchmark implements RedisClientBenchmarkInterface {

	public static void main(String[] args) throws IOException,
			RedisClientException {
		RedisClient redisClient = new RedisClient();
		redisClient.connect();
		int loop = 10000;
		benchmark(redisClient, loop, SET);
		benchmark(redisClient, loop, GET);
		benchmark(redisClient, loop, SETNX);
		benchmark(redisClient, loop, PING);
		benchmark(redisClient, loop, INCR);
		benchmark(redisClient, loop, LPUSH);
		benchmark(redisClient, loop, LPOP);
		redisClient.disconnect();
	}

	private static void benchmark(RedisClient redisClient, int loop,
			int redisAction) throws RedisClientException {
		Field[] fields = RedisClientBenchmarkInterface.class
				.getDeclaredFields();
		long timeStart = System.nanoTime();
		String setValue = "transactionId, requesterID, service, operation, addtime";
		for (int i = 0; i < loop; i++) {
			switch (redisAction) {
			case GET:
				redisClient.get("key" + i);
				break;
			case SET:
				redisClient.set("key" + i, setValue);
				break;
			case SETNX:
				redisClient.setNX("key" + i, setValue);
				break;
			case PING:
				redisClient.ping();
				break;
			case INCR:
				redisClient.incr("incr");
				break;
			case LPUSH:
				redisClient.lPush("list", setValue);
				break;
			case LPOP:
				redisClient.lPop("list");
				break;
			default:
				break;
			}
		}
		long timeTaken = (System.nanoTime() - timeStart);
		double elementsPerSec = (loop * 1000000000.0) / timeTaken;
		
		System.out.println(fields[redisAction].getName() + " rates: "
				+ elementsPerSec + " requests per second.");
	}

}
