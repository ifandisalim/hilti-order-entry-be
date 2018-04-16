package errors;

import exceptions.AccessTokenException;
import exceptions.AuthenticationException;
import exceptions.DatabaseException;
import exceptions.NotFoundException;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import javax.persistence.Access;

public class ErrorFactory {

    public static Result create(Error error) {
        return Results.status(error.getHttpCode(), Json.toJson(error));
    }


    public static Result create(Throwable exception) {

        if(exception instanceof DatabaseException) {
            return create(new Error(Http.Status.INTERNAL_SERVER_ERROR, "database_exception", exception.getMessage()));
        }

        if(exception instanceof NotFoundException) {
            return create(new Error(Http.Status.BAD_REQUEST, "not_found_exception", exception.getMessage()));
        }

        if(exception instanceof AccessTokenException) {
            return create(new Error(Http.Status.BAD_REQUEST, "access_token_exception", exception.getMessage()));
        }

        if(exception instanceof AuthenticationException) {
            return create(new Error(Http.Status.BAD_REQUEST, "authentication_exception", exception.getMessage()));
        }

        return create(new Error(Http.Status.INTERNAL_SERVER_ERROR, "unexpected_exception", exception.getMessage()));
    }

}
