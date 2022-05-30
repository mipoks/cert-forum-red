package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.dto.category.CategoryDTO;
import design.kfu.sunrise.domain.dto.club.ClubVDTO;
import design.kfu.sunrise.service.mail.util.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author Daniyar Zakiev
 */
@RestController
@RequestMapping(value = "/v1")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PermitAll
    @GetMapping("/search/clubs")
    public Set<ClubVDTO> getClubsByName(@Size(min = 3) @RequestParam("name") String clubLike, @RequestParam("description") Boolean withDescription) {
        if (withDescription) {
            return searchService.getClubsByNameAndDescription(clubLike);
        } else {
            return searchService.getClubsByName(clubLike);
        }
    }

    @PermitAll
    @GetMapping("/search/categories")
    public Set<CategoryDTO> getCategoriesByName(@Size(min = 3) @RequestParam("name") String categoryLike, @RequestParam("description") Boolean withDescription) {
        if (withDescription) {
            return searchService.getCategoriesByNameAndDescription(categoryLike);
        } else {
            return searchService.getCategoriesByName(categoryLike);
        }
    }

}
