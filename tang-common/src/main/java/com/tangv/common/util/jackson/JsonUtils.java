package com.tangv.common.util.jackson;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * JSON Tools
 *
 * @author tangwei
 * @time 2022/4/24 7:08 PM
 */
@Log4j2
public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonReadFeature.ALLOW_MISSING_VALUES.mappedFeature(), true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> String toJSONString(T entity) {
        String json = null;
        try {
            json = mapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            throw new JacksonException(StrUtil.format("jackson to string error, data: {}", entity), e);
        }
        return json;
    }

    public static <T> T parseObject(String json, Class<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new JacksonException(StrUtil.format("jackson parse error, json: {}, type: {}", json, type), e);
        }
    }

    public static <T> List<T> parseArray(String json, Class<T> T) {
        CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, T);
        try {
            return mapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new JacksonException(StrUtil.format("jackson array parse error, json: {}, type: {}", json, type), e);
        }
    }

    public static ObjectNode createObjectNode() {
        return mapper.createObjectNode();
    }

    public static JsonNode parseJsonNode(String jsonText) {
        try {
            return mapper.readTree(jsonText);
        } catch (JsonProcessingException e) {
            throw new JacksonException(StrUtil.format("jackson format json error, json: {}", jsonText), e);
        }
    }
}