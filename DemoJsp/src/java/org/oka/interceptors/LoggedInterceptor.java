package org.oka.interceptors;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

@Named
public class LoggedInterceptor implements Serializable {

    public LoggedInterceptor() {
    }

    @AroundInvoke
    public Object logMethodEntry(InvocationContext context)
            throws Exception {

        //Object o = null;
        Logger logger = Logger.getLogger(context.getMethod().getDeclaringClass().toString());
        long start = System.currentTimeMillis();

        if (context.getMethod().getName().equalsIgnoreCase("getUser")) {
            Object[] parametreler = context.getParameters(); // Kesilen metodun parametreleri alınıyor
            String userName = parametreler[0].toString();

            // userName = "elifa";
            // parametreler[0] = userName;
            // context.setParameters(parametreler);
        }

        try {
            //o = context.proceed();
            return context.proceed();
        } finally {
            Date myDate = new Date();
            logger.log(Level.INFO, " Tarih :{0} class:{1} : method {2} executed in {3} milliseconds",
                    new Object[]{
                        myDate.toString(), 
                        context.getMethod().getDeclaringClass().toString(),
                        context.getMethod().getName(), 
                        System.currentTimeMillis() - start});
        }

    }
}
