package ait.cohort49.shop.logging;

import ait.cohort49.shop.model.dto.ProductDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Sergey Bugaenko
 * {@code @date} 19.12.2024
 */

// AspectJ - фреймворк


@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // JoinPoint - точка соединение (где будет выполняться)

    @Pointcut("execution(* ait.cohort49.shop.service.ProductServiceImpl.saveProduct(..))")
    public void saveProductPoint(){
        // Метод без тела, используется для создания PointCut
    }

//    @Before("saveProductPoint()")
//    public void beforeSavingProduct() {
//        logger.info("Method saveProduct in class ProductServiceImpl was called");
//    }

    @Before("saveProductPoint()")
    public void beforeSavingProductWithArgs(JoinPoint joinPoint) {
        // Извлекаем параметры
        Object[] params = joinPoint.getArgs();
        // Логируем информацию о вызове метода
        logger.info("Method saveProduct in class ProductServiceImpl was called with parameter: {}", params[0]);
//        logger.info("Method saveProduct in class ProductServiceImpl (arrays): {}", Arrays.toString(params));

    }

    @After("saveProductPoint()")
    public void afterSavingProduct(){
        logger.info("Method saveProduct in class ProductServiceImpl finished its execution");
    }

//    @AfterReturning
//    @AfterThrowing
//    public void afterReturning(JoinPoint joinPoint, Exception ex) {}

    @Pointcut("execution(* ait.cohort49.shop.service.ProductServiceImpl.getAllActiveProducts(..))")
    public void getAllProducts() {
        // Пустой
    }

    @Around("getAllProducts()")
    public Object aroundGetAllProducts(ProceedingJoinPoint joinPoint) throws Throwable {

        Object result = null;

        try {
            // Логирую до выполнения основного метода
            logger.info("Method `getAllActiveProducts` of the class ProductServiceImpl was called");

            // Выполняю основной метож
            result = joinPoint.proceed();

            //Логирование после успешного выполнения метод
            logger.info("Method `getAllActiveProducts` successfully returned with result: {}", result);

            // Изменяем результат, добавляя доп.логику

            result = ((List<ProductDTO>) result).stream()
                    .filter(product -> product.getPrice().doubleValue() > 1)
                    .toList();

        } catch (Throwable ex){
            // Логируем, если произошло исключение
            logger.error("Method `getAllActiveProducts` threw an exception: {}", ex.getMessage());

        }

        // Возвращаю модифицированный результат
        return  result == null ? Collections.emptyList() : result;

    }

}
