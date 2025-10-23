package app.web.seun_olo2.URLShortingAPI.controller;


import app.web.seun_olo2.URLShortingAPI.model.ResponseMessage;
import app.web.seun_olo2.URLShortingAPI.model.UrlModel;
import app.web.seun_olo2.URLShortingAPI.service.ShortUrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shorten")
public class ShortUrlController {
    private final ShortUrlService shortUrlService;
    private ResponseMessage rm;

    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UrlModel urlModel){
        rm = shortUrlService.create(urlModel);
        if(rm.getType().equals("error"))
            return ResponseEntity
                    .status(rm.getHttpStatus())
                    .body(rm.getMessage());

        return ResponseEntity
                .status(rm.getHttpStatus())
                .body(rm.getObject());
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<?> retrieveOriginalUrl(@PathVariable("shortCode") String shortCode){
        rm = shortUrlService.getByShortCode(shortCode);
        if(rm.getType().equals("error"))
            return ResponseEntity
                    .status(rm.getHttpStatus())
                    .body(rm.getMessage());

        return ResponseEntity
                .status(rm.getHttpStatus())
                .body(rm.getObject());
    }

    @PutMapping("/{shortCode}")
    public ResponseEntity<?> update(@PathVariable("shortCode") String shortCode, @RequestBody UrlModel urlModel){
        rm = shortUrlService.update(shortCode, urlModel);
        if(rm.getType().equals("error"))
            return ResponseEntity
                    .status(rm.getHttpStatus())
                    .body(rm.getMessage());

        return ResponseEntity
                .status(rm.getHttpStatus())
                .body(rm.getObject());
    }

    @DeleteMapping("/{shortCode}")
    public ResponseEntity<?> delete(@PathVariable("shortCode") String shortCode) {
        rm = shortUrlService.delete(shortCode);
        return ResponseEntity
                .status(rm.getHttpStatus())
                .body(rm.getMessage());
    }

    @GetMapping("/{shortCode}/stats")
    public ResponseEntity<?> getStats(@PathVariable("shortCode") String shortCode) {
        rm = shortUrlService.getStats(shortCode);
        if (rm.getType().equals("error"))
            return ResponseEntity
                    .status(rm.getHttpStatus())
                    .body(rm.getMessage());
        return ResponseEntity
                .status(rm.getHttpStatus())
                .body(rm.getObject());
    }

}
