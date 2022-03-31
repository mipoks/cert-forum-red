package design.kfu.sunrise;

import design.kfu.sunrise.domain.dto.CategoryDTO;
import design.kfu.sunrise.domain.dto.ClubCDTO;
import design.kfu.sunrise.domain.model.Account;
import design.kfu.sunrise.domain.model.embedded.AccountInfo;
import design.kfu.sunrise.domain.model.embedded.CostInfo;
import design.kfu.sunrise.repository.AccountRepository;
import design.kfu.sunrise.repository.ClubRepository;
import design.kfu.sunrise.service.AccountService;
import design.kfu.sunrise.service.AuthorityService;
import design.kfu.sunrise.service.CategoryService;
import design.kfu.sunrise.service.ClubService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SunriseApplicationTests {


    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private ClubService clubService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CategoryService categoryService;

    @Test
    void contextLoads() {
    }

//    @Test
//    void testOneToMany() {
//        Club club = new Club();
//        Account account1 = new Account();
//        Account account2 = new Account();
//        club.getAccounts().add(account1);
//        club.getAccounts().add(account2);
//
//        club = clubRepository.save(club);
//
//        club.getAccounts().remove(account1);
//        clubRepository.save(club);
//    }

    @Test
    void createCategory() {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name("Детские мягкие игрушки")
                .description("В форме зверей")
                .build();

        categoryService.addCategory(categoryDTO);
    }

    @Test
    void createAccount() {
        Account account = Account.builder()
                .accountInfo(
                        AccountInfo.builder()
                                .phone("+79272422387")
                                .build()
                )
                .hashPassword("testPwd")
                .login("temp@mail.ru")
                .build();
        accountRepository.save(account);
    }

    @Test
    void createClub() {
        ClubCDTO clubCDTO = ClubCDTO.builder()
                .categoryId(1L)
                .creatorId(1L)
                .name("Сертификат на игрушки")
                .description("Собираю деньги на сертификат. Казань")
                .costInfo(
                        CostInfo.builder()
                                .certificateCost(25000)
                                .entryCost(5000)
                                .build()
                )
                .build();
        clubService.addClub(clubCDTO);
    }

//    @Test
//    void testRelationShip() {
//        accountRepository.save(Account.builder().login("temp@mail.ru").build());
//        Account account = accountRepository.getAccountByLogin("temp@mail.ru").get();
//        clubService.addClub(ClubCDTO.builder().name("Club!").build());
//        Club club = clubRepository.findById(1L).get();
//        clubService.addAccountToClub(club, account);
//
//    }


//    @Test
////    @Transactional
//    void testAddClubs() {
//        Account account = accountRepository.getAccountByLogin("temp@mail.ru").get();
//
//        clubService.addClub(ClubCDTO.builder().name("Club2").build());
//        clubService.addClub(ClubCDTO.builder().name("Club3").build());
//
//        Club club2 = clubRepository.findById(2L).get();
//        Club club3 = clubRepository.findById(3L).get();
//        clubService.addAccountToClub(club2, account);
//        clubService.addAccountToClub(club3, account);
//
////        Club club = clubRepository.findById(1L).get();
////        Authority authority = authorityService.findOrThrow(account, club);
////        Set<Authority.AuthorityType> authorityTypeSet = authority.getAuthorityTypes();
////        authorityTypeSet.forEach(System.out::println);
//    }
//
//
//    @Test
//    @Transactional
//    void testGetAuthority() {
//        Account account = accountRepository.getAccountByLogin("temp@mail.ru").get();
//
//        account.getClubs().forEach(System.out::println);
////        Club club = clubRepository.findById(1L).get();
////        Authority authority = authorityService.findOrThrow(account, club);
////        Set<Authority.AuthorityType> authorityTypeSet = authority.getAuthorityTypes();
////        authorityTypeSet.forEach(System.out::println);
//    }

}
