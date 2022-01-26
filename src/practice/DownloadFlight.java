package practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import practice.FlightDto.Response.Body.Items.Item;

public class DownloadFlight {
    // 특정 날짜와 특정 출발지에서 특정 목적지로 도착하는 항공편 정보 가져오는 메서드
    // 출발날짜, 출발 공항 이름, 도착 공항 이름 매개변수 메서드
    public static List<Item> getFlightList(String depPlanTime, String depAirportNm, String arrAirportNm) {
        Map<String, String> airpoMap = DownloadAirport.getAirportList();
        String depAirportId = airpoMap.get(depAirportNm); // 메서드 매개변수에 공항이름을 넣어야 된다는 강제성 부여
        String arrAirportId = airpoMap.get(arrAirportNm);

        try {
            URL url = new URL(
                    "http://openapi.tago.go.kr/openapi/service/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=wJmmW29e3AEUjwLioQR22CpmqS645ep4S8TSlqtSbEsxvnkZFoNe7YG1weEWQHYZ229eNLidnI2Yt5EZ3Stv7g%3D%3D&numOfRows=10&pageNo=1&depAirportId="
                            + depAirportId + "&arrAirportId=" + arrAirportId + "&depPlandTime="
                            + depPlanTime + "&_type=json");
            // 쿼리스트링에 출발, 도착 공항 아이디와 출발 시간 사용자화

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Stream 연결

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            // 캐스팅

            String responseJson = br.readLine();
            // 캐스팅된 데이터 읽기
            Gson gson = new Gson();
            FlightDto dto = gson.fromJson(responseJson, FlightDto.class);
            // FlightDto Json으로 변환
            List<Item> result = dto.getResponse().getBody().getItems().getItem();
            return result;

        } catch (Exception e) {
            System.out.println("항공편 조회 중 오류가 발생했습니다.");
        }
        return null;
    }

}