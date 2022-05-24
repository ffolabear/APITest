package APITest.naverAPI.domain.movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ParsingJson {

    public String parsingActor(String actorList) {

        String[] temp = actorList.split("\\|");
        List<String> result = new ArrayList<>();
        String resultToString;

        if (temp.length == 0) {
            resultToString = "-";
        } else {
            result = new ArrayList<>(Arrays.asList(temp));
            resultToString = String.join(", ", result);
        }

        return resultToString;
    }

    public String parsingImageUrl(String imageUrl) {
        imageUrl = imageUrl.replace("\\", "");
        return imageUrl;
    }

    public String parsingDirector(String directorList) {
        String[] result = directorList.split("\\|");
        String resultToString = String.join(", ", result);

        return resultToString;
    }

    public String parsingTitle(String title) {
        title = title.replace("<b>", "");
        title = title.replace("</b>", "");
        return title;
    }

    public String parsingSubTitle(String subtitle) {
        return subtitle;
    }

    public Integer parsingPubDate(String pubdate) {
        return Integer.parseInt(pubdate);
    }

    public Double parsingUserRating(String userRating) {
        return Double.parseDouble(userRating);
    }
}
