package errors;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonError extends Error {

    private JsonNode jsonError;

    public JsonError(Integer httpCode, String type, String message, JsonNode jsonError) {
        super(httpCode, type, message);
        this.jsonError = jsonError;
    }

    public JsonNode getJsonError() {
        return jsonError;
    }

    public void setJsonError(JsonNode jsonError) {
        this.jsonError = jsonError;
    }
}
