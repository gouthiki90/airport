package wheather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import wheather.WheatherDto.Response.Body.Items.Item;

public class DownloadWheather {
    public static List<Item> getWheatherList(String baseDate) {
        Map<String, String> wheatherMap = new HashMap<>();
        // String fcstValue = wheatherMap.get(baseDate);
        try {
            URL url = new URL(
                    "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst?serviceKey=eCazEq%2FCP4iBdrQDcQQrLr3rgUrV%2ByZOFRdwxGTrcfeZbe3FqDvkQ6iMAWgeXRDOa%2FABNLYI3Dhz7hzxyUuI4A%3D%3D&pageNo=1&numOfRows=1000&dataType=Json&base_date="
                            + baseDate + "&base_time=0630" + "&nx=92&ny=69");

            HttpURLConnection stream = (HttpURLConnection) url.openConnection();
            // stream 연결
            BufferedReader br = new BufferedReader(new InputStreamReader(stream.getInputStream(), "utf-8"));
            // 캐스팅
            String responseJson = br.readLine();
            // 데이터 문자열로 읽기
            Gson gson = new Gson();
            WheatherDto dto = gson.fromJson(responseJson, WheatherDto.class);

            // key = 발표 날짜, vaule = 예보값
            // 날짜 입력하면 예보값 나오도록
            List<Item> result = dto.getResponse().getBody().getItems().getItem();
            for (int i = 0; i < result.size(); i++) {
                wheatherMap.put(result.get(i).getBaseDate(), result.get(i).getFcstValue());
            }
            return result;
        } catch (Exception e) {
            System.out.println("날씨 정보를 불러오지 못 했습니다.");
        }
        return null;
    }
    // 지역 이름, 날짜 입력하면 시간대별로 리턴

}
