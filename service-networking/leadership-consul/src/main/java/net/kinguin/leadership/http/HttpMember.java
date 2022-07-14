package net.kinguin.leadership.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import net.kinguin.leadership.core.Member;
import net.kinguin.leadership.http.config.HttpMemberProperties;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.subjects.PublishSubject;

@Log
@AllArgsConstructor
public class HttpMember implements Member {

  private final PublishSubject<Object> publisher = PublishSubject.create();
  private HttpMemberProperties properties;

  public boolean isLeader() {
    OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder()
        .url(properties.getCheckUrl())
        .build();

    Response response;
    try {
      response = client.newCall(request).execute();

      if (!response.isSuccessful()) {
        response.close();
        throw new IOException();
      }

      ObjectMapper objectMapper = new ObjectMapper();
      ElectionResponse res = objectMapper
          .readValue(response.body().string(), ElectionResponse.class);

      return res.getName().equals(properties.getNodeName());

    } catch (IOException e) {
      log.warning(String.format("Something went wrong with get leader. Cause %s", e.getMessage()));
      return false;
    }
  }

  @Override
  public Observable<Object> asObservable() {
    return publisher;
  }
}
