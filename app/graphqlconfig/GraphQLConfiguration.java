package graphqlconfig;

import com.coxautodev.graphql.tools.SchemaParser;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GraphQLConfiguration {

    private GraphQL graphQL;
    private final RootQueryResolver rootQueryResolver;

    @Inject
    public GraphQLConfiguration(RootQueryResolver rootQueryResolver) {
        this.rootQueryResolver = rootQueryResolver;
        this.graphQL = initializeGraphQL();
    }

    private GraphQL initializeGraphQL() {
        GraphQLSchema graphQLSchema = SchemaParser.newParser()
                .file("graphql/schema.graphqls")
                .resolvers(
                        rootQueryResolver
                )
                .build()
                .makeExecutableSchema();

        return GraphQL.newGraphQL(graphQLSchema).build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
