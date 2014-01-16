/*
 * LoadableResourceException.java
 *
 * Created on January 29, 2001, 9:58 AM
 */
package net.sf.navigator.util;


/**
 *
 * @author  ssayles
 * @version
 */
public class LoadableResourceException extends Exception {
    //~ Constructors ===========================================================

    /**
     * Creates new <code>LoadableResourceException</code> without detail message.
     */
    public LoadableResourceException() {}

    /**
     * Constructs an <code>LoadableResourceException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public LoadableResourceException(String msg) {
        super(msg);
    }
}
