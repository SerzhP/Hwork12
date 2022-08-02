import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Scanner;



public class CountriesApi {
    private static Scanner scanner;

    public static void main(String[] args)throws IOException,JSONException {
        System.out.println("Please enter country name");
        scanner = new Scanner(System.in);
        String userChoose = scanner.next();
        getData(userChoose);

    }

    private static void  getData(String userChoose)throws IOException{
        OkHttpClient user = new OkHttpClient();
        Request userRequest = new Request.Builder()
                .url("https://restcountries.com/v3.1/name/" + userChoose).build();
        Response response = user.newCall(userRequest).execute();
        String jsonData = response.body().string();
        if(response.code() == 404){
            System.out.println("Choose another country");
            chooseAgain();
        }
        JSONArray jsonArray = new JSONArray(jsonData);
        JSONObject jsonObject = (JSONObject)jsonArray.get(0);
        String region = jsonObject.getString("region");
        JSONArray borders = jsonObject.getJSONArray("borders");
//        String currency = jsonObject.getString("currency");
//        JSONArray currency = jsonObject.getJSONArray("currency");
        System.out.println(region + " " + borders + " " +currency);
        chooseAgain();
    }

    private static void chooseAgain()throws IOException{
        System.out.println("Please enter country name or exit");
        String userChoose = scanner.next();
        if (!userChoose.equals("exit")){
            getData(userChoose);
        }
        else {
            System.exit(0);
        }
    }

}
