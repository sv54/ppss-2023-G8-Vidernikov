package ppss.Alquilacoches;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlquilaCoche2Stub extends AlquilaCoches2 {
    
    
    IService s;
    
    @Override
    public IService getServicio(){
        return s;
    }
    public void setServicio(IService stub){
        s = stub;
    }


}
