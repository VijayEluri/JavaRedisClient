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

public interface PersistenceCtrlCommands {
	/**
	 * Synchronously save the DB on disk
	 * 
	 * @return +OK if successful, else -ERR
	 */
	boolean save() throws RedisClientException;

	/**
	 * Asynchronously save the DB on disk
	 * 
	 * @return true if successful, else -ERR
	 */
	boolean bgSave() throws RedisClientException;

	/**
	 * Return the UNIX time stamp of the last successfully saving of the dataset
	 * on disk
	 * 
	 * @return UNIX timestamp of last save, as a String
	 */
	String lastSave() throws RedisClientException;

	/**
	 * Synchronously save the DB on disk, then shutdown the server
	 * 
	 * @return -ERR on error. No details on success, since server quits
	 */
	void shutDown();
}
