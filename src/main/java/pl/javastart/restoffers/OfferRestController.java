package pl.javastart.restoffers;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class OfferRestController {

    private OfferRepository offerRepository;
    private CategoryRepository categoryRepository;

    public OfferRestController(OfferRepository offerRepository, CategoryRepository categoryRepository) {
        this.offerRepository = offerRepository;
        this.categoryRepository = categoryRepository;
    }

//    GET: /api/offers -> zwraca listę wszystkich ogłoszeń
//    poniżej razem z wyszukiwaniem

    //    GET: /api/offers/count -> zwraca liczbę ogłoszeń w serwisie
    @GetMapping("api/offers/count")
    public int offersCount() {
        final int offerNumber = (int) offerRepository.count();
        return offerNumber;
    }

//    GET: /api/offers?title=param_value
//    GET: /api/offers -> zwraca listę wszystkich ogłoszeń
    @GetMapping("api/offers")
    public List<OfferDto> getOffersByTitle(@RequestParam(required = false) String title) {

        List<OfferDto> result = new ArrayList<>();
        List<Offer> offers;

        if (title == null || title == "") {
            offers = offerRepository.findAll();
        } else {
            offers = offerRepository.findUsingTitle(title.toLowerCase());
        }

        for (Offer offer : offers) {
            OfferDto offerDto = new OfferDto(offer.getId(), offer.getTitle(), offer.getDescription(), offer.getImgUrl(), offer.getPrice(), offer.getCategory().getName());
            result.add(offerDto);
        }
        return result;
    }


//    GET: /api/categories/names – zwraca listę nazw wszystkich dostępnych kategorii
//w category


// POST: /api/offers – zapisuje przesłaną ofertę.
    @PostMapping("api/offers")
    public void saveOffer(@RequestBody OfferDto offerDto) {
        Category category = categoryRepository.findByName(offerDto.getCategory());
        Offer newOffer = new Offer(offerDto.getTitle(), offerDto.getDescription(), offerDto.getImgUrl(), offerDto.getPrice(), category);
        offerRepository.save(newOffer);
    }

//    GET: /api/categories – zwraca listę wszystkich kategorii w serwisie.
//    w kategoriach

//    GET: /api/offers/{id} – zwraca ofertę o wskazanym id

//    POST: /api/categories -> pozwala dodać nową kategorię przesyłając do niego odpowiedni obiekt JSON

//    DELETE: /api/offers/{id}

//    DELETE: /api/categories/{id}
}
