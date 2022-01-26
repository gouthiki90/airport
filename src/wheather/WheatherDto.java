package wheather;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WheatherDto {
    private Response response;

    @AllArgsConstructor
    @Data
    class Response {
        private Header header;
        private Body body;

        @AllArgsConstructor
        @Data
        class Header {
            private String resultCode;
            private String resultMsg;
        }

        @AllArgsConstructor
        @Data
        class Body {
            private String dataType;
            private Items items;

            @AllArgsConstructor
            @Data
            class Items {
                private List<Item> item;
                private int pageNo;
                private int numOfRows;
                private int totalCount;

                @AllArgsConstructor
                @Data
                class Item {
                    private String baseDate;
                    private String baseTime;
                    private String category;
                    private String fcstDate;
                    private String fcstTime;
                    private String fcstValue;
                    private int nx;
                    private int ny;
                }
            }
        }
    }
}
