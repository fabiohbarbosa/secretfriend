package br.com.fabiohgbarbosa.secretfriend.web.rest.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * JSON serializable and deserializable
 * Created by fabio on 10/09/15.
 */
public class JsonParse {
    public static <T> List<T> fromJsonToList(String json, Class<T> typeClass) {
        Type listType = new TypeToken<ArrayList<T>>() {}.getType();
        return new Gson().fromJson(json, listType);
    }

    public static <T> T fromJson(String json, Class<T> typeClass) {
        return new Gson().fromJson(json, typeClass);
    }

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }
}
