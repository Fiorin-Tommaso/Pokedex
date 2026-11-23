import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Pokemon;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

void main() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Inserisci nome o numero del Pokémon: ");
    String query = scanner.nextLine().trim().toLowerCase();

    String url = "https://pokeapi.co" + query;

    try {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());

            int index = root.get("id").asInt();
            String nome = root.get("name").asText();

            JsonNode typesNode = root.get("types");
            String tipo1 = typesNode.get(0).get("type").get("name").asText();
            String tipo2 = typesNode.size() > 1
                    ? typesNode.get(1).get("type").get("name").asText()
                    : null;

            int hp = 0;
            for (JsonNode statNode : root.get("stats")) {
                if ("hp".equals(statNode.get("stat").get("name").asText())) {
                    hp = statNode.get("base_stat").asInt();
                    break;
                }
            }

            Pokemon pokemon = new Pokemon(index, nome, tipo1, tipo2, hp);
            System.out.println(pokemon);
        } else {
            System.out.println("Pokémon non trovato.");
        }
    } catch (Exception e) {
        System.out.println("Errore: " + e.getMessage());
    }
}
