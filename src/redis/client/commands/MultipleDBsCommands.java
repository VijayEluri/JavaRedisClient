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

public interface MultipleDBsCommands {
	/**
	 * Select the DB having the specified index
	 * 
	 * @return true on success, exception on failure
	 */
	boolean select(int index) throws RedisClientException;

	/**
	 * Move the key from the currently selected DB to the DB having as index
	 * dbIndex
	 * 
	 * @return 1 if the key was moved; 0 if the key was not moved because
	 *         already present on the target DB or was not found in the current
	 *         DB; -3 if the destination DB is the same as the source DB; -4 if
	 *         the database index if out of range
	 */
	int move(String key, int dbIndex) throws RedisClientException;

	/**
	 * Remove all the keys of the currently selected DB
	 * 
	 * @return +OK on success, -ERR on failure
	 */
	String flushDB() throws RedisClientException;

	/**
	 * Remove all the keys from all the databases
	 * 
	 * @return +OK on success, -ERR on failure
	 */
	String flushAll() throws RedisClientException;
}
