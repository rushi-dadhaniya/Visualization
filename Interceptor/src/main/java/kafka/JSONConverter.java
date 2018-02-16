package kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by rushi on 02/05/17.
 */
public class JSONConverter {

    private static Gson gson = null;

    static {
        gson = new GsonBuilder().disableHtmlEscaping().create();
    }

    /**
     * Converts given object into json string.
     *
     * @param object
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> String toJSON(T object, Class<T> classOfT) {
        String jsonString = null;
        if (object != null) {
            jsonString = gson.toJson(object, classOfT);
        }
        return jsonString;
    }

    /**
     * Converts json string to object of given class type.
     * @param jsonString
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T toObject(String jsonString, Class<T> classOfT) {
        if(jsonString != null) {
           return gson.fromJson(jsonString, classOfT);
        }
        return null;
    }

}