package de.janlingen.bezahlservice.saga;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

/**
 * @author janlingen
 */
@Component
public class StringParser {

  public Double parseGesamtwarenwertFromString(String input) {
    Pattern pattern = Pattern.compile("Produkt\\(name=([^,]+), preis=([^,]+), anzahl=([^)]+)\\)");
    Matcher matcher = pattern.matcher(input);

    Double gesamtwarenwert = 0.0;

    while (matcher.find()) {
      Double preis = Double.parseDouble(matcher.group(2));
      Long anzahl = Long.parseLong(matcher.group(3));
      gesamtwarenwert += preis * anzahl;
    }
    return gesamtwarenwert;
  }

  public String parseKundenIdFromString(String input) {

    Pattern pattern = Pattern.compile("kundenId=([^,]+)");
    Matcher matcher = pattern.matcher(input);

    if (matcher.find()) {
      return matcher.group(1);
    } else {
      return null;
    }
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
