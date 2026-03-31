package com.rays.common;

/**
 * Utility class for storing and retrieving the {@link UserContext}
 * on a per-thread basis using a {@link ThreadLocal} variable.
 * <p>
 * This holder ensures that each request thread in the ORS application
 * has its own isolated {@link UserContext}, preventing data leakage
 * between concurrent requests. It is typically populated by a filter
 * or interceptor at the beginning of each request and cleared at the end.
 * </p>
 *
 * @author Krati Trivedi
 */
public class UserContextHolder {

    /**
     * Thread-local storage for the {@link UserContext}, ensuring each
     * thread maintains its own independent context instance.
     */
    private static final ThreadLocal<UserContext> threadLocal = new ThreadLocal<>();

    /**
     * Stores the given {@link UserContext} in the current thread's local storage.
     *
     * @param context the {@link UserContext} to associate with the current thread
     */
    public static void setContext(UserContext context) {
        threadLocal.set(context);
    }

    /**
     * Retrieves the {@link UserContext} associated with the current thread.
     *
     * @return the current thread's {@link UserContext},
     *         or {@code null} if no context has been set
     */
    public static UserContext getContext() {
        return threadLocal.get();
    }

    /**
     * Removes the {@link UserContext} from the current thread's local storage.
     * <p>
     * This method should always be called at the end of a request lifecycle
     * to prevent memory leaks in thread-pooled environments such as application servers.
     * </p>
     */
    public static void clear() {
        threadLocal.remove();
    }
}