package pl.javastart.restoffers;

import antlr.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OfferRestController {

    private OfferRepository offerRepository;

    public OfferRestController(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @GetMapping("/api/offers")
    public List<Offer> getOffers(){
            return offerRepository.findAll();
    }

    @GetMapping("/api/offers/count")
    public int offersCount() {
        final int offerNumber = offerRepository.findAll().size();
        return offerNumber;
    }


}
