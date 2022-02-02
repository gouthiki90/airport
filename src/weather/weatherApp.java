package weather;

import java.util.List;
import java.util.Scanner;

import weather.weatherDto.Response.Body.Items.Item;

public class weatherApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("⌌ 오늘 날짜를 입력해주세요. ⌍");
        System.out.println("⌌ 날짜를 입력하면 현재 온도를 알려드립니다. ⌍");
        System.out.println("⌌ ex) 20220202 ⌍");
        String baseDate = sc.nextLine();

        List<Item> weatherList = Downloadweather.getweatherList(baseDate);
        for (int i = 0; i < weatherList.size(); i++) {
            System.out.println("현재 온도는" + weatherList.get(i).getObsrValue() + "도 입니다.");
        }
    }
}
