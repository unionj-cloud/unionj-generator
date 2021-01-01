package cloud.unionj.generator.mock.schemafaker;

/**
 * @author created by wubin
 * @version v0.1
 *   cloud.unionj.generator.mock.schemafaker
 * @date 2020/11/20
 */
public class SchemasNullException extends Exception {
  /**
   * Constructs a new runtime exception with the specified detail message.
   * The cause is not initialized, and may subsequently be initialized by a
   * call to {@link #initCause}.
   *
   * @param message the detail message. The detail message is saved for
   *                later retrieval by the {@link #getMessage()} method.
   */
  public SchemasNullException(String message) {
    super(message);
  }
}
