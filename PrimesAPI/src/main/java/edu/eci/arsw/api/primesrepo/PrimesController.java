package edu.eci.arsw.api.primesrepo;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.service.PrimeException;
import edu.eci.arsw.api.primesrepo.service.PrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@RestController
@RequestMapping(value = "/primes")
public class PrimesController
{
    @Autowired
    @Qualifier("primeServiceStub")
    PrimeService primeService;


    @RequestMapping( method = RequestMethod.GET )
    public ResponseEntity<?> getPrimes()
    {
        try {
            return new ResponseEntity<>(primeService.getFoundPrimes(), HttpStatus.ACCEPTED);
        } catch (PrimeException e) {
            return new ResponseEntity<>("No se encontró ese cinema", HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value="/{primenumber}", method = GET)
    public ResponseEntity<?> getPrimeNumber(@PathVariable("primenumber") String primeNumber){
        try {
            return new ResponseEntity<>(primeService.getPrime(primeNumber), HttpStatus.ACCEPTED);
        }catch (PrimeException e){
            return new ResponseEntity<>("No se encontró ese numero", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addPrime(@RequestBody FoundPrime fp){
        try {
            primeService.addFoundPrime(fp);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (PrimeException e) {
            return new ResponseEntity<>("El numero ya existe",HttpStatus.FORBIDDEN);
        }

    }





}
