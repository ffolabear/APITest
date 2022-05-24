package APITest.naverAPI.web.movie;

import APITest.naverAPI.domain.movie.Movie;
import APITest.naverAPI.domain.movie.ParsingJson;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

@Slf4j
@RequestMapping("/main/movieApi")
@Controller
//@RestController
public class MovieController {

    @GetMapping
    public String hello() {
        log.info("movie controller called");
        return "movie/movieMain";
    }

    //    @ResponseBody
    @PostMapping("/movieSearch")
    public String search(String searchWord, Model model) throws ParseException {

        log.info("movie search method call!");

        //=====================================Naver API Logic===================================================

        String clientId = "kSzN57Il4cGSY7RTWd27"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "aZD1YIQlDu"; //애플리케이션 클라이언트 시크릿값"
        String text = null;

        try {
            text = URLEncoder.encode(searchWord, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패", e);
        }

        String apiURL = "https://openapi.naver.com/v1/search/movie.json?query=" + text + "&display=10&start=1";    // json 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL, requestHeaders);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(responseBody);
        JSONArray infoArray = (JSONArray) jsonObject.get("items");

        //=======================================================================================================

        List<Movie> searchResult = new ArrayList<>();
        ParsingJson parsingJson = new ParsingJson();
        Long id = 1L;

        for (int i = 0; i < infoArray.size(); i++) {

            Movie movie = new Movie();
            JSONObject tempJson = (JSONObject) infoArray.get(i);

            List<String> actorList = parsingJson.parsingActor(tempJson.get("actor").toString());
            String image = parsingJson.parsingImageUrl(tempJson.get("image").toString());
            List<String> directorList = parsingJson.parsingDirector(tempJson.get("director").toString());
            String title = parsingJson.parsingTitle(tempJson.get("title").toString());
            String subtitle = parsingJson.parsingSubTitle(tempJson.get("subtitle").toString());
            Integer pubDate = parsingJson.parsingPubDate(tempJson.get("pubDate").toString());
            Double userRating = parsingJson.parsingUserRating(tempJson.get("userRating").toString());

            movie.setId(id++);
            movie.setTitle(title);
            movie.setSubtitle(subtitle);
            movie.setActor(actorList);
            movie.setDirector(directorList);
            movie.setImage(image);
            movie.setUserRating(userRating);
            movie.setPubDate(pubDate);

            searchResult.add(movie);
        }


        model.addAttribute("searchWord", searchWord);
        model.addAttribute("searchResult", searchResult);

        return "movie/movieResult";
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

}
