package controllers;

import actions.VerifyAccessToken;
import com.fasterxml.jackson.databind.JsonNode;
import exceptions.NotFoundException;
import graphql.ExecutionResult;
import graphqlconfig.GraphQLConfiguration;
import helpers.AuthenticationHelper;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

import javax.inject.Inject;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private final GraphQLConfiguration graphQLConfiguration;
    private final AuthenticationHelper authenticationHelper;

    @Inject
    public HomeController(GraphQLConfiguration graphQLConfiguration, AuthenticationHelper authenticationHelper) {
        this.graphQLConfiguration = graphQLConfiguration;
        this.authenticationHelper = authenticationHelper;
    }


    public Result index() {
        return ok(index.render());
    }



    @VerifyAccessToken
    public Result graphql() {
        // Convert POST body to JSON
        JsonNode graphQLQueryJson = request().body().asJson();

        // Get the value for key query and convert to String
        String graphQLQuery = graphQLQueryJson.get("query").asText();
        // Execute query
        ExecutionResult queryResult = graphQLConfiguration.getGraphQL().execute(graphQLQuery);
        return ok(Json.toJson(queryResult));
    }

    public Result getToken() {
        return ok(Json.toJson(authenticationHelper.generateAccessToken("2")));
    }

    @VerifyAccessToken
    public Result testToken() {
        return ok("Access token works");
    }
}
