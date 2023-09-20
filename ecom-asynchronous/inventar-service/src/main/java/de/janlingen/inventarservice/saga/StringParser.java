package de.janlingen.inventarservice.saga;

import de.janlingen.inventarservice.data.InventarPositionDTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

/**
 * @author janlingen
 */
@Component
public class StringParser {
  public List<InventarPositionDTO> parseInventarPositionenFromString(String input) {
    List<InventarPositionDTO> posis = new ArrayList<>();

    Pattern pattern = Pattern.compile("Produkt\\(name=([^,]+), preis=([^,]+), anzahl=([^)]+)\\)");
    Matcher matcher = pattern.matcher(input);

    while (matcher.find()) {
      String name = matcher.group(1);
      Long anzahl = Long.parseLong(matcher.group(3));

      InventarPositionDTO posi = InventarPositionDTO.builder()
          .name(name)
          .anzahl(anzahl)
          .build();
      posis.add(posi);
    }
    return posis;
  }

  public LocalDateTime parseModifiziertFromString(String input) {
    Pattern pattern = Pattern.compile("modifiziert=([^,]+)");
    Matcher matcher = pattern.matcher(input);

    if (matcher.find()) {
      String modifiziertString = matcher.group(1);
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
      return LocalDateTime.parse(modifiziertString, formatter);
    } else {
      return null;
    }
  }
}



