package edu.eci.arsw.primefinder;

import edu.eci.arsw.math.MathUtilities;

import javax.sound.midi.Track;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PrimeFinder{
    private ArrayList<ThreadPrimeFinder> hilos;
    private boolean ejecucion;

	public PrimeFinder(){

        hilos = new ArrayList<ThreadPrimeFinder>();
        ejecucion = true;

    }
	

	public void findPrimes(BigInteger _a, BigInteger _b, PrimesResultSet prs) {
        hilos = new ArrayList<ThreadPrimeFinder>();
        BigInteger a = _a;
        BigInteger b = _b;
        BigInteger numThreads = new BigInteger("4");
/*
                MathUtilities mt=new MathUtilities();
                
                int itCount=0;
            
                BigInteger i=a;
                while (i.compareTo(b)<=0){
                    itCount++;
                    if (mt.isPrime(i)){
                        prs.addPrime(i);
                    }

                    i=i.add(BigInteger.ONE);
                }
                
	}

 */
        BigInteger division = b.subtract(a).divide(numThreads);
        int rango = Math.round(division.intValue());
        for (int i = 0; i < 4; i++) {
            ThreadPrimeFinder hilo = new ThreadPrimeFinder((a.intValue() + (i * rango)), (a.intValue() + ((i + 1) * rango)), prs);
            hilos.add(hilo);
        }
        for (ThreadPrimeFinder i : hilos) {
            i.start();
        }

        for (ThreadPrimeFinder i : hilos) {
            try {
                i.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }




    }


	
	
}
