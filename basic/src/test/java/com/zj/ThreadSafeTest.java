package com.zj;

import org.junit.Test;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2017/12/27
 * Time: 17:12
 * CopyRight: Zhouji
 */
public class ThreadSafeTest {
    @ThreadSafe
    public class synFActor implements Servlet {

        @GuardedBy("this") private BigInteger laNumber;

        @Override
        public void init(ServletConfig servletConfig) throws ServletException {

        }

        @Override
        public ServletConfig getServletConfig() {
            return null;
        }

        @Override
        public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        }

        @Override
        public String getServletInfo() {
            return null;
        }

        @Override
        public void destroy() {

        }
    }

    @Test
    public void local() {
        DateFormat dateFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd")).get();
//        ThreadLocal.
    }
}
