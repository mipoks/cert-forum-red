package design.kfu.sunrise.service;

import org.springframework.context.ApplicationContext;

/**
 * @author Daniyar Zakiev
 */
public class StaticService {

    private static ApplicationContext applicationContext;

    public StaticService(ApplicationContext applicationContext) {
        StaticService.applicationContext = applicationContext;
    }

    private static <T> T getInstance(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static CategoryService getCategoryService() {
        return getInstance(CategoryService.class);
    }

    public static AccountService getAccountService() {
        return getInstance(AccountService.class);
    }
}
