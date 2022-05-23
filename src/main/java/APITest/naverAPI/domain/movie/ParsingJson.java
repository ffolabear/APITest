package APITest.naverAPI.domain.movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParsingJson {

    private List<String> parsingActor(String actorList) {

        actorList = actorList.replace("|", " ");
        String[] temp = actorList.split(" ");
        return new ArrayList<>(Arrays.asList(temp));
    }

    private String parsingImageUrl(String imageUrl) {
        imageUrl = imageUrl.replace("\\", "");
        return imageUrl;
    }

    private List<String> parsingDirector(String directorList) {
        directorList = directorList.replace("|", " ");
        String[] temp = directorList.split(" ");
        return new ArrayList<>(Arrays.asList(temp));
    }

    private String parsingTitle(String title) {

        return "";
    }
}
