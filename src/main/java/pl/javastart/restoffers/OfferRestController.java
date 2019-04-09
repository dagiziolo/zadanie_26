package pl.javastart.restoffers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OfferRestController {

    private OfferRepository offerRepository;

    public OfferRestController(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @GetMapping("/api/offers")
    public List<Offer> getAll() {
        return offerRepository.findAll();
    }
}
