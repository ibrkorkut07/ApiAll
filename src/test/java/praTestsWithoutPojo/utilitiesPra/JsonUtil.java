package praTestsWithoutPojo.utilitiesPra;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    private  static ObjectMapper mapper;
    static{
        mapper = new ObjectMapper();
    }
    // Method 1: De-serialization, used to convert Json data to Java object
    public static <T> T convertJsonToJava(String json, Class<T> cls){
        T javaResult= null;
        try {
            javaResult = mapper.readValue(json, cls);
        } catch (IOException e) {
            System.err.println("Json data could not be converted to Java object" + e.getMessage());
        }
        return javaResult;
    }

    // Method 2: Serialization, used to convert Java object to Json data
    public static String convertJavaToJson(Object obj){
        String jsonResult= null;
        try {
            jsonResult = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            System.err.println("Java object could not be converted to Json data" + e.getMessage());
        }
        return jsonResult;
    }

}