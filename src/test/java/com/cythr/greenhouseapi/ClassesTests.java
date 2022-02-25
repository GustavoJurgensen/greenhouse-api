package com.cythr.greenhouseapi;

import com.cythr.greenhouseapi.models.Greenhouse;
import com.cythr.greenhouseapi.models.GreenhouseData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static javax.management.timer.Timer.ONE_HOUR;

@ExtendWith(SpringExtension.class)
@DisplayName("Class coverage tests")
public class ClassesTests {
    Greenhouse gh1 = new Greenhouse(1L,"tomato","1",3900F,35F,90F,2500F);
    Greenhouse gh2 = new Greenhouse(2L,"strawberry","2",3950F,33F,85F,2700F);
    Greenhouse gh3 = new Greenhouse(2L,"strawberry","2",3950F,33F,85F,2700F);

    Date date = new Date();
    GreenhouseData data1 = new GreenhouseData(1L,"1", new Date(date.getTime() - ONE_HOUR*24*10),3900F,
            35F,85F,2700F,25F, 50F,0F);
    GreenhouseData data2 = new GreenhouseData(2L,"1", new Date(date.getTime() - ONE_HOUR*24*2),3950F,
            25F,70F,2755F,15F, 75F,0F);
    GreenhouseData data3 = new GreenhouseData(2L,"1", new Date(date.getTime() - ONE_HOUR*24*2),3950F,
            25F,70F,2755F,15F, 75F,0F);

    @Test
    @DisplayName("Greenhouse Class")
    public void testGreenhouse(){
        Assertions.assertEquals(gh1, gh1);
        Assertions.assertNotEquals(gh1, gh2);
        Assertions.assertNotEquals(gh1, null);
        Assertions.assertNotEquals(gh1, data1);
        Assertions.assertEquals(gh2.hashCode(), gh3.hashCode());
    }
    @Test
    @DisplayName("GreenhouseData Class")
    public void testGreenhouseData(){
        Assertions.assertEquals(data1, data1);
        Assertions.assertNotEquals(data1, data2);
        Assertions.assertNotEquals(data1, null);
        Assertions.assertNotEquals(data1, gh1);
        Assertions.assertEquals(data2.hashCode(), data3.hashCode());
    }
}
