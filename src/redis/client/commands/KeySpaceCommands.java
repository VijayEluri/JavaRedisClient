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

import java.util.List;

import redis.client.RedisClientException;

public interface KeySpaceCommands {
	/**
	 * return all the keys matching a given pattern
	 * 
	 * @param patterns
	 *            can contain *, ? or \ to escape
	 * 
	 * @return List of keys if the patterns matches, empty list if there are
	 *         no matches
	 */
	List<String> keys(String pattern) throws RedisClientException;

	/**
	 * return a random key from the key space
	 * 
	 * @return the randomly selected key or an empty string if the database is
	 *         empty.
	 */
	String randomKey() throws RedisClientException;

	/**
	 * rename the old key with the new one, destroying the newname key if it
	 * already exists
	 * 
	 * @return true if success, Exception if it fails
	 */
	boolean rename(String oldKey, String newKey) throws RedisClientException;

	/**
	 * rename the old key with the new one, if the newname key does not already
	 * exist
	 * 
	 * @return 1 if the key was renamed; 0 if the target key already exist; -1
	 *         if the source key does not exist; -3 if source and destination
	 *         keys are the same.
	 */
	int renameNX(String oldKey, String newKey) throws RedisClientException;
}
