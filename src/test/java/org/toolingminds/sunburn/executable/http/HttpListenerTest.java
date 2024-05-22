package org.toolingminds.sunburn.executable.http;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpListenerTest {

    @Test
    public void testTrackEndpoint() throws Exception {
        OkHttpClient client = new OkHttpClient();

        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create("{\"test\":\"test\"}", JSON);
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/track")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            assertEquals(202, response.code());
        }
    }
}