package br.com.wiremock.teste;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class TesteWiremockTodosVerbos {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8091);

	@Before
	public void setupStub() {

		stubFor(delete("/sucesso").willReturn(ok()));

		stubFor(get("/sucesso-with-body").willReturn(ok("body content")));

		stubFor(get("/json").willReturn(okJson(
				"{\"userId\" : 1,\"id\" : 1,\"title\" : \"livro da chapuzinho vermelho\",\"body\" : \"livro da chapuzinho vermelho\"}")));

		stubFor(post("/redirecionar").willReturn(temporaryRedirect("/novo/lugar")));

		stubFor(post("/desculpa-nao").willReturn(unauthorized()));

		stubFor(put("/status-somente").willReturn(status(418)));

		stubFor(get("/erro500").willReturn(status(500)));

		stubFor(get(urlEqualTo("/binary-body")).willReturn(aResponse().withBody(new byte[] { 1, 2, 3, 4 })));
	}

	@Test
	public void testeStatusCodePositive() {

		given().when().get("http://localhost:8091/json").then().assertThat().statusCode(200).log().all();
	}

	@Test
	public void testeSucessoBody() {
		given().when().get("http://localhost:8091/sucesso-with-body").then().assertThat().statusCode(200).log().all();
	}

	@Test
	public void testeRedirecionar() {
		given().when().post("http://localhost:8091/redirecionar").then().assertThat().statusCode(302).log().all();
	}

	@Test
	public void testeNaoAutorizado() {
		given().when().post("http://localhost:8091/desculpa-nao").then().assertThat().statusCode(401).log().all();
	}

	@Test
	public void testeErro500() {
		given().when().get("http://localhost:8091/erro500").then().assertThat().statusCode(500).log().all();
	}

	@Test
	public void testeDeletar() {
		given().when().post("http://localhost:8091/desculpa-nao").then().assertThat().statusCode(401).log().all();
	}

	@Test
	public void testeAlterarComStatusSomente() {
		given().when().put("http://localhost:8091/status-somente").then().assertThat().statusCode(418).log().all();
	}
	

}
