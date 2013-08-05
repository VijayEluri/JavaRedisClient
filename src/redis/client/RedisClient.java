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

package redis.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class RedisClient implements RedisClientInterface {

	private String hostname = "localhost";
	private int port = 6379;
	private Socket redisServer;
	private PrintWriter request = null;
	private BufferedReader response = null;

	public RedisClient(String hostname, String port) {
		if (hostname != null)
			this.hostname = hostname;
		if (port != null)
			this.port = Integer.parseInt(port);

	}

	public RedisClient() {
		super();
	}

	public void connect() {
		try {
			if (this.redisServer == null) {
				this.redisServer = new Socket(this.hostname, this.port);
				this.request = new PrintWriter(redisServer.getOutputStream(),
						true);
				this.response = new BufferedReader(new InputStreamReader(
						redisServer.getInputStream()));
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void disconnect() {
		try {
			this.request.close();
			this.response.close();
			this.redisServer.close();
			this.redisServer = null;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public List<String> keys(String pattern) throws RedisClientException {
		this.request.println("KEYS " + pattern + "\r\n");
		return parseSingleLineList();
	}

	public String randomKey() throws RedisClientException {
		this.request.println("RANDOMKEY\r\n");
		return singleLineReply();
	}

	public boolean rename(String oldKey, String newKey)
			throws RedisClientException {
		this.request.println("RENAME " + oldKey + " " + newKey + "\r\n");
		return statusCodeReply();
	}

	public int renameNX(String oldKey, String newKey)
			throws RedisClientException {
		this.request.println("RENAMENX " + oldKey + " " + newKey + "\r\n");
		return integerReply();
	}

	public int decr(String key) throws RedisClientException {
		return decrBy(key, 1);
	}

	public int del(String key) throws RedisClientException {
		this.request.println("DEL " + key + "\r\n");
		return integerReply();
	}

	public int exists(String key) throws RedisClientException {
		String userInput = "EXISTS " + key + "\r\n";
		request.println(userInput);
		return integerReply();
	}

	public String get(String key) throws RedisClientException {
		String userInput = "GET " + key + "\r\n";
		request.println(userInput);
		return bulkReply();
	}

	public Object mget(String keys) throws RedisClientException {
		String userInput = "GET " + keys + "\r\n";
		request.println(userInput);
		return multiBulkReply();
	}

	public int incr(String key) throws RedisClientException {
		return incrBy(key, 1);
	}

	public boolean set(String key, String value) throws RedisClientException {
		int valueLen = value.length();
		String valueString = " " + valueLen + "\r\n" + value + "\r\n";
		String userInput = "SET " + key + valueString;
		this.request.println(userInput);
		return statusCodeReply();
	}

	public int setNX(String key, String value) throws RedisClientException {
		int valueLen = value.length();
		String valueString = " " + valueLen + "\r\n" + value + "\r\n";
		String userInput = "SETNX " + key + valueString;
		this.request.println(userInput);
		return integerReply();
	}

	public String type(String key) throws RedisClientException {
		this.request.println("PING\r\n");
		return singleLineReply();
	}

	public String ping() throws RedisClientException {
		this.request.println("PING\r\n");
		return singleLineReply();
	}

	public void quit() {
		this.request.println("QUIT\r\n");
	}

	public String info() throws RedisClientException {
		this.request.println("INFO\r\n");
		return bulkReply();
	}

	public boolean bgSave() throws RedisClientException {
		this.request.println("BGSAVE\r\n");
		return statusCodeReply();
	}

	public String lastSave() throws RedisClientException {
		this.request.println("LASTSAVE\r\n");
		return singleLineReply();
	}

	public boolean save() throws RedisClientException {
		this.request.println("SAVE\r\n");
		return statusCodeReply();
	}

	public void shutDown() {
		this.request.println("SHUTDOWN\r\n");
	}

	public Object sort(String key, String query) throws RedisClientException {
		this.request.println("SORT " + key + " " + query + "\r\n");
		return multiBulkReply();
	}

	public int sAdd(String key, String member) throws RedisClientException {
		this.request.println("SADD " + key + " " + member.length() + "\r\n"
				+ member + "\r\n");
		return integerReply();
	}

	public int sCard(String key) throws RedisClientException {
		this.request.println("SCARD " + key + "\r\n");
		return integerReply();
	}

	public Object sInter(String keys) throws RedisClientException {
		this.request.println("SINTER " + keys + "\r\n");
		return multiBulkReply();
	}

	public int sIsMember(String key, String member) throws RedisClientException {
		this.request.println("SISMEMBER " + key + " " + member + "\r\n");
		return integerReply();
	}

	public int sRem(String key, String member) throws RedisClientException {
		this.request.println("SREM " + key + " " + member + "\r\n");
		return integerReply();
	}

	public boolean sInterStore(String keyForComputedSet, String keys)
			throws RedisClientException {
		this.request.println("SINTERSTORE " + keys + "\r\n");
		return statusCodeReply();
	}

	public Object sMembers(String key) throws RedisClientException {
		this.request.println("SMEMBERS " + key + "\r\n");
		return multiBulkReply();
	}

	public String flushAll() throws RedisClientException {
		this.request.println("FLUSHALL\r\n");
		return singleLineReply();
	}

	public String flushDB() throws RedisClientException {
		this.request.println("FLUSHDB\r\n");
		return singleLineReply();
	}

	public int move(String key, int dbIndex) throws RedisClientException {
		this.request.println("MOVE " + key + " " + dbIndex + "\r\n");
		return integerReply();
	}

	public boolean select(int dbIndex) throws RedisClientException {
		this.request.println("SELECT " + dbIndex + "\r\n");
		return statusCodeReply();
	}

	public String lIndex(String key, int index) throws RedisClientException {
		this.request.println("LINDEX " + key + " " + index + "\r\n");
		return bulkReply();
	}

	public int lLen(String key) throws RedisClientException {
		this.request.println("LLEN " + key + "\r\n");
		return integerReply();
	}

	public String lPop(String key) throws RedisClientException {
		this.request.println("LPOP " + key + "\r\n");
		return bulkReply();
	}

	public boolean lPush(String key, String value) throws RedisClientException {
		this.request.println("LPUSH " + key + " " + value.length() + "\r\n"
				+ value + "\r\n");
		return statusCodeReply();
	}

	public Object lRange(String key, int start, int end)
			throws RedisClientException {
		this.request
				.println("LRANGE " + key + " " + start + " " + end + "\r\n");
		return multiBulkReply();
	}

	public int lRem(String key, int count, String value)
			throws RedisClientException {
		this.request
				.println("LREM " + key + " " + count + " " + value + "\r\n");
		return integerReply();
	}

	public boolean lSet(String key, int index, String value)
			throws RedisClientException {
		this.request.println("LSET " + key + " " + index + " " + value.length()
				+ "\r\n" + value + "\r\n");
		return statusCodeReply();
	}

	public boolean lTrim(String key) throws RedisClientException {
		this.request.println("LTRIM " + key + "\r\n");
		return statusCodeReply();
	}

	public String rPop(String key) throws RedisClientException {
		this.request.println("RPOP " + key + "\r\n");
		return bulkReply();
	}

	public boolean rPush(String key, String value) throws RedisClientException {
		this.request.println("RPUSH " + key + " " + value.length() + "\r\n"
				+ value + "\r\n");
		return statusCodeReply();
	}

	public int decrBy(String key, int decrementValue)
			throws RedisClientException {
		this.request.println("DECRBY " + key + " " + decrementValue + "\r\n");
		return integerReply();
	}

	public int incrBy(String key, int incrementValue)
			throws RedisClientException {
		this.request.println("INCRBY " + key + " " + incrementValue + "\r\n");
		return integerReply();
	}

	private int integerReply() throws RedisClientException {
		int output = 0;
		try {
			String returnedValue = this.response.readLine().trim();
			checkError(returnedValue);
			output = Integer.parseInt(returnedValue);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return output;
	}

	private boolean statusCodeReply() throws RedisClientException {
		boolean output = false;
		try {
			String returnedValue = this.response.readLine().trim();
			checkError(returnedValue);
			output = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}

	private String bulkReply() throws RedisClientException {
		String output = NIL;
		try {
			String returnedValue = this.response.readLine().trim();
			checkError(returnedValue);
			if (!NIL.equalsIgnoreCase(returnedValue)) {
				output = this.response.readLine().trim();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}

	/**
	 * {number of total elements \n, { element size in bytes \n actual element
	 * \n}* }
	 * 
	 * @return List<strings>, NIL, or an exception
	 * @throws RedisClientException
	 */
	private Object multiBulkReply() throws RedisClientException {
		Object output = NIL;
		try {
			String returnedValue = this.response.readLine().trim();
			checkError(returnedValue);
			if (!NIL.equalsIgnoreCase(returnedValue)) {
				Vector<String> listValue = new Vector<String>(Integer
						.parseInt(returnedValue));
				while (this.response.readLine().trim().length() != 0) {
					listValue.add(this.response.readLine().trim());
				}
				output = listValue;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}

	private String singleLineReply() throws RedisClientException {
		String output = NIL;
		try {
			String returnedValue = this.response.readLine().trim();
			checkError(returnedValue);
			if (!NIL.equalsIgnoreCase(returnedValue)) {
				output = returnedValue;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}

	/**
	 * {output length in bytes\n, element 1 element 2... element n \n }
	 * 
	 * @return List of strings
	 * @throws RedisClientException
	 */
	private List<String> parseSingleLineList() throws RedisClientException {
		Vector<String> output = new Vector<String>();
		try {
			String returnedValue = this.response.readLine().trim();
			checkError(returnedValue);
			if (!NIL.equalsIgnoreCase(returnedValue)) {
				StringTokenizer tokenList = new StringTokenizer(this.response
						.readLine().trim(), " ");
				while (tokenList.hasMoreTokens()) {
					output.add(tokenList.nextToken());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}

	private void checkError(String returnedValue) throws RedisClientException {
		if (returnedValue.startsWith("-"))
			throw new RedisClientException(returnedValue);
	}

}
