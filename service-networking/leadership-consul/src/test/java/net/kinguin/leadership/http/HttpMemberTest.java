package net.kinguin.leadership.http;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.junit.Assert.assertTrue;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import net.kinguin.leadership.http.config.HttpMemberProperties;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class HttpMemberTest {

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(4040);

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  private HttpMember httpElection;

  @Before
  public void setUp() {
    httpElection = new HttpMember(
        new HttpMemberProperties("test-hostname", "http://localhost:4040"));
  }

  @Test
  public void should_return_leader_response_when_request_to_election_endpoint() {
    // given
    stubFor(
        get(("/"))
            .willReturn(aResponse().withStatus(200).withBody("{\"name\": \"test-hostname\"}")));

    // when / then
    assertTrue("Expected hostname is not leader", httpElection.isLeader());
  }
}