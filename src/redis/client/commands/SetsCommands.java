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

public interface SetsCommands {
	/**
	 * Add the specified member to the Set value at key
	 * 
	 * @return 1 if the new element was added; 0 if the new element was already
	 *         a member of the set; -2 if the key contains a non set value.
	 */
	int sAdd(String key, String member) throws RedisClientException;

	/**
	 * Remove the specified member from the Set value at key
	 * 
	 * @return 1 if the new element was removed; 0 if the new element was not a
	 *         member of the set; -2 if the key does not hold a set value.
	 */
	int sRem(String key, String member) throws RedisClientException;

	/**
	 * Return the number of elements (the cardinality) of the Set at key
	 * 
	 * @return the cardinality (number of elements) of the set as an integer
	 *         `>=` 0 if the operation succeeded -2 if the specified key does
	 *         not hold a set value
	 */
	int sCard(String key) throws RedisClientException;

	/**
	 * Test if the specified value is a member of the Set at key
	 * 
	 * @return 1 if the element is a member of the set; 0 if the element is not
	 *         a member of the set OR if the key does not exist; -2 if the key
	 *         does not hold a set value.
	 */
	int sIsMember(String key, String member) throws RedisClientException;

	/**
	 * Return the intersection between the Sets stored at key1, key2, ..., keyN
	 * 
	 * @return Set of values, if successful. return NIL if there is an error.
	 */
	Object sInter(String keys) throws RedisClientException;

	/**
	 * Compute the intersection between the Sets stored at key1, key2, ...,
	 * keyN, and store the resulting Set at keyForComputedSet
	 * @ return true if successful, exception otherwise
	 */
	boolean sInterStore(String keyForComputedSet, String keys)
			throws RedisClientException;

	/**
	 * Return all the members of the Set value at key
	 * 
	 * @return Set of values, if successful. return NIL if there is an error.
	 */
	Object sMembers(String key) throws RedisClientException;

}
