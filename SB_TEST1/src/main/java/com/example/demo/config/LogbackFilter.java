package com.example.demo.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * sql 로그 출력 막기
 * 쿼리에 NO_LOG 표시
 * @author USER
 *
 */
public class LogbackFilter extends Filter<ILoggingEvent>{
    @Override
    public FilterReply decide(ILoggingEvent event) {    
        if (event.getMessage().contains("NO_LOG")) {
            return FilterReply.DENY;  // FilterReply.DENY; 로그 막는거인듯
        }else{
            return FilterReply.ACCEPT;
        }
    }
}