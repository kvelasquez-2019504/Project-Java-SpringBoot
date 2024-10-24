package org.springproject.library.exception;

import static org.springproject.library.utils.Constants.TOKEN_EXPIRED_MALFORMED_ERROR_MESSAGE;

public class TokenExpiredException extends ServerErrorException {

    public TokenExpiredException() {
        super(TOKEN_EXPIRED_MALFORMED_ERROR_MESSAGE);
    }

}