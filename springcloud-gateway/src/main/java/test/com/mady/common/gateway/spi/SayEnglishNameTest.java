package test.com.mady.common.gateway.spi; 

import com.mady.common.gateway.spi.ISayName;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ServiceLoader;

/** 
* SayEnglishName Tester. 
* 
* @author <Authors name> 
* @since <pre>10�� 16, 2020</pre> 
* @version 1.0 
*/ 
public class SayEnglishNameTest { 

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: sayName(String name)
    *
    */



    @Test
    public void testSayName() throws Exception {
        ServiceLoader<ISayName> serviceLoaders = ServiceLoader.load(ISayName.class);
        for(ISayName iSayName : serviceLoaders){
            iSayName.sayName("mady");
        }
    }
}
