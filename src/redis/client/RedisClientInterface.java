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

import redis.client.commands.KeySpaceCommands;
import redis.client.commands.ListsCommands;
import redis.client.commands.MultipleDBsCommands;
import redis.client.commands.PersistenceCtrlCommands;
import redis.client.commands.SetsCommands;
import redis.client.commands.SortingCommands;
import redis.client.commands.StringValuesCommands;

public interface RedisClientInterface extends StringValuesCommands,
		KeySpaceCommands, ListsCommands, MultipleDBsCommands,
		PersistenceCtrlCommands, SetsCommands, SortingCommands {

	String NIL = "NIL";

	/**
	 * PING
	 * 
	 * @return +PONG
	 */
	String ping() throws RedisClientException;

	/**
	 * QUIT close the connection
	 */
	void quit();

	/**
	 * return the version of the server.
	 * 
	 * @return Return the server version as a float
	 */
	String info() throws RedisClientException;
}
