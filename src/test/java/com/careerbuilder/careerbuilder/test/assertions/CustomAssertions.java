package com.careerbuilder.careerbuilder.test.assertions;

import com.careerbuilder.careerbuilder.global.common.error.ErrorCode;
import com.careerbuilder.careerbuilder.global.common.error.ErrorCodeIfs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomAssertions {

    static ObjectMapper objectMapper = new ObjectMapper();

    public static void assertMvcErrorEquals(MvcResult result, ErrorCodeIfs errorCodeIfs) throws UnsupportedEncodingException, JsonProcessingException {
        final String response = result.getResponse().getContentAsString();
        final JsonNode responseBody = objectMapper.readTree(response);

        final JsonNode successField = responseBody.get("success");
        final JsonNode errorField = responseBody.get("error");
        final JsonNode dataField = responseBody.get("data");

        assertThat(successField).isNotNull();
        assertThat(successField.asBoolean()).isEqualTo(false);
        assertThat(errorField).isNotNull();
        assertThat(dataField).isNull();

        assertThat(errorField.get("code").asText()).isEqualTo(errorCodeIfs.getErrorCode());
        assertThat(errorField.get("message").asText()).isEqualTo(errorCodeIfs.getErrorMessage());
    }

    public static void assertMvcDataEquals(MvcResult result, Consumer<JsonNode> consumer) throws UnsupportedEncodingException, JsonProcessingException {
        final String response = result.getResponse().getContentAsString();
        final JsonNode responseBody = objectMapper.readTree(response);

        final JsonNode successField = responseBody.get("success");
        final JsonNode errorField = responseBody.get("error");
        final JsonNode dataField = responseBody.get("data");

        assertThat(successField).isNotNull();
        assertThat(successField.asBoolean()).isEqualTo(true);
        assertThat(errorField).isNull();
        assertThat(dataField).isNotNull();

        consumer.accept(dataField);
    }

    public static void assertMvcSuccessEquals(MvcResult result, boolean isSuccess) throws UnsupportedEncodingException, JsonProcessingException {
        final String response = result.getResponse().getContentAsString();
        final JsonNode responseBody = objectMapper.readTree(response);

        final JsonNode successField = responseBody.get("success");
        final JsonNode errorField = responseBody.get("error");
        final JsonNode dataField = responseBody.get("data");

        assertThat(successField).isNotNull();
        assertThat(successField.asBoolean()).isEqualTo(true);
        assertThat(errorField).isNull();
        assertThat(dataField).isNull();

        assertThat(successField.asBoolean()).isEqualTo(isSuccess);
    }

}
