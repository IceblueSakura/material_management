package com.example.material.exception

/**
 * CustomException is an open class that represents a custom exception in the system.
 *
 * Used for custom API exception handler, can use the class itself and subclasses.
 *
 * @property errorCode The error code associated with the exception.Maybe in the form of http code.
 * @param message The detail message.
 *
 * @constructor Creates a new CustomException with the specified error code and message.
 */
open class CustomException(val errorCode: Int, message: String) : Exception(message) {
}