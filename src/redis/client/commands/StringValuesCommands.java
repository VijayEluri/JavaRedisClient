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

package redis.client.commands;

import redis.client.RedisClientException;

public interface StringValuesCommands {

	/**
	 * EXISTS somekey
	 * 
	 * @return 0, if non-existent key, else return 1
	 */
	int exists(String key) throws RedisClientException;

	/**
	 * set a key to a string value
	 * 
	 * @return true if success, exception on failure
	 */
	boolean set(String key, String value) throws RedisClientException;

	/**
	 * return the string value of the key
	 * 
	 * @return length of the value, and the string value of the key, if key
	 *         exists, else return nil
	 */
	String get(String key) throws RedisClientException;
	
	/**
	 * return the string value of the key
	 * 
	 * @return A list of values, with the length of the value, and the string value of the key, if key
	 *         exists, else return nil
	 */
	Object mget(String keys) throws RedisClientException;

	/**
	 * set a key to a string value if the key does not exist
	 * 
	 * @return 1 if the key was set, 0 if key not set
	 */
	int setNX(String key, String value) throws RedisClientException;

	/**
	 * increment the integer value of key
	 * 
	 * @return incremented value. If the key does not exist, then the key is
	 *         set, and incremented value is returned
	 */
	int incr(String key) throws RedisClientException;

	/**
	 * increment the integer value of key by integer
	 * 
	 * @return incremented value. If the key does not exist, then the key is
	 *         set, and incremented value is returned
	 */
	int incrBy(String key, int incrementValue) throws RedisClientException;

	/**
	 * decrement the integer value of key
	 * 
	 * @return decremented value. If the key does not exist, then the key is
	 *         set, and decremented value is returned
	 */
	int decr(String key) throws RedisClientException;

	/**
	 * decrement the integer value of key by integer
	 * 
	 * @return decremented value. If the key does not exist, then the key is
	 *         set, and decremented value is returned
	 */
	int decrBy(String key, int decrementValue) throws RedisClientException;

	/**
	 * delete a key
	 * 
	 * @return 1 if the key was removed, 0 if the key does not exist
	 */
	int del(String key) throws RedisClientException;

	/**
	 * return the type of the value stored at key
	 * 
	 * @return "none" if the key does not exist; "string" if the key contains a
	 *         String value; "list" if the key contains a List value; "set" if
	 *         the key contains a Set value
	 */
	String type(String key) throws RedisClientException;
}