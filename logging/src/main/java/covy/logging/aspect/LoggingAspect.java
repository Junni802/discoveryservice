package covy.logging.aspect;

import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
  private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
  private final ObjectMapper objectMapper;

  @Pointcut("execution(* com.sch.sch_elasticsearch.domain..*.*(..)) " +
      "&& @annotation(com.sch.sch_elasticsearch.aop.SaveLogging)" )
  public void pointCut() {}

  @AfterReturning("pointCut()")
    // SaveLogging 어노테이션이 붙은 메서드의 로그를 DEBUG 레벨로 기록
    // 실제로 아래 내역대로 로깅하면 안 된다. 테스트용으로 시험했다.
    logger.debug("포인트컷");
  }
}
