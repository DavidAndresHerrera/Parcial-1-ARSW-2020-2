package edu.eci.arsw.api.primesrepo.service;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@Service("primeServiceStub")
public class PrimeServiceStub implements PrimeService
{
    private CopyOnWriteArrayList<FoundPrime> primos;


    public  PrimeServiceStub(){
        crearLista();
    }

    private void crearLista() {
        primos = new CopyOnWriteArrayList<>();
        FoundPrime uno = new FoundPrime();
        uno.setUser("david");
        uno.setPrime("1");
        primos.add(uno);
        FoundPrime dos = new FoundPrime();
        dos.setUser("andres");
        dos.setPrime("2");
        primos.add(dos);
        FoundPrime tres = new FoundPrime();
        tres.setUser("herrera");
        tres.setPrime("3");
        primos.add(tres);
        FoundPrime cinco = new FoundPrime();
        cinco.setUser("moya");
        cinco.setPrime("5");
        primos.add(cinco);
        FoundPrime siete = new FoundPrime();
        siete.setUser("cesar");
        siete.setPrime("7");
        primos.add(siete);
        FoundPrime once = new FoundPrime();
        once.setUser("villamil");
        once.setPrime("11");
        primos.add(once);
    }

    @Override
    public void addFoundPrime( FoundPrime foundPrime ) throws PrimeException {
        for(FoundPrime i: primos){
            if(i.getPrime().equals(foundPrime.getPrime())){
                throw new PrimeException("El numero ya existe");
            }
        }
        primos.add(foundPrime);
    }

    @Override
    public List<FoundPrime> getFoundPrimes() throws PrimeException {
        if (primos.size()==0){
            throw new PrimeException("No hay ningun primo");
        }
        return primos;
    }

    @Override
    public FoundPrime getPrime( String prime ) throws PrimeException {
        for(FoundPrime i: primos){
            if(i.getPrime().equals(prime)){
                return i;
            }
        }
        throw new PrimeException("No existe ese primo");
    }
}
