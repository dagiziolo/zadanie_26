package pl.javastart.restoffers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
public class OfferRestController {

    private OfferRepository offerRepository;
    private CategoryRepository categoryRepository;

    public OfferRestController(OfferRepository offerRepository, CategoryRepository categoryRepository) {
        this.offerRepository = offerRepository;
        this.categoryRepository = categoryRepository;
    }


    //    GET: /api/offers/count -> zwraca liczbę ogłoszeń w serwisie
    @GetMapping("api/offers/count")
    public int offersCount() {
        final int offerNumber = (int) offerRepository.count();
        return offerNumber;
    }

    //    GET: /api/offers -> zwraca listę wszystkich ogłoszeń
    //    GET: /api/offers?title=param_value
    @GetMapping("api/offers")
    public List<OfferDto> getOffersByTitle(@RequestParam(required = false) String title) {
        List<OfferDto> listOfferDto = new ArrayList<>();
        List<Offer> offers;

        if (title == null || title == "") {
            offers = offerRepository.findAll();
        } else {
            offers = offerRepository.findUsingTitle(title.toLowerCase());
        }

        for (Offer offer : offers) {
            OfferDto offerDto = new OfferDto(offer.getId(), offer.getTitle(), offer.getDescription(), offer.getImgUrl(), offer.getPrice(), offer.getCategory().getName());
            listOfferDto.add(offerDto);
        }
        return listOfferDto;
    }

    // POST: /api/offers – zapisuje przesłaną ofertę.
    @PostMapping("api/offers")
    public void saveOffer(@RequestBody OfferDto offerDto) {
        Category category = categoryRepository.findByName(offerDto.getCategory());
        Offer newOffer = new Offer(offerDto.getTitle(), offerDto.getDescription(), offerDto.getImgUrl(), offerDto.getPrice(), category);
        offerRepository.save(newOffer);
    }

    //    GET: /api/offers/{id} – zwraca ofertę o wskazanym id
    @GetMapping("/api/offers/{id}")
    public ResponseEntity<OfferDto> getOffer(@PathVariable long id) {
        Optional<Offer> optional = offerRepository.findById(id);
        if (optional.isPresent()) {
            Offer offer = optional.get();
            OfferDto offerDto = new OfferDto(offer.getId(), offer.getTitle(), offer.getDescription(), offer.getImgUrl(), offer.getPrice(), offer.getCategory().getName());
            return ResponseEntity.ok(offerDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //    DELETE: /api/offers/{id}
    @DeleteMapping("/api/offers/{id}")
    public void removeOffer(@PathVariable long id) {
        Optional<Offer> optional = offerRepository.findById(id);
        if (optional.isPresent()) {
            offerRepository.delete(optional.get());
        }
    }
}
