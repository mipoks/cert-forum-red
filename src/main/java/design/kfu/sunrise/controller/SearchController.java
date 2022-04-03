package design.kfu.sunrise.controller;

import design.kfu.sunrise.domain.dto.CategoryDTO;
import design.kfu.sunrise.domain.dto.ClubVDTO;
import design.kfu.sunrise.service.mail.util.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.Set;

/**
 * @author Daniyar Zakiev
 */
@RestController
@RequestMapping(value = "v1")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PermitAll
    @GetMapping("/search/clubs")
    public Set<ClubVDTO> getClubsByName(@RequestParam("name") String clubLike) {
        return searchService.getClubsByName(clubLike);
    }

    @PermitAll
    @GetMapping("/search/categories")
    public Set<CategoryDTO> getCategoriesByName(@RequestParam("name") String categoryLike) {
        return searchService.getCategoriesByName(categoryLike);
    }

}
