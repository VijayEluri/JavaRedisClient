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

public interface ListsCommands {

	/**
	 * Append an element to the tail of the List value at key
	 * 
	 * @return true if success, exception if failure
	 */
	boolean rPush(String key, String value) throws RedisClientException ;

	/**
	 * Insert an element to the head of the List value at key
	 * 
	 * @return true if success, exception if failure
	 */
	boolean lPush(String key, String value) throws RedisClientException;

	/**
	 * Return the length of the List value at key
	 * 
	 * @return The length of the list as an integer `>=` 0 if the operation
	 *         succeeded; -2 if the specified key does not hold a list value
	 */
	int lLen(String key) throws RedisClientException;

	/**
	 * Return a range of elements from the List at key
	 * 
	 * @return List of selected elements, if successful, else an empty list.
	 */
	Object lRange(String key, int start, int end) throws RedisClientException;

	/**
	 * Trim the list at key to the specified range of elements
	 * 
	 * @return true if success, exception if failure
	 */
	boolean lTrim(String key) throws RedisClientException;

	/**
	 * Return the element at index position from the List at key
	 * 
	 * @return element if available at index, empty string if index out of
	 *         bounds, NIL if there is an error
	 */
	String lIndex(String key, int index) throws RedisClientException;

	/**
	 * Set a new value as the element at index position of the List at key
	 * 
	 * @return true if success, exception if failure
	 */
	boolean lSet(String key, int index, String value) throws RedisClientException;

	/**
	 * Remove the first-N, last-N, or all the elements matching value from the
	 * List at key
	 * 
	 * @return The number of removed elements if the operation succeeded; -1 if
	 *         the specified key does not exist; -2 if the specified key does
	 *         not hold a list value.
	 */
	int lRem(String key, int count, String value) throws RedisClientException;

	/**
	 * Return and remove (atomically) the first element of the List at key
	 * 
	 * @return Element if successful, NIL if failure
	 */
	String lPop(String key) throws RedisClientException;

	/**
	 * Return and remove (atomically) the last element of the List at key
	 * 
	 * @return Element if successful, NIL if failure
	 */
	String rPop(String key) throws RedisClientException;
}
