package com.puzzless

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory

import scala.reflect.{classTag, ClassTag}

/**
 * Custom ObjectMapper for Jackson with functions that do not throw and return Option or Either instead.
 */
class OptionObjectMapper extends ObjectMapper {

  private val log = LoggerFactory.getLogger("OptionObjectMapper")

  /**
   * Parses JSON string in a form of ByteArray.
   * @param data JSON data
   * @param cls class to parse to
   * @tparam T class to parse to
   * @return T instance or None
   */
  def readValueOption[T](data: Array[Byte], cls: Class[T]): Option[T] = {
    try {
      Option(readValue[T](data, cls))
    } catch {
      case e: Throwable ⇒ None
    }
  }

  /**
   * Parses JSON string.
   * @param data JSON string
   * @param cls class to parse to
   * @tparam T class to parse to
   * @return T instance or None
   */
  def readValueOption[T](data: String, cls: Class[T]): Option[T] = {
    try {
      Option(readValue[T](data, cls))
    } catch {
      case e: Throwable ⇒
        log.error("Error parsing JSON", e)
        None
    }
  }

  /**
   * Simplified readValueOption which does not require cls parameter.
   * It is probably slower than the other one as it depends on runtime reflection.
   * @param data JSON string
   * @tparam T class to parse to
   * @return T instance or None
   */
  def readValueOption[T: ClassTag](data: String): Option[T] = {
    try {
      Option(readValue[T](data, classTag[T].runtimeClass.asInstanceOf[Class[T]]))
    } catch {
      case e: Throwable ⇒ None
    }
  }

  /**
   * Parses JSON string and returns either instance of class or Throwable error produced.
   * @param data JSON string
   * @tparam T class to parse to
   * @return Either T instance or Throwable
   */
  def readValueEither[T: ClassTag](data: String): Either[T, Throwable] = {
    try {
      Left(readValue[T](data, classTag[T].runtimeClass.asInstanceOf[Class[T]]))
    } catch {
      case e: Throwable ⇒ Right(e)
    }
  }

}