package io.vertx.codegen.annotations;

import io.vertx.codegen.format.Case;
import io.vertx.codegen.format.LowerCamelCase;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Declares a data object, such object must provide at least:
 *
 * <ul>
 *   <li>a constructor with {@link io.vertx.core.json.JsonObject}</li>
 * </ul>
 * <p/>
 *
 * When the data object is processed, a model of properties from the setter methods or adder methods. Single valued
 * and multi valued properties are supported.<p/>
 *
 * A setter is just like a JavaBean setter, however the method return type shall be fluent and return the
 * current object. A {@link java.util.List} setter type declares a multi valued property, otherwise it declares
 * a single valued property.<p/>
 *
 * An adder is a method prefixed by {@code add}, its purpose is to declare a {@link java.util.List} of properties.
 * An adder declares a multi valued property, mapped to the {@link io.vertx.core.json.JsonArray} type. An adder
 * and a multi valued setter of the same property name are naturally compatible and is encouraged.<p/>
 *
 * Properties types can be:<p/>
 *
 * <ul>
 *   <li>any basic type</li>
 *   <li>a valid data object type</li>
 *   <li>{@link io.vertx.core.json.JsonObject}</li>
 *   <li>{@link io.vertx.core.json.JsonArray}</li>
 *   <li>an enum type</li>
 *   <li>a {@link java.util.List} of above</li>
 * </ul>
 * <p/>
 *
 * A data object can be an interface or an abstract class to accomodate the api design. Data object can extend
 * other data objects.<p/>
 *
 * Sometimes data object can have a {@code toJson()} method that takes no arguments and returns a {@code JsonObject} representing
 * the data object as a {@code JsonObject}.<p/>
 *
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DataObject {

  /**
   * @return true if converter should be generated for the data object
   * @deprecated instead declare a {@link io.vertx.codegen.json.annotations.JsonGen} annotation
   */
  @Deprecated
  boolean generateConverter() default false;

  /**
   * @return true if the converter should handle the state of the ancestors.
   * @deprecated instead declare a {@link io.vertx.codegen.json.annotations.JsonGen#inheritConverter()}
   */
  @Deprecated
  boolean inheritConverter() default false;

  /**
   * @return whether the generated converter should be public or package private
   * @deprecated instead declare a {@link io.vertx.codegen.json.annotations.JsonGen#publicConverter()}
   */
  @Deprecated
  boolean publicConverter() default true;

  /**
   * @return the case class specifying how the data object converter properties will be translated to JSON element names.
   * @deprecated instead declare a {@link io.vertx.codegen.json.annotations.JsonGen#jsonPropertyNameFormatter()}
   */
  @Deprecated
  Class<? extends Case> jsonPropertyNameFormatter() default LowerCamelCase.class;

  /**
   * Returns the expected Base64 (RFC 4648) type to be used. When omitted, his is
   * vert.x default alphabet, usually (RFC 4648 Table 2) also known as {@code base64url}, unless the
   * system property {@code vertx.json.base64} is {@code legacy}. In this case the alphabet will
   * be {@code basic} as it was during the vert.x 3.x releases.
   *
   * Allowed values are:
   *
   * <ul>
   *   <li>{@code "base64url"} - Base64 URL and Filename Safe as defined in (RFC 4648 Table 2) </li>
   *   <li>{@code "basic"} - Base64 Basic as defined in (RFC 4648 Table 1) </li>
   * </ul>
   *
   * @return if generated converters are enabled, buffers should default to the configured type.
   * @deprecated instead declare a {@link io.vertx.codegen.json.annotations.JsonGen#base64Type()}
   */
  @Deprecated
  String base64Type() default "";
}
