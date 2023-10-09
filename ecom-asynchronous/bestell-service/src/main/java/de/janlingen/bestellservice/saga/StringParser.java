package de.janlingen.bestellservice.saga;

import de.janlingen.bestellservice.domain.Bestellung;
import de.janlingen.bestellservice.domain.Produkt;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author janlingen
 */
@Component
public class StringParser {
  public Bestellung parseBestellungFromString(String input) {
    String[] parts = input.split(", ");
    Long id = Long.parseLong(parts[0].substring(parts[0].indexOf("=") + 1));
    String kundenId = parts[1].substring(parts[1].indexOf("=") + 1);

    String produktPart = parts[2].substring(parts[2].indexOf("[") + 1, parts[2].indexOf("]"));
    Produkt produkt = parseProduktFromString(produktPart);

    Boolean bestellungExcecuted = Boolean.parseBoolean(parts[3].substring(parts[3].indexOf("=") + 1));
    String bezahlungStatus = parts[4].substring(parts[4].indexOf("=") + 1);
    String inventarStatus = parts[5].substring(parts[5].indexOf("=") + 1);

    LocalDateTime erstellt = LocalDateTime.parse(parts[6].substring(parts[6].indexOf("=") + 1));
    LocalDateTime modifiziert = LocalDateTime.parse(parts[7].substring(parts[7].indexOf("=") + 1));

    return Bestellung.builder()
        .id(id)
        .kundenId(kundenId)
        .produktList(List.of(produkt))
        .bestellungExcecuted(bestellungExcecuted)
        .erstellt(erstellt)
        .modifiziert(modifiziert)
        .build();
  }

  private static Produkt parseProduktFromString(String produktPart) {
    String[] produktParts = produktPart.split(", ");
    String name = produktParts[0].substring(produktParts[0].indexOf("=") + 1);
    Double preis = Double.parseDouble(produktParts[1].substring(produktParts[1].indexOf("=") + 1));
    Long anzahl = Long.parseLong(produktParts[2].substring(produktParts[2].indexOf("=") + 1));

    return Produkt.builder()
        .name(name)
        .preis(preis)
        .anzahl(anzahl)
        .build();
  }
}
