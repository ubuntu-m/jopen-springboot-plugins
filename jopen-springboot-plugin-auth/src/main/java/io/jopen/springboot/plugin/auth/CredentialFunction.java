package io.jopen.springboot.plugin.auth;

import org.checkerframework.checker.nullness.qual.NonNull;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Function;

/**
 * @author maxuefeng
 * @see java.util.function.Function
 * @see Credential
 * @since 2020/2/4
 */
@FunctionalInterface
public interface CredentialFunction extends Function<HttpServletRequest, Credential> {

    /**
     * @param request {@link HttpServletRequest}
     * @return {@link Credential#getValid()}
     */
    @NonNull
    Credential apply(@NonNull HttpServletRequest request);

    class EmptyCredentialFunction implements CredentialFunction {
        @Override
        public @NonNull Credential apply(@NonNull HttpServletRequest request) {
            return Credential.INVALID_CREDENTIAL;
        }
    }

    /**
     * 自定义异常  开发者可以覆盖当前Exception
     *
     * @overtide
     */
    default RuntimeException ifErrorThrowing() {
        return new AuthException("access deny! because you has not access this api interface grant!");
    }
}
