package ss;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<UUID> uuidList = new ArrayList<>();
        uuidList.add(UUID.randomUUID());
        uuidList.add(UUID.randomUUID());
        uuidList.add(UUID.randomUUID());
        uuidList.add(UUID.randomUUID());
        uuidList.add(UUID.randomUUID());

        String body = uuidList.toString();

//        HttpEntity requestEntity = new HttpEntity<>(requestBody, headers);
        String url = "http://localhost:8088/api/v1/admin/list";
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(body);
        System.out.println(response);
    }

}
