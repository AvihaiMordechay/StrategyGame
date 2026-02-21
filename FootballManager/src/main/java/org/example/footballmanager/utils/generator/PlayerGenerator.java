package org.example.footballmanager.utils.generator;

import org.example.footballmanager.dto.PlayerDTO;
import org.example.footballmanager.utils.enums.PlayerPosition;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class PlayerGenerator {
    private final List<String> firstNames;
    private final List<String> lastNames;
    private final PlayerAttributesGenerator attributesGenerator;

    public PlayerGenerator(PlayerAttributesGenerator attributesGenerator) {
        this.attributesGenerator = attributesGenerator;
        this.firstNames = new ArrayList<>();
        this.lastNames = new ArrayList<>();
    }

    public List<PlayerDTO> generatePlayers() throws IOException {
        reloadNames();
        int count = this.firstNames.size();
        List<PlayerDTO> players = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String firstName = firstNames.get((int) (Math.random() * firstNames.size()));
            String lastName = lastNames.get((int) (Math.random() * lastNames.size()));
            int age = 18 + (int) (Math.random() * 15);

            if (i < count * 0.1) {
                players.add(createPlayer(firstName, lastName, age, PlayerPosition.GOALKEEPER));
            } else if (i < count * 0.4) {
                players.add(createPlayer(firstName, lastName, age, PlayerPosition.DEFENDER));
            } else if (i < count * 0.7) {
                players.add(createPlayer(firstName, lastName, age, PlayerPosition.MIDFIELDER));
            } else {
                players.add(createPlayer(firstName, lastName, age, PlayerPosition.FORWARD));
            }
        }
        return players;
    }

    private PlayerDTO createPlayer(String firstName, String lastName, int age, PlayerPosition position) {
        	return new PlayerDTO(
                    null,
                    firstName,
                    lastName,
                    age,
                    false,
                    attributesGenerator.generateAttributesForPosition(position),
                    position
            );
    }

    public void reloadNames() throws IOException {
        firstNames.clear();
        lastNames.clear();

        loadNamesFromFile("first_names.csv", firstNames);
        loadNamesFromFile("last_names.csv", lastNames);

        if (firstNames.size() != lastNames.size()) {
            throw new IllegalStateException(
                    "First names and last names files must contain the same number of rows. " +
                            "First: " + firstNames.size() +
                            ", Last: " + lastNames.size()
            );
        }

        Collections.shuffle(firstNames);
        Collections.shuffle(lastNames);
    }

    private void loadNamesFromFile(String path, List<String> targetList) throws IOException {
        try (var in = PlayerGenerator.class.getClassLoader().getResourceAsStream(path)) {

            if (in == null) {
                throw new FileNotFoundException("File not found: " + path);
            }

            try (var reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.isBlank()) {
                        targetList.add(line.trim());
                    }
                }
            }
        }
    }

}
