package actions;

import exceptions.AccessTokenException;
import helpers.AuthenticationHelper;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class VerifyAccessTokenAction extends Action<VerifyAccessToken> {

    private final AuthenticationHelper authenticationHelper;

    @Inject
    public VerifyAccessTokenAction(AuthenticationHelper authenticationHelper) {
        this.authenticationHelper = authenticationHelper;
    }

    @Override
    public CompletionStage<Result> call(Http.Context ctx) {
        Http.Request request = ctx.request();
        Http.Headers requestHeaders = request.getHeaders();

        String accessToken = requestHeaders.get("Authorization")
                .orElse("");

        if(accessToken.isEmpty()) {
            throw new AccessTokenException("Access Token not found.");
        }

        this.authenticationHelper.decodeAccessToken(accessToken);
        return delegate.call(ctx);
    }
}
