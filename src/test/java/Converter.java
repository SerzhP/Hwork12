import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class Converter {
    public static void main(String[] args)throws IOException {

        System.out.println("Welcome to currency converter");
        System.out.println("Please enter an amount to convert");
        Scanner scanner = new Scanner(System.in);
//        OkHttpClient user = new OkHttpClient();
        OkHttpClient user = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("https://api.apilayer.com/exchangerates_data/convert?to=ils&from=usd&amount=7")
                .addHeader("apikey", "48uigO1ltrCdxDagcOIHCl1ypLLEpPs1")
//                .method("GET" )
            .build();


        Response response = user.newCall(request).execute();
        String jsonData = response.body().string();
        response.close();
        JSONObject jsonObj = new JSONObject(jsonData);
        JSONObject ratesJson = jsonObj.getJSONObject("info");
        System.out.println("your result " + (scanner.nextDouble()*ratesJson.getDouble("rate")));
        System.out.println("Thank you");

    }
}
