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

public interface SortingCommands {
	/**
	 * Sort a Set or a List accordingly to the specified parameters.
	 * 
	 * SORT _key_ BY _pattern_ LIMIT _start_ _end_ GET _pattern_ ASC|DESC ALPHA
	 * 
	 * @return Sorted String list, if successful. Else returns NIL
	 */
	Object sort(String key, String query) throws RedisClientException;
}
